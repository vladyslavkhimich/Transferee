module.exports = (sequelize, DataTypes) => {
  return sequelize.define('Type_Of_Card', {
      Type_Of_Card_ID: {
          type: DataTypes.INTEGER,
          autoIncrement: true,
          primaryKey: true,
          allowNull: false
      },
      Type_Of_Card_Name: DataTypes.STRING
  })
};