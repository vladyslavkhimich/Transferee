module.exports = (sequelize, DataTypes) => {
    let goal = sequelize.define('Goal', {
        Goal_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false,
        },
        Minute_Of_Goal: DataTypes.INTEGER,
        Is_Penalty: DataTypes.BOOLEAN,
        Is_Own_Goal: DataTypes.BOOLEAN,
        Goalscorer_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'Players',
                key: 'Player_ID'
            }
        },
        Assistant_ID: {
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
    goal.associate = (models) => {
        goal.belongsTo(models.Player, {foreignKey: 'Goalscorer_ID', as: 'Goalscorer', targetKey: 'Player_ID'});
        goal.belongsTo(models.Player, {foreignKey: 'Assistant_ID', as: 'Assistant', targetKey: 'Player_ID'});
        goal.belongsTo(models.Match, {foreignKey: 'Match_ID'});
    };
    return goal;
};