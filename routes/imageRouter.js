const imageController = require('../controllers/imageController');

let imageRouter = require('express').Router();

/*const multer = require('multer');
let assign = multer.diskStorage({
    destination:function(req, file, cb) {
        const playerDir = '../public/images/player/';
        const clubDir = '../public/images/club/';
        const countryDir = '../public/images/country/';
        if (file.fieldName === 'playerImage')
            cb(null, playerDir);
    },
    filename: function(req, file, cb) {
        cb(null, file.originalname);
    }
});

let upload = multer({storage: assign});*/

/*imageRouter.post('/player/upload',  function (request, response, next) {
    console.log(request.body.playerID);
});*/
imageRouter.get('/player/check', imageController.checkIfPlayerImageExists);
imageRouter.get('/player', imageController.getPlayerUploadImagePage);
imageRouter.get('/club/check', imageController.checkIfClubImageExists);
imageRouter.get('/club', imageController.getClubUploadImagePage);
imageRouter.get('/country/check', imageController.checkIfCountryImageExists);
imageRouter.get('/country', imageController.getCountryUploadImagePage);


module.exports = imageRouter;