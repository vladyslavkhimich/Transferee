module.exports = (sequelize, DataTypes) => {
    return sequelize.define('Match_Card', {
        Match_Card_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Minute_Of_Receiving: DataTypes.INTEGER
    })
};