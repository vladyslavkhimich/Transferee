module.exports = (sequelize, DataTypes) => {
    return sequelize.define('Goal', {
        Goal_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false,
        },
        Minute_Of_Goal: DataTypes.INTEGER,
        Is_Penalty: DataTypes.BOOLEAN,
        Is_Own_Goal: DataTypes.BOOLEAN
    })
};