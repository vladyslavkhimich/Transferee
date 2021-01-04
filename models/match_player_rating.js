module.exports = (sequelize, DataTypes) => {
    return sequelize.define('Match_Player_Rating', {
        Match_Rating_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Match_Rating: DataTypes.FLOAT
    })
};