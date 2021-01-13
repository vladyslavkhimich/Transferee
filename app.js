const express = require('express');
const bodyParser = require('body-parser');

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


app.use(adminBro.options.rootPath, router);
app.use('/player', playerRouter);
app.get('/', function(request, response) {
   response.send('Express is working properly');
});

app.listen(3000);