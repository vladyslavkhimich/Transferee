module.exports = (sequelize, DataTypes) => {
    let season = sequelize.define('Season', {
        Season_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Season_Number: {
            type: DataTypes.STRING(5)
        }
    });
    season.associate = (models) => {
        season.hasMany(models.League_Season, {foreignKey: 'Season_ID'});
    };
    return season;
};