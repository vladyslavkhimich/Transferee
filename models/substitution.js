module.exports = (sequelize, DataTypes) => {
  return sequelize.define('Substitution', {
      Substitution_ID: {
          type: DataTypes.INTEGER,
          autoIncrement: true,
          primaryKey: true,
          allowNull: false
      },
      Minute_Of_Substitution: DataTypes.INTEGER
  })
};