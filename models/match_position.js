module.exports = (sequelize, DataTypes) => {
    let matchPosition = sequelize.define('Match_Position', {
        Match_Position_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Player_Position_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'Player_Positions',
                key: 'Player_Position_ID'
            }
        },
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
    matchPosition.associate = (models) => {
        matchPosition.belongsTo(models.Player, {foreignKey: 'Player_ID'});
        matchPosition.belongsTo(models.Match, {foreignKey: 'Match_ID'});
        matchPosition.belongsTo(models.Player_Position, {foreignKey: 'Player_Position_ID'});
    };
    return matchPosition;
};