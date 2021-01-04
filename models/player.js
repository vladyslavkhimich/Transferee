module.exports = (sequelize, DataTypes) => {
    return sequelize.define('Player', {
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
        Preferred_Foot: DataTypes.BOOLEAN
    })
};