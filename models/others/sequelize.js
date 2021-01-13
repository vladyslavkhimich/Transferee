const Sequelize = require('sequelize');
const sequelize = new Sequelize('Transferee_Database', 'root', 'mypassword123', {
    dialect: 'mysql',
    host: 'localhost',
    define: {
        timestamps: false
    }
});

module.exports = {
    Sequelize, sequelize
};