  const playerController = require('../controllers/playerController');

  let playerRouter = require('express').Router();

  playerRouter.get('/gettoprated', playerController.findTopRatedPlayers);

  module.exports = playerRouter;