module.exports = (sequelize, DataTypes) => {
    let loan = sequelize.define('Loan', {
        Loan_ID: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            allowNull: false
        },
        Is_With_Transfer_Option: DataTypes.BOOLEAN,
        Start_Date: DataTypes.DATEONLY,
        Finish_Date: DataTypes.DATEONLY,
        Transfer_ID: {
            type: DataTypes.INTEGER,
            references: {
                model: 'Transfers',
                key: 'Transfer_ID'
            }
        }
    });
    loan.associate = (models) => {
        loan.belongsTo(models.Transfer, {foreignKey: 'Transfer_ID'});
    };
    return loan;
};