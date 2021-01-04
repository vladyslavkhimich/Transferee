module.exports = (sequelize, DataTypes) => {
    return sequelize.define('Match', {
        Match_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Match_Date: DataTypes.DATEONLY,
        Is_With_Added_Time: DataTypes.BOOLEAN
    })
};