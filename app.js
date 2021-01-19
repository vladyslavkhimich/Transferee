const express = require('express');
const bodyParser = require('body-parser');
const path = require('path');
const https = require('https');
const expressHbs = require('express-handlebars');
const {Sequelize, sequelize} = require('./models/others/sequelize');
const db = require('./models');


const AdminBro = require('admin-bro');
const AdminBroExpress = require('@admin-bro/express');
const AdminBroSequelize = require('@admin-bro/sequelize');
AdminBro.registerAdapter(AdminBroSequelize);

const adminBro = require('./admin');


const app = express();
//const databaseSequelize = require('./models/others/database_sequelize');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended:true}));
const router = AdminBroExpress.buildRouter(adminBro);
const playerRouter = require('./routes/playerRouter');
const imageRouter = require('./routes/imageRouter');
const ImageHelper = require('./helpers/ImageHelper');

const multer = require('multer');
let assign = multer.diskStorage({
    destination:function(req, file, cb) {
        const playerDir = './public/images/player/';
        const clubDir = './public/images/club/';
        const countryDir = './public/images/country/';
        if (file.fieldname === 'playerImage')
            cb(null, playerDir);
        else if (file.fieldname === 'clubImage')
            cb(null, clubDir);
        else if (file.fieldname === 'countryImage')
            cb(null, countryDir);
    },
    filename: function(req, file, cb) {
        cb(null, file.originalname);
    }
});

let upload = multer({storage: assign});

app.post('/imageup/player/upload', upload.single('playerImage'), function (request, response, next) {
    db.Player.findByPk(parseInt(request.body.playerID)).then(player => {
       if (player) {
           let newImageName = request.file.filename.substring(0, request.file.filename.indexOf('.'));
           player.update(
               {
                   Image: newImageName
               }
           ).then(result => {
               response.send('Updated');
           }).catch(err => {
               if (err)
                   response.send('Not updated');
           })
       }
    });
});
app.post('/imageup/club/upload', upload.single('clubImage'), function (request, response, next) {
   db.Club.findByPk(parseInt(request.body.clubID)).then(club => {
      if (club) {
          let newImageName = ImageHelper.getFileNameWithoutExtension(request.file.filename);
          club.update({
              Logo: newImageName
          }).then(result => {
              response.send('Updated');
          }).catch(err => {
              if (err)
                  response.send('Not updated');
          })
      }
   });
});
app.post('/imageup/country/upload', upload.single('countryImage'), function (request, response, next) {
    db.Country.findByPk(parseInt(request.body.countryID)).then(country => {
        if (country) {
            let newImageName = ImageHelper.getFileNameWithoutExtension(request.file.filename);
            country.update({
                Flag: newImageName
            }).then(result => {
                response.send('Updated');
            }).catch(err => {
                if (err)
                    response.send('Not updated');
            })
        }
    });
});
app.use(adminBro.options.rootPath, router);
app.use('/player', playerRouter);
let dir = path.join(__dirname, 'public');
app.use(express.static(dir));
app.engine('hbs', expressHbs(
    {
       extname: 'hbs',
    }
));
app.set('view engine', 'hbs');


app.use('/image', imageRouter);
app.get('/', function(request, response) {
   response.send('Express is working properly');
});

app.listen(3000);