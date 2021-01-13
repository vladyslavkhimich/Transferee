module.exports = (sequelize, DataTypes) => {
    let playerContract =  sequelize.define('Player_Contract', {
        Player_Contract_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Start_Date: DataTypes.DATEONLY,
        Finish_Date: DataTypes.DATEONLY,
        Player_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'Players',
                key: 'Player_ID'
            }
        }
    });
    playerContract.associate = (models) => {
        playerContract.belongsTo(models.Player, {foreignKey: 'Player_ID'});
    };
    return playerContract;
};