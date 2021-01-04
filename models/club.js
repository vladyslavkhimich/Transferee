module.exports = (sequelize, DataTypes) => {
    return sequelize.define('Club', {
        Club_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Name: DataTypes.STRING,
        Logo: DataTypes.STRING
    });
};