module.exports = (sequelize, DataTypes) => {
  let typeOfCard = sequelize.define('Type_Of_Card', {
      Type_Of_Card_ID: {
          type: DataTypes.INTEGER,
          autoIncrement: true,
          primaryKey: true,
          allowNull: false
      },
      Name: DataTypes.STRING
  });
  typeOfCard.associate = (models) => {
      typeOfCard.hasMany(models.Match_Card, {foreignKey: 'Type_Of_Card_ID'});
  };
  return typeOfCard;
};