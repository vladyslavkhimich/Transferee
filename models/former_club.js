module.exports = (sequelize, DataTypes) => {
    return sequelize.define('Former_Club', {
        Former_Club_ID: {
            type:DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Date_Of_Joining: DataTypes.DATEONLY,
        Date_Of_Departure: DataTypes.DATEONLY
    })
};