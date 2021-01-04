module.exports = (sequelize, DataTypes) => {
    return sequelize.define('General_Position', {
        General_Position_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Position_Name: DataTypes.STRING
    })
};
