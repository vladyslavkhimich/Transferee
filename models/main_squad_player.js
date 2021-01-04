module.exports = (sequelize, DataTypes) => {
    return sequelize.define('Main_Squad_Player', {
        Main_Squad_Player_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        }
    })
};