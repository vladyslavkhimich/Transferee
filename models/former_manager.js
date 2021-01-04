module.exports = (sequelize, DataTypes) => {
    return sequelize.define('Former_Manager', {
        Former_Manager_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Date_Of_Joining: DataTypes.DATEONLY,
        Date_Of_Departure: DataTypes.DATEONLY
    })
};