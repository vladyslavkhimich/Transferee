module.exports = (sequelize, DataTypes) => {
    let clubTitle = sequelize.define('Club_Title', {
        Club_Title_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Date_Of_Winning: DataTypes.DATEONLY,
        Title_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'Titles',
                key: 'Title_ID'
            }
        },
        Club_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'Clubs',
                key: 'Club_ID'
            }
        },
        League_Season_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'League_Seasons',
                key: 'League_Season_ID'
            }
        }
    });
    clubTitle.associate = (models) => {
        clubTitle.belongsTo(models.Club, {foreignKey: 'Club_ID'});
        clubTitle.belongsTo(models.Title, {foreignKey: 'Title_ID'});
        clubTitle.belongsTo(models.League_Season, {foreignKey: 'League_Season_ID'});
    };
    return clubTitle;
};