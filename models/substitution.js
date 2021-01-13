module.exports = (sequelize, DataTypes) => {
  let substitution = sequelize.define('Substitution', {
      Substitution_ID: {
          type: DataTypes.INTEGER,
          autoIncrement: true,
          primaryKey: true,
          allowNull: false
      },
      Minute_Of_Substitution: DataTypes.INTEGER,
      Substituted_Player_ID: {
          type: DataTypes.INTEGER,
          references: {
              model: 'Players',
              key: 'Player_ID'
          }
      },
      Substitute_Player_ID: {
          type: DataTypes.INTEGER,
          references: {
              model: 'Players',
              key: 'Player_ID'
          }
      },
      Match_ID: {
          type: DataTypes.INTEGER,
          references: {
              model: 'Matches',
              key: 'Match_ID'
          }
      }
  });
  substitution.associate = (models) => {
      substitution.belongsTo(models.Player, {foreignKey: 'Substituted_Player_ID', as: 'Substituted_Player', targetKey: 'Player_ID'});
      substitution.belongsTo(models.Player, {foreignKey: 'Substitute_Player_ID', as: 'Substitute_Player', targetKey: 'Player_ID'});
      substitution.belongsTo(models.Match, {foreignKey: 'Match_ID'});
  };
  return substitution;
};