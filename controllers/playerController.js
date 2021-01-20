const {Sequelize, sequelize} = require('../models/others/sequelize');
const db = require('../models');
const ImageHelper = require('../helpers/ImageHelper');

exports.findTopRatedPlayers = (req, res) => {
    db.Player.findAll({include: [{model: db.Match_Player_Rating, order: [['Match_Rating_ID', 'DESC']], limit: 10}, {model: db.Club}], order: [[db.Club, db.Former_Club, 'Date_Of_Joining', 'DESC']]}).then(players => {
        players = players.filter((player) => {
            return player.Match_Player_Ratings.length > 0;
        });
        players.forEach(player => {
            player.setDataValue('Average_Rating', calculatePlayerAverageRating(player.Match_Player_Ratings));
        });
        players.sort((a, b) => b['Average_Rating'] - a['Average_Rating']);
        let playersResponse = players.slice(0, 10).map((player) => ({Player_ID: player.Player_ID, Name: player.Name, Image_URL: ImageHelper.getPlayerImagePath(player.Image), Club_URL: ImageHelper.getClubImagePath(player.Clubs[0].Logo), Club_Name: player.Clubs[0].Name, Average_Rating: player.getDataValue('Average_Rating')}));
        let topRatedPlayersJSON = {topRatedPlayersPOJO: JSON.parse(JSON.stringify(playersResponse))};
        res.json(topRatedPlayersJSON);
        console.log('Players queried at ' + new Date());
    });
};

function calculatePlayerAverageRating(matchRatings) {
    let ratingsSum = 0.0;
    matchRatings.forEach(matchRating => {
        ratingsSum += matchRating.Match_Rating;
    });
    return ratingsSum / matchRatings.length;
}

exports.findTopMarketValuePlayers = (req, res) => {
  db.Player.findAll({include: [{model: db.Player_Price_Change, order: [['Player_Price_Change_ID', 'DESC']], limit: 1}, {model: db.Club}], order: [[db.Club, db.Former_Club, 'Date_Of_Joining', 'DESC']]}).then(players => {
      players = players.filter((player) => {
         return player.Player_Price_Changes.length > 0;
      });
     players.sort((a, b) => b.Player_Price_Changes[0].Market_Price - a.Player_Price_Changes[0].Market_Price);
     let playersResponse = players.slice(0,10).map((player) => ({Player_ID: player.Player_ID, Name: player.Name, Image_URL: ImageHelper.getPlayerImagePath(player.Image), Club_URL: ImageHelper.getClubImagePath(player.Clubs[0].Logo), Club_Name: player.Clubs[0].Name, Market_Price: player.Player_Price_Changes[0].Market_Price}));
     let topMarketValuePlayersJSON = { topMarketValuePlayersPOJO: JSON.parse(JSON.stringify(playersResponse)) };
     res.json(topMarketValuePlayersJSON);
     console.log('Top market value players queried at ' + new Date());
  });
};

