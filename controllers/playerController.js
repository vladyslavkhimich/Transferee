const {Sequelize, sequelize} = require('../models/others/sequelize');
const db = require('../models');
const ImageHelper = require('../helpers/ImageHelper');

exports.findTopRatedPlayers = (req, res) => {
    db.Player.findAll({include: [{model: db.Match_Player_Rating, order: [['Match_Rating_ID', 'DESC']], limit: 10}, {model: db.Club, order: [[db.Former_Club, 'Date_Of_Joining', 'DESC']]}]}).then(players => {
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
  db.Player.findAll({include: [{model: db.Player_Price_Change, order: [['Player_Price_Change_ID', 'DESC']], limit: 1}, {model: db.Club, order: [[db.Former_Club, 'Date_Of_Joining', 'DESC']]}]}).then(players => {
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
    let searchText = req.query.searchText;
    db.Player.findAll({ include: [{model: db.Club, order: [[db.Former_Club, 'Date_Of_Joining', 'DESC']]}], where: { Name: sequelize.where(sequelize.fn('LOWER', sequelize.col('Player.Name')), 'LIKE', '%' + searchText + '%') }}).then(players => {
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