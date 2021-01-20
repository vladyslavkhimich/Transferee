const reportController = require('../controllers/reportController');

let reportRouter = require('express').Router();

reportRouter.get('/gettoprated', reportController.getTopRatedPlayersPage);
reportRouter.get('/gettopmarket', reportController.getTopMarketValuePlayersPage);

module.exports = reportRouter;