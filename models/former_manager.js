module.exports = (sequelize, DataTypes) => {
    return sequelize.define('Former_Manager', {
        Former_Manager_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Date_Of_Joining: DataTypes.DATEONLY,
        Date_Of_Departure: DataTypes.DATEONLY,
        Manager_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'Managers',
                key: 'Manager_ID'
            }
        },
        Club_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'Clubs',
                key: 'Club_ID'
            }
        }
    })
};