module.exports = (sequelize, DataTypes) => {
    let transfer =  sequelize.define('Transfer', {
        Transfer_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Date_Of_Transfer: DataTypes.DATEONLY,
        Transfer_Price: DataTypes.FLOAT,
        Market_Value: DataTypes.FLOAT,
        Player_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'Players',
                key: 'Player_ID'
            }
        },
        Departure_Club_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'Clubs',
                key: 'Club_ID'
            },
        },
        Joining_Club_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'Clubs',
                key: 'Club_ID'
            }
        }
    });
    transfer.associate = (models) => {
        transfer.belongsTo(models.Club, {foreignKey: 'Departure_Club_ID', as: 'Departure_Club', targetKey: 'Club_ID'});
        transfer.belongsTo(models.Club, {foreignKey: 'Joining_Club_ID', as: 'Joining_Club', targetKey: 'Club_ID'});
        transfer.belongsTo(models.Player, {foreignKey: 'Player_ID'});
        transfer.hasOne(models.Loan, {foreignKey: 'Transfer_ID'});
    };
    return transfer;
};