module.exports = (sequelize, DataTypes) => {
    return sequelize.define('Player_Contract', {
        Player_Contract_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Start_Date: DataTypes.DATEONLY,
        Finish_Date: DataTypes.DATEONLY
    })
};