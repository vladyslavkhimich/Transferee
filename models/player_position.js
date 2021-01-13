module.exports = (sequelize, DataTypes) => {
    let playerPosition = sequelize.define('Player_Position', {
        Player_Position_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Name: DataTypes.STRING,
        Full_Position_Name: DataTypes.STRING,
        General_Position_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'General_Positions',
                key: 'General_Position_ID'
            }
        }
    });
    playerPosition.associate = (models) => {
        playerPosition.belongsTo(models.General_Position, {foreignKey: 'General_Position_ID'});
        playerPosition.hasMany(models.Match_Position, {foreignKey: 'Player_Position_ID'});
    };
    return playerPosition;
};