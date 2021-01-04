module.exports = (sequelize, DataTypes) => {
    return sequelize.define('Tour', {
        Tour_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Tour_Number: DataTypes.INTEGER,
        Tour_Name: DataTypes.STRING
    })
};