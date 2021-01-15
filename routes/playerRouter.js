  const playerController = require('../controllers/playerController');

  let playerRouter = require('express').Router();

  playerRouter.get('/gettoprated', playerController.findTopRatedPlayers);
  playerRouter.get('/gettopmarket', playerController.findTopMarketValuePlayers);
  playerRouter.get('/gettransfers', playerController.findLatestTransfers);
  playerRouter.get('/getplayersbyname', playerController.findPlayersByName);

  module.exports = playerRouter;