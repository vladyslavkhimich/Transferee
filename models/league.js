module.exports = (sequelize, DataTypes) => {
  return sequelize.define('League', {
      League_ID: {
          type: DataTypes.INTEGER,
          autoIncrement:true,
          primaryKey: true,
          allowNull: false
      },
      Name: DataTypes.STRING,
      Number_Of_Teams: DataTypes.INTEGER
  })
};