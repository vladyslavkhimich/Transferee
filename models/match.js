module.exports = (sequelize, DataTypes) => {
    let match = sequelize.define('Match', {
        Match_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Match_Date: DataTypes.DATEONLY,
        Is_With_Added_Time: DataTypes.BOOLEAN,
        Tour_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'Tours',
                key: 'Tour_ID'
            }
        },
        Home_Club_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'Clubs',
                key: 'Club_ID'
            }
        },
        Guest_Club_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'Clubs',
                key: 'Club_ID'
            }
        }
    });
    match.associate = (models) => {
        match.belongsTo(models.Club, {foreignKey: 'Home_Club_ID', as: 'Home_Club', targetKey: 'Club_ID'});
        match.belongsTo(models.Club, {foreignKey: 'Guest_Club_ID', as: 'Guest_Club', targetKey: 'Club_ID'});
        match.belongsTo(models.Tour, {foreignKey: 'Tour_ID'});
        match.hasMany(models.Main_Squad_Player, {foreignKey: 'Match_ID'});
        match.hasMany(models.Match_Player_Rating, {foreignKey: 'Match_ID'});
        match.hasMany(models.Substitution, {foreignKey: 'Match_ID'});
        match.hasMany(models.Match_Card, {foreignKey: 'Match_ID'});
        match.hasMany(models.Goal, {foreignKey: 'Match_ID'});
        match.hasMany(models.Match_Position, {foreignKey: 'Match_ID'});
    };
    return match;
};