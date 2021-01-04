module.exports = (sequelize, DataTypes) => {
    return sequelize.define('Loan', {
        Loan_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Is_With_Transfer_Option: DataTypes.BOOLEAN,
        Start_Date: DataTypes.DATEONLY,
        Finish_Date: DataTypes.DATEONLY
    })
};