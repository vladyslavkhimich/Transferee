module.exports = (sequelize, DataTypes) => {
    let mainSquadPlayer =  sequelize.define('Main_Squad_Player', {
        Main_Squad_Player_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Match_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'Matches',
                key: 'Match_ID'
            }
        },
        Club_ID: {
          type: DataTypes.INTEGER,
          references: {
              model: 'Clubs',
              key: 'Club_ID'
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
    mainSquadPlayer.associate = (models) => {
        mainSquadPlayer.belongsTo(models.Player, {foreignKey: 'Player_ID'});
        mainSquadPlayer.belongsTo(models.Club, {foreignKey: 'Club_ID'});
        mainSquadPlayer.belongsTo(models.Match, {foreignKey: 'Match_ID'});
    };
    return mainSquadPlayer;
};