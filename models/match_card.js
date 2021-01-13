module.exports = (sequelize, DataTypes) => {
    let matchCard = sequelize.define('Match_Card', {
        Match_Card_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Minute_Of_Receiving: DataTypes.INTEGER,
        Match_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'Matches',
                key: 'Match_ID'
            }
        },
        Type_Of_Card_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'Type_Of_Cards',
                key: 'Type_Of_Card_ID'
            }
        },
        Player_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'Players',
                key: 'Player_ID'
            }
        }
    });
    matchCard.associate = (models) => {
        matchCard.belongsTo(models.Player, {foreignKey: 'Player_ID'});
        matchCard.belongsTo(models.Match, {foreignKey: 'Match_ID'});
        matchCard.belongsTo(models.Type_Of_Card, {foreignKey: 'Type_Of_Card_ID'});
    };
    return matchCard;
};