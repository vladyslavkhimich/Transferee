module.exports = (sequelize, DataTypes) => {
    let tour = sequelize.define('Tour', {
        Tour_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Tour_Number: DataTypes.INTEGER,
        Name: DataTypes.STRING,
        League_Season_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'League_Seasons',
                key: 'League_Season_ID'
            }
        }
    });
    tour.associate = (models) => {
        tour.belongsTo(models.League_Season, {foreignKey: 'League_Season_ID'});
        tour.hasMany(models.Match, {foreignKey: 'Tour_ID'});
    };
    return tour;
};