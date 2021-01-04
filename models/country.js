module.exports = (sequelize, DataTypes) => {
  return sequelize.define('Country', {
     Country_ID: {
         type: DataTypes.INTEGER,
         autoIncrement: true,
         primaryKey: true,
         allowNull: false,
     },
     Name: DataTypes.STRING,
     Flag: DataTypes.STRING
  });
};