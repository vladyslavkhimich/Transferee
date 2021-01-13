module.exports = (sequelize, DataTypes) => {
  let league =  sequelize.define('League', {
      League_ID: {
          type: DataTypes.INTEGER,
          autoIncrement:true,
          primaryKey: true,
          allowNull: false
      },
      Name: DataTypes.STRING,
      Number_Of_Teams: DataTypes.INTEGER,
      Country_ID: {
          type: DataTypes.INTEGER,
          references: {
              model: 'Countries',
              key: 'Country_ID'
          }

      }
  });
  league.associate = (models) => {
      league.belongsToMany(models.Club, {through: models.Former_League, foreignKey: 'League_ID'});
      league.belongsTo(models.Country, {foreignKey: 'Country_ID'});
      league.hasMany(models.League_Season, {foreignKey: 'League_ID'});
  };
  return league;
};