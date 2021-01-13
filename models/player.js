module.exports = (sequelize, DataTypes) => {
     let player =  sequelize.define('Player', {
        Player_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Name: DataTypes.STRING,
        Pseudonim: DataTypes.STRING,
        Birth_Date: DataTypes.DATEONLY,
        Height: DataTypes.INTEGER,
        Age: DataTypes.INTEGER,
        Image: DataTypes.STRING,
        Shirt_Number: DataTypes.INTEGER,
        Preferred_Foot: DataTypes.BOOLEAN,
        Country_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'Countries',
                key: 'Country_ID'
            }
        }
    });
     player.associate = (models) => {
         player.belongsToMany(models.Club, {through: models.Former_Club, foreignKey: 'Player_ID'});
         player.hasMany(models.Transfer, {foreignKey: 'Player_ID'});
         player.hasMany(models.Player_Contract, {foreignKey: 'Player_ID'});
         player.hasMany(models.Player_Price_Change, {foreignKey: 'Player_ID'});
         player.hasMany(models.Match_Position, {foreignKey: 'Player_ID'});
         player.hasMany(models.Goal, {foreignKey: 'Goalscorer_ID', sourceKey: 'Player_ID'});
         player.hasMany(models.Goal, {foreignKey: 'Assistant_ID', sourceKey: 'Player_ID'});
         player.hasMany(models.Match_Card, {foreignKey: 'Player_ID'});
         player.hasMany(models.Main_Squad_Player, {foreignKey: 'Player_ID', sourceKey: 'Player_ID'});
         player.hasMany(models.Match_Player_Rating, {foreignKey: 'Player_ID', sourceKey: 'Player_ID'});
         player.hasMany(models.Substitution, {foreignKey: 'Substituted_Player_ID', sourceKey: 'Player_ID'});
         player.hasMany(models.Substitution, {foreignKey: 'Substitute_Player_ID', sourceKey: 'Player_ID'});
         player.belongsTo(models.Country, {foreignKey: 'Country_ID'});
     };
    return player;
};