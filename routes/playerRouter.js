  const playerController = require('../controllers/playerController');

  let playerRouter = require('express').Router();

  playerRouter.get('/gettoprated', playerController.findTopRatedPlayers);
  playerRouter.get('/gettopmarket', playerController.findTopMarketValuePlayers);
  playerRouter.get('/gettransfers', playerController.findLatestTransfers);
  playerRouter.get('/getplayersbyname', playerController.findPlayersByName);
  playerRouter.get('/get/:id', function (request, response) {
     playerController.findPlayerByID(request, response, request.params.id);
  });
  playerRouter.get('/getoverview/:id', (request, response) => {
      playerController.findPlayerOverviewByID(request, response, request.params.id);
  });
  playerRouter.get('/getmarket/:id', (request, response) => {
     playerController.findPlayerMarketByID(request, response, request.params.id);
  });
  playerRouter.get('/gettransfers/:id', (request, response) => {
     playerController.findPlayerTransfersByID(request, response, request.params.id);
  });
  playerRouter.get('/getstats/:id', (request, response) => {
      playerController.findPlayerStatsByID(request, response, request.params.id);
  });

  module.exports = playerRouter;