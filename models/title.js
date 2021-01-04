module.exports = (sequelize, DataTypes) => {
    return sequelize.define('Title', {
        Title_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Title_Name: DataTypes.STRING,
        Title_Image: DataTypes.STRING
    })
};