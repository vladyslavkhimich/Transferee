module.exports = (sequelize, DataTypes) => {
    return sequelize.define('Season', {
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
};