module.exports = (sequelize, DataTypes) => {
  return sequelize.define('Former_League', {
      Former_League_ID: {
          type: DataTypes.INTEGER,
          autoIncrement: true,
          primaryKey: true,
          allowNull: false
      }
  })
};