exports.findLatestTransfers = (req, res) => {
    db.Transfer.findAll({order: [['Date_Of_Transfer', 'DESC']], limit: 10, include: [{model: db.Player}, {model: db.Club, as: 'Departure_Club'}, {model: db.Club, as: 'Joining_Club'}]}).then(transfers => {
        let transfersProcessedCount = 0;
            transfers.forEach(transfer => {
           if (transfer.Market_Value == null) {
               db.Player.findByPk(transfer.Player.Player_ID, {include: {model: db.Player_Price_Change}, order: [[db.Player_Price_Change, 'Change_Date', 'DESC']]}).then(player => {
                   let closestPriceChange = player.Player_Price_Changes.reduce((a, b) => new Date(transfer.Date_Of_Transfer) - new Date(a.Change_Date) < new Date(transfer.Date_Of_Transfer) - new Date(b.Change_Date) ? a : b);
                   transfer.Market_Value = closestPriceChange.Market_Price;
               });
           }
           db.Player.findByPk(transfer.Player.Player_ID, {include: {model: db.Player_Contract}, order: [[db.Player_Contract, 'Start_Date', 'DESC']]}).then(player => {
               if (player.Player_Contracts.length > 0) {
               let closestAfterPlayerContract = player.Player_Contracts.reduce((a, b) =>  {
                 let adiff = new Date(a.Start_Date) - new Date(transfer.Date_Of_Transfer);
                 return adiff > 0 && adiff > new Date(b.Start_Date) - new Date(transfer.Date_Of_Transfer) ? a : b
               });
                 transfer.setDataValue('Contract', closestAfterPlayerContract);
                 transfersProcessedCount++;
                 if (transfersProcessedCount === transfers.length) {
                     let transfersResponse = transfers.map((transfer) => ({
                         Player_ID: transfer.Player.Player_ID,
                         Name: transfer.Player.Name,
                         Image_URL: ImageHelper.getPlayerImagePath(transfer.Player.Image),
                         Date_Of_Transfer: transfer.Date_Of_Transfer,
                         Departure_Club_Name: transfer.Departure_Club.Name,
                         Departure_Club_URL: ImageHelper.getClubImagePath(transfer.Departure_Club.Logo),
                         Joining_Club_Name: transfer.Joining_Club.Name,
                         Joining_Club_URL: ImageHelper.getClubImagePath(transfer.Joining_Club.Logo),
                         Transfer_Price: transfer.Transfer_Price,
                         Contract_Start_Date: transfer.getDataValue('Contract') != null ? transfer.getDataValue('Contract').Start_Date : null,
                         Contract_Finish_Date: transfer.getDataValue('Contract') != null ? transfer.getDataValue('Contract').Finish_Date : null,
                         Market_Value: transfer.Market_Value }));
                     let latestTransfersJSON = {latestTransfersPOJO: JSON.parse(JSON.stringify(transfersResponse))};
                     res.json(latestTransfersJSON);
                 }
               }
               else {
                   transfersProcessedCount++;
                   transfer.setDataValue('Contract', null);
                   if (transfersProcessedCount === transfers.length) {
                       let transfersResponse = transfers.map((transfer) => ({
                           Player_ID: transfer.Player.Player_ID,
                           Name: transfer.Player.Name,
                           Image_URL: ImageHelper.getPlayerImagePath(transfer.Player.Image),
                           Date_Of_Transfer: transfer.Date_Of_Transfer,
                           Departure_Club_Name: transfer.Departure_Club.Name,
                           Departure_Club_URL: ImageHelper.getClubImagePath(transfer.Departure_Club.Logo),
                           Joining_Club_Name: transfer.Joining_Club.Name,
                           Joining_Club_URL: ImageHelper.getClubImagePath(transfer.Joining_Club.Logo),
                           Transfer_Price: transfer.Transfer_Price,
                           Contract_Start_Date: transfer.getDataValue('Contract') != null ? transfer.getDataValue('Contract').Start_Date : null,
                           Contract_Finish_Date: transfer.getDataValue('Contract') != null ? transfer.getDataValue('Contract').Finish_Date : null,
                           Market_Value: transfer.Market_Value }));
                       let latestTransfersJSON = {latestTransfersPOJO: JSON.parse(JSON.stringify(transfersResponse))};
                       res.json(latestTransfersJSON);
                   }
               }
           });

           });
        });

};

exports.findPlayersByName = (req, res) => {
    let searchText = req.query.searchText.toLowerCase();
    db.Player.findAll({ include: [{model: db.Club}], order: [[db.Club, db.Former_Club, 'Date_Of_Joining', 'DESC']], where: { Name: sequelize.where(sequelize.fn('LOWER', sequelize.col('Player.Name')), 'LIKE', '%' + searchText + '%') }}).then(players => {
        if (players.length === 0) {
            res.status(404).send();
        }
        else {
                let playersProcessedCount = 0;
            players.forEach(player => {
                db.Country.findByPk(player.Country_ID).then(country => {
                   player.setDataValue('Country', country);
                   playersProcessedCount++;
                   if (playersProcessedCount === players.length) {
                       let foundPlayersResponse = players.map((player) => ({Player_ID: player.Player_ID, Name: player.Name, Image_URL: ImageHelper.getPlayerImagePath(player.Image), Club_URL: ImageHelper.getClubImagePath(player.Clubs[0].Logo), Club_Name: player.Clubs[0].Name, Country_URL: ImageHelper.getCountryImagePath(player.getDataValue('Country').Flag), Country_Name: player.getDataValue('Country').Name}));
                       let foundPlayersJSON = {foundPlayersPOJO: JSON.parse(JSON.stringify(foundPlayersResponse))};
                       res.json(foundPlayersJSON);
                   }
                });
            });
        }
    });
};

