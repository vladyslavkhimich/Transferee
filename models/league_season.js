module.exports = (sequelize, DataTypes) => {
  let leagueSeason = sequelize.define('League_Season', {
      League_Season_ID: {
          type: DataTypes.INTEGER,
          autoIncrement: true,
          primaryKey: true,
          allowNull: false
      },
      Start_Date: DataTypes.DATEONLY,
      Finish_Date: DataTypes.DATEONLY,
      Tour_Count: DataTypes.INTEGER,
      League_ID: {
          type: DataTypes.INTEGER,
          references: {
              model: 'Leagues',
              key: 'League_ID'
          }
      },
      Season_ID: {
          type: DataTypes.INTEGER,
          references: {
              model: 'Seasons',
              key: 'Season_ID'
          }
      }
  });
  leagueSeason.associate = (models) => {
      leagueSeason.belongsTo(models.League, {foreignKey: 'League_ID'});
      leagueSeason.belongsTo(models.Season, {foreignKey: 'Season_ID'});
      leagueSeason.hasMany(models.Tour, {foreignKey: 'League_Season_ID'});
  };
  return leagueSeason;
};