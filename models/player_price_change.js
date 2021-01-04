module.exports = (sequelize, DataTypes) => {
    return sequelize.define('Player_Price_Change', {
        Player_Price_Change_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Change_Date: DataTypes.DATEONLY,
        Market_Price: DataTypes.FLOAT
    })
};