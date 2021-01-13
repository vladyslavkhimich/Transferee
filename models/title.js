module.exports = (sequelize, DataTypes) => {
    let title = sequelize.define('Title', {
        Title_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Name: DataTypes.STRING,
        Title_Image: DataTypes.STRING
    });
    title.associate = (models) => {
        title.hasMany(models.Club_Title, {foreignKey: 'Title_ID'});
    };
    return title;
};