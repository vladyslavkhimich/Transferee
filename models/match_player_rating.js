module.exports = (sequelize, DataTypes) => {
    let matchPlayerRating =  sequelize.define('Match_Player_Rating', {
        Match_Rating_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Match_Rating: DataTypes.FLOAT,
        Player_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'Players',
                key: 'Player_ID'
            }
        },
        Match_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'Matches',
                key: 'Match_ID'
            }
        }
    });
    matchPlayerRating.associate = (models) => {
        matchPlayerRating.belongsTo(models.Player, {foreignKey: 'Player_ID'});
        matchPlayerRating.belongsTo(models.Match, {foreignKey: 'Match_ID'});
    };
    return matchPlayerRating;
};