exports.findPlayerByID = (req, res, id) => {
    db.Player.findByPk(id, {include: [{model: db.Club}], order: [[db.Club, db.Former_Club, 'Date_Of_Joining', 'DESC']]}).then(player => {
        let playerResponse = {Player_ID: id, Name: player.Name, Image_URL: ImageHelper.getPlayerImagePath(player.Image), Club_Name: player.Clubs[0].Name, Club_URL: ImageHelper.getClubImagePath(player.Clubs[0].Logo) };
        res.json(JSON.parse(JSON.stringify(playerResponse)));
    });
};

exports.findPlayerOverviewByID = (req, res, id) => {
  db.Player.findByPk(id, {include: [{model: db.Match_Position}]}).then(player => {
     let positionDictionary = {};
     db.Country.findByPk(player.Country_ID).then(country => {
         player.setDataValue('Country', country);
         let matchPositionsProcessed = 0;
         if (player.Match_Positions.length > 0) {


         player.Match_Positions.forEach(matchPosition => {
             db.Player_Position.findByPk(matchPosition.Player_Position_ID).then(playerPosition => {
                 if (positionDictionary[playerPosition.Full_Position_Name] == null) {
                     positionDictionary[playerPosition.Full_Position_Name] = 1;
                     matchPositionsProcessed++;
                     if (matchPositionsProcessed === player.Match_Positions.length) {
                        let allPositions = sortPositions(positionDictionary);
                         let othersPositions = allPositions.slice(1, allPositions.length);
                         let othersArray = [];
                         othersPositions.forEach(position => {
                             othersArray.push(position[0]);
                         });
                         let playerOverviewResponse = {
                             Height: player.Height,
                             Age: calculateAge(player.Birth_Date),
                             Birth_Date: player.Birth_Date,
                             Shirt_Number: player.Shirt_Number,
                             Preferred_Foot: player.Preferred_Foot,
                             Country_URL: ImageHelper.getCountryImagePath(country.Flag),
                             Pseudonym: player.Pseudonim,
                             Primary: allPositions[0][0],
                             Others: othersArray};
                         res.json(JSON.parse(JSON.stringify(playerOverviewResponse)));
                        console.log();
                     }
                 }
                 else {
                     positionDictionary[playerPosition.Full_Position_Name] = positionDictionary[playerPosition.Full_Position_Name] + 1;
                     matchPositionsProcessed++;
                     if (matchPositionsProcessed === player.Match_Positions.length) {
                         let allPositions = sortPositions(positionDictionary);
                         let othersPositions = allPositions.slice(1, allPositions.length);
                         let othersArray = [];
                         othersPositions.forEach(position => {
                             othersArray.push(position[0]);
                         });
                         let playerOverviewResponse = {
                             Height: player.Height,
                             Age: calculateAge(player.Birth_Date),
                             Birth_Date: player.Birth_Date,
                             Shirt_Number: player.Shirt_Number,
                             Preferred_Foot: player.Preferred_Foot == null ? false : player.Preferred_Foot,
                             Country_URL: ImageHelper.getCountryImagePath(country.Flag),
                             Pseudonym: player.Pseudonim,
                             Primary: allPositions[0][0],
                             Others: othersArray};
                         res.json(JSON.parse(JSON.stringify(playerOverviewResponse)));
                         console.log();
                     }
                 }
             })
            });
         }
         else {
             let playerOverviewResponse = {
                 Height: player.Height,
                 Age: calculateAge(player.Birth_Date),
                 Birth_Date: player.Birth_Date,
                 Shirt_Number: player.Shirt_Number,
                 Preferred_Foot: player.Preferred_Foot == null ? false : player.Preferred_Foot,
                 Country_URL: ImageHelper.getCountryImagePath(country.Flag),
                 Pseudonym: player.Pseudonim,
                 Primary: null,
                 Others: null
             };
             res.json(JSON.parse(JSON.stringify(playerOverviewResponse)));
         }
     });
  });
};

