module.exports = (sequelize, DataTypes) => {
  let country = sequelize.define('Country', {
     Country_ID: {
         type: DataTypes.INTEGER,
         autoIncrement: true,
         primaryKey: true,
         allowNull: false,
     },
     Name: DataTypes.STRING,
     Flag: DataTypes.STRING
  });
  country.associate = (models) => {
      country.hasMany(models.League, {foreignKey: 'Country_ID'});
      country.hasMany(models.Player, {foreignKey: 'Country_ID'});
  };
  return country;
};