module.exports = (sequelize, DataTypes) => {
    return sequelize.define('Player_Position', {
        Player_Position_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Position_Name: DataTypes.INTEGER,
        Full_Position_Name: DataTypes.STRING
    })
};