exports.findPlayerMarketByID = (req, res, id) => {
    db.Player.findByPk(id, {include: [{model: db.Club}, {model: db.Player_Price_Change}], order: [[db.Club, db.Former_Club, 'Date_Of_Joining', 'DESC'], [db.Player_Price_Change, 'Change_Date', 'DESC']]}).then(player => {
       for (let i = 0; i < player.Player_Price_Changes.length; i++) {
           if (i + 1 < player.Player_Price_Changes.length) {
               player.Player_Price_Changes[i].setDataValue('Previous_Price', player.Player_Price_Changes[i + 1].Market_Price);
               player.Player_Price_Changes[i].Market_Price > player.Player_Price_Changes[i + 1].Market_Price ? player.Player_Price_Changes[i].setDataValue('Is_Rise', true) : player.Player_Price_Changes[i].setDataValue('Is_Rise', false);
           }
           else {
               player.Player_Price_Changes[i].setDataValue('Previous_Price', null);
               player.Player_Price_Changes[i].setDataValue('Is_Rise', null);
           }
           let closestPriceChangeClub = player.Clubs.reduce((a, b) => new Date(player.Player_Price_Changes[i].Change_Date) - new Date(a.Former_Club.Date_Of_Joining) < new Date(player.Player_Price_Changes[i].Change_Date) - new Date(b.Former_Club.Date_Of_Joining) ? a : b);
           player.Player_Price_Changes[i].setDataValue('Club_URL', ImageHelper.getClubImagePath(closestPriceChangeClub.Logo));
       }
       db.Player.findAll({where: {Country_ID: player.Country_ID}, include: {model: db.Player_Price_Change, limit: 1, order: [['Change_Date', 'DESC']]}}).then(players => {
          let countryPriceArray = [];
          players = players.filter((player) => {
             return player.Player_Price_Changes.length > 0;
          });
          players.forEach((player) => {
             countryPriceArray.push(player.Player_Price_Changes[0].Market_Price);
          });
          countryPriceArray.sort((a, b) => b - a);
          player.setDataValue('Country_Price', player.Player_Price_Changes[0] != null ? countryPriceArray.indexOf(player.Player_Price_Changes[0].Market_Price) + 1 : null);
           db.Player.findAll({include: {model: db.Player_Price_Change, limit: 1, order: [['Change_Date', 'DESC']]}}).then(players => {
               let worldwidePricesArray = [];
               players = players.filter((player) => {
                  return player.Player_Price_Changes.length > 0;
               });
               players.forEach((player) => {
                   worldwidePricesArray.push(player.Player_Price_Changes[0].Market_Price);
               });
               worldwidePricesArray.sort((a, b) => b - a);
               player.setDataValue('Worldwide_Price', player.Player_Price_Changes[0] != null ? worldwidePricesArray.indexOf(player.Player_Price_Changes[0].Market_Price) + 1 : null);
               let playerPriceChangesResponse = player.Player_Price_Changes.map((priceChange) => ({Change_Date: priceChange.Change_Date, Previous_Price: priceChange.getDataValue('Previous_Price'), Club_URL: priceChange.getDataValue('Club_URL'), Is_Rise: priceChange.getDataValue('Is_Rise'), New_Price: priceChange.Market_Price}));
               if (player.Player_Price_Changes[0] != null) {
                   let playerResponse = {
                       Current_Price: player.Player_Price_Changes[0].Market_Price,
                       Last_Price_Change: player.Player_Price_Changes[0].Change_Date,
                       Price_Changes_POJO: playerPriceChangesResponse,
                       Worldwide_Price: player.getDataValue('Worldwide_Price'),
                       Country_Price: player.getDataValue('Country_Price')
                   };
                   res.json(JSON.parse(JSON.stringify(playerResponse)));
               }
               else
                   res.status(404).send();
           });
       });
    });
};

