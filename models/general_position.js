module.exports = (sequelize, DataTypes) => {
    let generalPosition =  sequelize.define('General_Position', {
        General_Position_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Name: DataTypes.STRING
    });
    generalPosition.associate = (models) => {
        generalPosition.hasMany(models.Player_Position, {foreignKey: 'General_Position_ID'});
    };
    return generalPosition;
};
