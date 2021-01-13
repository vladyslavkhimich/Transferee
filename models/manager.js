module.exports = (sequelize, DataTypes) => {
    let manager = sequelize.define('Manager', {
        Manager_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Name: DataTypes.STRING
    });
    manager.associate = (models) => {
        manager.belongsToMany(models.Club, {through: models.Former_Manager, foreignKey: 'Manager_ID'});
    };
    return manager;
};