exports.findPlayerTransfersByID = (req, res, id) => {
    db.Transfer.findAll({where: {Player_ID: id}, include: [{model: db.Club, as: 'Departure_Club'}, {model: db.Club, as: 'Joining_Club'}], order: [['Date_Of_Transfer', 'DESC']]}).then(transfers => {
        let transfersProcessedCount = 0;
        transfers.forEach(transfer => {
           if (transfer.Market_Value == null) {
               db.Player.findByPk(id, {include: {model: db.Player_Price_Change}, order: [[db.Player_Price_Change, 'Change_Date', 'DESC']]}).then(player => {
                   let closestPriceChange = player.Player_Price_Changes.reduce((a, b) => new Date(transfer.Date_Of_Transfer) - new Date(a.Change_Date) < new Date(transfer.Date_Of_Transfer) - new Date(b.Change_Date) ? a : b);
                   transfer.Market_Value = closestPriceChange.Market_Price;
               });
           }
            db.Player.findByPk(id, {include: {model: db.Player_Contract}, order: [[db.Player_Contract, 'Start_Date', 'DESC']]}).then(player => {
                if (player.Player_Contracts.length > 0) {
                    let closestAfterPlayerContract = player.Player_Contracts.reduce((a, b) => {
                        let adiff = new Date(a.Start_Date) - new Date(transfer.Date_Of_Transfer);
                        return adiff > 0 && adiff > new Date(b.Start_Date) - new Date(transfer.Date_Of_Transfer) ? a : b
                    });
                    transfer.setDataValue('Contract', closestAfterPlayerContract);
                    transfersProcessedCount++;
                    if (transfersProcessedCount === transfers.length) {
                        let transfersResponse = transfers.map((transfer) => ({
                            Date_Of_Transfer: transfer.Date_Of_Transfer,
                            Departure_Club_Name: transfer.Departure_Club.Name,
                            Departure_Club_URL: ImageHelper.getClubImagePath(transfer.Departure_Club.Logo),
                            Joining_Club_Name: transfer.Joining_Club.Name,
                            Joining_Club_URL: ImageHelper.getClubImagePath(transfer.Joining_Club.Logo),
                            Transfer_Price: transfer.Transfer_Price,
                            Contract_Start_Date: transfer.getDataValue('Contract') != null ? transfer.getDataValue('Contract').Start_Date : null,
                            Contract_Finish_Date: transfer.getDataValue('Contract') != null ? transfer.getDataValue('Contract').Finish_Date : null,
                            Market_Value: transfer.Market_Value
                        }));
                        let playerTransfersJSON = {playerTransfersPOJO: JSON.parse(JSON.stringify(transfersResponse))};
                        res.json(playerTransfersJSON);
                    }
                } else {
                    transfersProcessedCount++;
                    transfer.setDataValue('Contract', null);
                    if (transfersProcessedCount === transfers.length) {
                        let transfersResponse = transfers.map((transfer) => ({
                            Date_Of_Transfer: transfer.Date_Of_Transfer,
                            Departure_Club_Name: transfer.Departure_Club.Name,
                            Departure_Club_URL: ImageHelper.getClubImagePath(transfer.Departure_Club.Logo),
                            Joining_Club_Name: transfer.Joining_Club.Name,
                            Joining_Club_URL: ImageHelper.getClubImagePath(transfer.Joining_Club.Logo),
                            Transfer_Price: transfer.Transfer_Price,
                            Contract_Start_Date: transfer.getDataValue('Contract') != null ? transfer.getDataValue('Contract').Start_Date : null,
                            Contract_Finish_Date: transfer.getDataValue('Contract') != null ? transfer.getDataValue('Contract').Finish_Date : null,
                            Market_Value: transfer.Market_Value
                        }));
                        let playerTransfersJSON = {playerTransfersPOJO: JSON.parse(JSON.stringify(transfersResponse))};
                        res.json(playerTransfersJSON);
                    }
                }
            });
        });
    });
};

