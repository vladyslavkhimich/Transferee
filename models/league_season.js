module.exports = (sequelize, DataTypes) => {
  return sequelize.define('League_Season', {
      League_Season_ID: {
          type: DataTypes.INTEGER,
          autoIncrement: true,
          primaryKey: true,
          allowNull: false
      },
      Start_Date: DataTypes.DATEONLY,
      Finish_Date: DataTypes.DATEONLY,
      Tour_Count: DataTypes.INTEGER
  })
};