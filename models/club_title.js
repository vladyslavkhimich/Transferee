module.exports = (sequelize, DataTypes) => {
    return sequelize.define('Club_Title', {
        Club_Title_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Date_Of_Winning: DataTypes.DATEONLY
    })
};