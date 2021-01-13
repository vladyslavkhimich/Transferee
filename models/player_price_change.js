module.exports = (sequelize, DataTypes) => {
    let playerPriceChange = sequelize.define('Player_Price_Change', {
        Player_Price_Change_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Change_Date: DataTypes.DATEONLY,
        Market_Price: DataTypes.FLOAT,
        Player_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'Players',
                key: 'Player_ID'
            }
        }
    });
    playerPriceChange.associate = (models) => {
        playerPriceChange.belongsTo(models.Player, {foreignKey: 'Player_ID'});
    };
    return playerPriceChange;
};