module.exports = (sequelize, DataTypes) => {
  return sequelize.define('Former_League', {
      Former_League_ID: {
          type: DataTypes.INTEGER,
          autoIncrement: true,
          primaryKey: true,
          allowNull: false
      },
      League_ID: {
          type: DataTypes.INTEGER,
          references: {
              model: 'Leagues',
              key: 'League_ID'
          }
      },
      Club_ID: {
          type: DataTypes.INTEGER,
          references: {
              model: 'Clubs',
              key: 'Club_ID'
          }
      }
  })
};