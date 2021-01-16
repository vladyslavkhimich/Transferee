const {Sequelize, sequelize} = require('../models/others/sequelize');
const db = require('../models');
const ImageHelper = require('../helpers/ImageHelper');

exports.findTopRatedPlayers = (req, res) => {
    db.Player.findAll({include: [{model: db.Match_Player_Rating, order: [['Match_Rating_ID', 'DESC']], limit: 10}, {model: db.Club}], order: [[db.Club, db.Former_Club, 'Date_Of_Joining', 'DESC']]}).then(players => {
        players.forEach(player => {
            player.setDataValue('Average_Rating', calculatePlayerAverageRating(player.Match_Player_Ratings));
        });
        players.sort((a, b) => b['Average_Rating'] - a['Average_Rating']);
        let playersResponse = players.slice(0, 5).map((player) => ({Player_ID: player.Player_ID, Name: player.Name, Image_URL: ImageHelper.getPlayerImagePath(player.Image), Club_URL: ImageHelper.getClubImagePath(player.Clubs[0].Logo), Club_Name: player.Clubs[0].Name, Average_Rating: player.getDataValue('Average_Rating')}));
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
     players.sort((a, b) => b.Player_Price_Changes[0].Market_Price - a.Player_Price_Changes[0].Market_Price);
     let playersResponse = players.slice(0,5).map((player) => ({Player_ID: player.Player_ID, Name: player.Name, Image_URL: ImageHelper.getPlayerImagePath(player.Image), Club_URL: ImageHelper.getClubImagePath(player.Clubs[0].Logo), Club_Name: player.Clubs[0].Name, Market_Price: player.Player_Price_Changes[0].Market_Price}));
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
                     let transfersResponse = transfers.map((transfer) => ({Player_ID: transfer.Player.Player_ID, Name: transfer.Player.Name, Image_URL: ImageHelper.getPlayerImagePath(transfer.Player.Image), Date_Of_Transfer: transfer.Date_Of_Transfer, Departure_Club_Name: transfer.Departure_Club.Name, Departure_Club_URL: ImageHelper.getClubImagePath(transfer.Departure_Club.Logo), Joining_Club_Name: transfer.Joining_Club.Name, Joining_Club_URL: ImageHelper.getClubImagePath(transfer.Joining_Club.Logo), Transfer_Price: transfer.Transfer_Price, Contract_Start_Date: transfer.getDataValue('Contract') != null ? transfer.getDataValue('Contract').Start_Date : null, Contract_Finish_Date: transfer.getDataValue('Contract') != null ? transfer.getDataValue('Contract').Finish_Date : null, Market_Value: transfer.Market_Value }));
                     let latestTransfersJSON = {latestTransfersPOJO: JSON.parse(JSON.stringify(transfersResponse))};
                     res.json(latestTransfersJSON);
                 }
               }
               else {
                   transfersProcessedCount++;
                   transfer.setDataValue('Contract', null);
                   if (transfersProcessedCount === transfers.length) {
                       let transfersResponse = transfers.map((transfer) => ({Player_ID: transfer.Player.Player_ID, Name: transfer.Player.Name, Image_URL: ImageHelper.getPlayerImagePath(transfer.Player.Image), Date_Of_Transfer: transfer.Date_Of_Transfer, Departure_Club_Name: transfer.Departure_Club.Name, Departure_Club_URL: ImageHelper.getClubImagePath(transfer.Departure_Club.Logo), Joining_Club_Name: transfer.Joining_Club.Name, Joining_Club_URL: ImageHelper.getClubImagePath(transfer.Joining_Club.Logo), Transfer_Price: transfer.Transfer_Price, Contract_Start_Date: transfer.getDataValue('Contract') != null ? transfer.getDataValue('Contract').Start_Date : null, Contract_Finish_Date: transfer.getDataValue('Contract') != null ? transfer.getDataValue('Contract').Finish_Date : null, Market_Value: transfer.Market_Value }));
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
        let playerResponse = { Name: player.Name, Image_URL: ImageHelper.getPlayerImagePath(player.Image), Club_Name: player.Clubs[0].Name, Club_URL: ImageHelper.getClubImagePath(player.Clubs[0].Logo) };
        res.json(JSON.parse(JSON.stringify(playerResponse)));
    });
};

