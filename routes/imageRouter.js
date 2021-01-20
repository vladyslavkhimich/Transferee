const imageController = require('../controllers/imageController');

let imageRouter = require('express').Router();

imageRouter.get('/player/check', imageController.checkIfPlayerImageExists);
imageRouter.get('/player', imageController.getPlayerUploadImagePage);
imageRouter.get('/club/check', imageController.checkIfClubImageExists);
imageRouter.get('/club', imageController.getClubUploadImagePage);
imageRouter.get('/country/check', imageController.checkIfCountryImageExists);
imageRouter.get('/country', imageController.getCountryUploadImagePage);


module.exports = imageRouter;