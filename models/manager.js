module.exports = (sequelize, DataTypes) => {
    return sequelize.define('Manager', {
        Manager_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Full_Name: DataTypes.STRING
    })
};