module.exports = (sequelize, DataTypes) => {
    return sequelize.define('Match_Position', {
        Match_Position_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        }
    })
};