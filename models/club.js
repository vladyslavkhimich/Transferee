module.exports = (sequelize, DataTypes) => {
    let club = sequelize.define('Club', {
        Club_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Name: DataTypes.STRING,
        Logo: DataTypes.STRING
    });
    club.associate = (models) => {
        club.belongsToMany(models.League, {through: models.Former_League, foreignKey: 'Club_ID' });
        club.belongsToMany(models.Manager, {through: models.Former_Manager, foreignKey: 'Club_ID'});
        club.belongsToMany(models.Player, {through: models.Former_Club, foreignKey: 'Club_ID'});
        club.hasMany(models.Transfer, {foreignKey: 'Departure_Club_ID', sourceKey: 'Club_ID'});
        club.hasMany(models.Transfer, {foreignKey: 'Joining_Club_ID', sourceKey: 'Club_ID'});
        club.hasMany(models.Club_Title, {foreignKey: 'Club_ID'});
        club.hasMany(models.Main_Squad_Player, {foreignKey: 'Club_ID'});
        club.hasMany(models.Match, {foreignKey: 'Home_Club_ID', sourceKey: 'Club_ID'});
        club.hasMany(models.Match, {foreignKey: 'Guest_Club_ID', sourceKey: 'Club_ID'});
    };
    return club;
};