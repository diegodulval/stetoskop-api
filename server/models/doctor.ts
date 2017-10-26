import User from "./user";

export default function (sequelize, DataTypes) {

  const Doctor = sequelize.define('Doctor', {
    id: {
      field: 'user_id',
      type: DataTypes.BIGINT,
      allowNull: false,
      primaryKey: true
    },
    profession: {
      type: DataTypes.STRING,
      allowNull: false
    },
    specialty: {
      type: DataTypes.STRING,
      allowNull: false,
      validate: {
        notEmpty: true
      }
    },
    crm: {
      type: DataTypes.STRING,
      allowNull: false,
      validate: {
        notEmpty: true
      }
    },
    telephone: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    plan: {
      type: DataTypes.STRING,
      allowNull: false,
      validate: {
        notEmpty: true
      }
    },
  });

  Doctor.associate = (models) => Doctor.belongsTo(models.User, { foreignKey: 'id', targetKey: 'id' });

  return Doctor;
}
