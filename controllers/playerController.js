const {Sequelize, sequelize} = require('../models/others/sequelize');
const db = require('../models');
const ImageHelper = require('../helpers/ImageHelper');

exports.findTopRatedPlayers = (req, res) => {
    db.Player.findAll({include: [{model: db.Match_Player_Rating, order: [['Match_Rating_ID', 'DESC']], limit: 10}, {model: db.Club, order: [[db.Former_Club, 'Date_Of_Joining', 'DESC']]}]}).then(players => {
        players.forEach(player => {
            player.setDataValue('Average_Rating', calculatePlayerAverageRating(player.Match_Player_Ratings));
        });
        players.sort((a, b) => a['Average_Rating'] - b['Average_Rating']);
        let playersResponse = players.map((player) => ({Player_ID: player.Player_ID, Name: player.Name, Image_URL: ImageHelper.getPlayerImagePath(player.Image), Club_URL: ImageHelper.getClubImagePath(player.Clubs[0].Logo), Club_Name: player.Clubs[0].Name, Average_Rating: player.getDataValue('Average_Rating')}));
        let topRatedPlayersPOJO = {topRatedPlayersPOJO: JSON.parse(JSON.stringify(playersResponse))};
        res.json(topRatedPlayersPOJO);
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