exports.findPlayerStatsByID = (req, res, id) => {
    db.Player.findByPk(id, {include:
            [   {model: db.Club, order: [[db.Club, db.Former_Club, 'Date_Of_Joining', 'DESC']]},
                {model: db.Main_Squad_Player, limit: 10, order: [[db.Match, 'Match_Date', 'DESC']], include: [{model: db.Match, include: [{model: db.Match_Card}, {model: db.Goal}, {model: db.Match_Position, include: [{model: db.Player_Position}]}, {model: db.Match_Player_Rating}]}]},
                {model: db.Substitution, as: 'Substituted_Player',  limit: 10, order: [[db.Match, 'Match_Date', 'DESC']], include: [{model: db.Match, include: [{model: db.Match_Card}, {model: db.Goal}, {model: db.Match_Position, include: [{model: db.Player_Position}]}, {model: db.Match_Player_Rating}]}]},
                {model: db.Substitution, as: 'Substitute_Player',  limit: 10, order: [[db.Match, 'Match_Date', 'DESC']], include: [{model: db.Match, include: [{model: db.Match_Card}, {model: db.Goal}, {model: db.Match_Position, include: [{model: db.Player_Position}]}, {model: db.Match_Player_Rating}]}]}
            ]})
        .then(playerWithMatches => {
       console.log();
       let matches = [];
       for (let i = 0; i < 10; i++) {
           if (playerWithMatches.Main_Squad_Players[i] != null) {
               matches.push(playerWithMatches.Main_Squad_Players[i].Match);
           }
           if (playerWithMatches.Substitute_Player[i] != null) {
               if (!matches.some(match => match.Match_ID === playerWithMatches.Substitute_Player[i].Match.Match_ID)) {
                   matches.push(playerWithMatches.Substitute_Player[i].Match);
               }
           }
       }

           matches.sort((a, b) => new Date(b.Match_Date) - new Date(a.Match_Date));
           console.log();
            let playerTotalGoals = 0;
            let playerTotalAssists = 0;
            let playerTotalRating = 0.0;
            let playerMatchesWithRating = 0;
            let matchesProcessedCount = 0;
            if (matches.length > 0) {
                matches.forEach(match => {
                    let homeGoals = 0;
                    let awayGoals = 0;
                    let matchClubID = getClosestClubIdToDate(match.Match_Date, playerWithMatches.Clubs);
                    let isPlayerTeamHomeTeam = matchClubID === match.Home_Club_ID;
                    let playerGoals = 0;
                    let playerAssists = 0;
                    let opponentClubID = isPlayerTeamHomeTeam ? match.Guest_Club_ID : match.Home_Club_ID;
                    db.Club.findByPk(opponentClubID).then(club => {
                        match.setDataValue('Club_URL', club.Logo);
                        match.setDataValue('Club_Name', club.Name);
                        db.Player.findAll({
                            include: [{
                                model: db.Club,
                                order: [[db.Club, db.Former_Club, 'Date_Of_Joining', 'DESC']]
                            }],
                        }).then(players => {
                            let playersIdFromMatchClub = [];
                            players.forEach(player => {
                                if (isPlayerFromMatchClub(matchClubID, match.Match_Date, player.Clubs))
                                    playersIdFromMatchClub.push(player.Player_ID);
                            });
                            console.log();
                            match.Goals.forEach(goal => {
                                if (playersIdFromMatchClub.some(player => player.Player_ID === goal.Goalscorer_ID) != null) {
                                    if (goal.Is_Own_Goal && isPlayerTeamHomeTeam)
                                        awayGoals++;
                                    else if (isPlayerTeamHomeTeam)
                                        homeGoals++;
                                    if (goal.Goalscorer_ID === playerWithMatches.Player_ID) {
                                        playerTotalGoals++;
                                        playerGoals++;
                                    }
                                    if (goal.Assistant_ID === playerWithMatches.Player_ID) {
                                        playerTotalAssists++;
                                        playerAssists++;
                                    }
                                } else if (isPlayerTeamHomeTeam) {
                                    awayGoals++;
                                } else
                                    homeGoals++;
                            });
                            match.setDataValue('Player_Goals', playerGoals);
                            match.setDataValue('Player_Assists', playerAssists);
                            let isPlayerTeamWin = false;
                            let isPlayerTeamLoss = false;
                            if (isPlayerTeamHomeTeam) {
                                if (homeGoals > awayGoals) {
                                    isPlayerTeamWin = true;
                                } else if (homeGoals < awayGoals) {
                                    isPlayerTeamLoss = true;
                                }
                            } else {
                                if (homeGoals > awayGoals)
                                    isPlayerTeamWin = false;
                                else if (homeGoals < awayGoals)
                                    isPlayerTeamWin = true;
                            }
                            match.setDataValue('Match_Score', homeGoals + ' : ' + awayGoals);
                            match.setDataValue('Is_Win', isPlayerTeamWin);
                            match.setDataValue('Is_Loss', isPlayerTeamLoss);
                            match.Match_Cards.forEach(card => {
                                if (isRedCard(card))
                                    match.setDataValue('Red_Card', card.Minute_Of_Receiving);
                                else
                                    match.setDataValue('Yellow_Card', card.Minute_Of_Receiving);
                            });
                            let mainSquadPlayer = playerWithMatches.Main_Squad_Players.find(main => main.Match_ID === match.Match_ID && main.Player_ID === playerWithMatches.Player_ID);
                            let minutesPlayed;
                            if (mainSquadPlayer != null) {
                                let substitutedPlayer = playerWithMatches.Substituted_Player.find(substituted => substituted.Match_ID === match.Match_ID && substituted.Substituted_Player_ID === playerWithMatches.Player_ID);
                                if (substitutedPlayer != null) {
                                    minutesPlayed = substitutedPlayer.Minute_Of_Substitution
                                } else {
                                    if (!match.Is_With_Added_Time)
                                        minutesPlayed = 90;
                                    else
                                        minutesPlayed = 120;
                                }
                            } else {
                                let substitutePlayer = playerWithMatches.Substitute_Player.find(substitute => substitute.Match_ID === match.Match_ID && substitute.Substitute_Player_ID === playerWithMatches.Player_ID);
                                if (substitutePlayer != null) {
                                    if (!match.Is_With_Added_Time)
                                        minutesPlayed = 90 - substitutePlayer.Minute_Of_Substitution;
                                    else
                                        minutesPlayed = 120 - substitutePlayer.Minute_Of_Substitution;
                                }
                            }
                            match.setDataValue('Minutes_Played', minutesPlayed);
                            match.Match_Player_Ratings = match.Match_Player_Ratings.filter((playerRating) => {
                                return playerRating.Player_ID === playerWithMatches.Player_ID
                            });
                            if (match.Match_Player_Ratings[0] != null) {
                                playerTotalRating += match.Match_Player_Ratings[0].Match_Rating;
                                playerMatchesWithRating++;
                            }
                            matchesProcessedCount++;
                            if (matchesProcessedCount === matches.length) {
                                let matchesPOJO = matches.map((match) => ({
                                    Match_Date: match.Match_Date,
                                    Club_URL: ImageHelper.getClubImagePath(match.getDataValue('Club_URL')),
                                    Club_Name: match.getDataValue('Club_Name'),
                                    Match_Score: match.get('Match_Score'),
                                    Is_Win: match.getDataValue('Is_Win'),
                                    Is_Loss: match.getDataValue('Is_Loss'),
                                    Yellow_Card: match.getDataValue('Yellow_Card') != null ? match.getDataValue('Yellow_Card') : null,
                                    Red_Card: match.getDataValue('Red_Card') != null ? match.getDataValue('Red_Card') : null,
                                    Minutes_Played: match.getDataValue('Minutes_Played'),
                                    Player_Goals: match.getDataValue('Player_Goals'),
                                    Player_Assists: match.getDataValue('Player_Assists'),
                                    Player_Rating: match.Match_Player_Ratings[0] != null ? match.Match_Player_Ratings[0].Match_Rating : null,
                                    Match_Position: match.Match_Positions[0].Player_Position != null ? match.Match_Positions[0].Player_Position.Name : null
                                }));
                                let statsResponse = {
                                    Total_Goals: playerTotalGoals,
                                    Total_Assists: playerTotalAssists,
                                    Total_Rating: playerTotalRating / playerMatchesWithRating,
                                    matchesPOJO: matchesPOJO
                                };
                                res.json(statsResponse);
                            }
                            console.log();
                        });
                    });
                })
            }
            else {
                res.status(404).send();
            }
       });

    };

