const {Sequelize, sequelize} = require('../models/others/sequelize');
const db = require('../models');
const ImageHelper = require('../helpers/ImageHelper');



exports.getPlayerUploadImagePage = (req, res) => {
    db.Player.findAll().then(players => {
        let playerOptions = players.map((player) => ({
                Player_ID: player.Player_ID,
                Player_Option: player.Name + ' (' + player.Player_ID + ')'
        }));
        res.render('fileplayer', {
            title: 'Player upload',
            playerOptions: playerOptions,
            scripts: [{script: '/javascripts/playerimagejs.js'}],
        });
    });
};

exports.checkIfPlayerImageExists = (req, res) => {
    let fileName = req.query.fileName;
    if (ImageHelper.checkIfPlayerImageExists(fileName))
        res.send('Exists');
    else
        res.send('Not exist');
};

exports.getClubUploadImagePage = (req, res) => {
  db.Club.findAll().then(clubs => {
      let clubOptions = clubs.map((club) => ({
          Club_ID: club.Club_ID,
          Club_Option: club.Name + ' (' + club.Club_ID + ')'
      }));
      res.render('fileclub', {
          title: 'Club upload',
          clubOptions: clubOptions,
          scripts: [{script: '/javascripts/clubimagejs.js'}]
      })
  })
};

exports.checkIfClubImageExists = (req, res) => {
    let fileName = req.query.fileName;
    if (ImageHelper.checkIfClubImageExists(fileName))
        res.send('Exists');
    else
        res.send('Not exist');
};

exports.getCountryUploadImagePage = (req, res) => {
  db.Country.findAll().then(countries => {
     let countryOptions = countries.map((country) => ({
         Country_ID: country.Country_ID,
         Country_Option: country.Name
     }));
     res.render('filecountry', {
         title: 'Country upload',
         countryOptions: countryOptions,
         scripts: [{script: '/javascripts/countryimagejs.js'}]
     })
  });
};

exports.checkIfCountryImageExists = (req, res) => {
  let fileName = req.query.fileName;
  if (ImageHelper.checkIfCountryImageExists(fileName))
      res.send('Exists');
  else
      res.send('Not exist');
};