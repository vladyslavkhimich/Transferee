const {Sequelize, sequelize} = require('../models/others/sequelize');
const db = require('../models');

exports.findTopRatedPlayers = (req, res) => {
    db.Player.findAll({include: [{model: db.Match_Player_Rating, order: [['Match_Rating_ID', 'DESC']], limit: 10}]}).then(players => {
       console.log(JSON.stringify(players));

       res.send('Players fetched, see result in console');
    });
};