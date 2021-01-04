module.exports = (sequelize, DataTypes) => {
    return sequelize.define('Transfer', {
        Transfer_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Date_Of_Transfer: DataTypes.DATEONLY,
        Transfer_Price: DataTypes.FLOAT
    })
};