function sortPositions(positionDictionary) {
    let positionItems = Object.keys(positionDictionary).map(function(key) {
        return [key, positionDictionary[key]];
    });
    positionItems.sort((a, b) => {
        return b[1] = a[1];
    });
    return positionItems;
}

function calculateAge(birthDate) {
    let ageDifferenceInMS = Date.now() - new Date(birthDate);
    let ageDate = new Date(ageDifferenceInMS);
    return Math.abs(ageDate.getUTCFullYear() - 1970);
}

function calculateMatchScore(match, playerId, formerClubs) {

}


function getClosestClubIdToDate(matchDate, formerClubs) {
    let closestFormerClub = formerClubs.reduce((a, b) => new Date(matchDate) - new Date(a.Former_Club.Date_Of_Joining) < new Date(matchDate) - new Date(b.Former_Club.Date_Of_Joining) ? a : b);
    return closestFormerClub.Club_ID;
}

function isPlayerFromMatchClub(clubId, matchDate, formerClubs) {
    let closestFormerClub = formerClubs.reduce((a, b) => new Date(matchDate) - new Date(a.Former_Club.Date_Of_Joining) < new Date(matchDate) - new Date(b.Former_Club.Date_Of_Joining) ? a : b);
    return closestFormerClub.Club_ID === clubId;
}

function isRedCard(card) {
    return card.Type_Of_Card_ID === 2;
}