exports.findPlayerOverviewByID = (req, res, id) => {
  db.Player.findByPk(id, {include: [{model: db.Match_Position}]}).then(player => {
     let positionDictionary = {};
     db.Country.findByPk(player.Country_ID).then(country => {
         player.setDataValue('Country', country);
         let matchPositionsProcessed = 0;
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
                         let playerOverviewResponse = {Height: player.Height, Age: calculateAge(player.Birth_Date), Birth_Date: player.Birth_Date, Shirt_Number: player.Shirt_Number, Preferred_Foot: player.Preferred_Foot, Country_URL: ImageHelper.getCountryImagePath(country.Flag), Pseudonym: player.Pseudonim, Primary: allPositions[0][0], Others: othersArray};
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
                         let playerOverviewResponse = {Height: player.Height, Age: calculateAge(player.Birth_Date), Birth_Date: player.Birth_Date, Shirt_Number: player.Shirt_Number, Preferred_Foot: player.Preferred_Foot, Country_URL: ImageHelper.getCountryImagePath(country.Flag), Pseudonym: player.Pseudonim, Primary: allPositions[0][0], Others: othersArray};
                         res.json(JSON.parse(JSON.stringify(playerOverviewResponse)));
                         console.log();
                     }
                 }
             })
         });
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
           let closestPriceChangeClub = player.Clubs.reduce((a, b) => new Date(player.Player_Price_Change[i].Change_Date) - new Date(a.Former_Club.Date_Of_Joining) < new Date(player.Player_Price_Change[i].Change_Date) - new Date(b.Former_Club.Date_Of_Joining) ? a : b);
           player.Player_Price_Changes[i].setDataValue('Club_URL', ImageHelper.getClubImagePath(closestPriceChangeClub.Logo));
       }
       db.Player.findAll({where: {Country_ID: player.Country_ID}, include: {model: db.Player_Price_Change, limit: 1, order: [['Change_Date', 'DESC']]}}).then(players => {
          let countryPriceArray = [];
          players.forEach((player) => {
             countryPriceArray.push(player.Player_Price_Changes[0].Market_Price);
          });
          countryPriceArray.sort((a, b) => b - a);
          player.setDataValue('Country_Price', countryPriceArray.indexOf(player.Player_Price_Changes[0].Market_Price) + 1);
           db.Player.findAll({include: {model: db.Player_Price_Change, limit: 1, order: [['Change_Date', 'DESC']]}}).then(players => {
               let worldwidePricesArray = [];
               players.forEach((player) => {
                   worldwidePricesArray.push(player.Player_Price_Changes[0].Market_Price);
               });
               worldwidePricesArray.sort((a, b) => b - a);
               player.setDataValue('Worldwide_Price', worldwidePricesArray.indexOf(player.Player_Price_Changes[0].Market_Price) + 1);
               let playerPriceChangesResponse = player.Player_Price_Changes.map((priceChange) => ({Change_Date: priceChange.Change_Date, Previous_Price: priceChange.getDataValue('Previous_Price'), Club_URL: priceChange.getDataValue('Club_URL'), Is_Rise: priceChange.getDataValue('Is_Rise'), New_Price: priceChange.Market_Price}));
               let playerResponse = {Current_Price: player.Player_Price_Changes[0].Market_Price, Last_Price_Change: player.Player_Price_Changes[0].Change_Date, Price_Changes_POJO: playerPriceChangesResponse, Worldwide_Price: player.getDataValue('Worldwide_Price'), Country_Price: player.getDataValue('Country_Price')};
               res.json(JSON.parse(JSON.stringify(playerResponse)));
           });
       });
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