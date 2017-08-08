module.exports = (db, DataTypes) => {
  const Doctor = db.define('Doctor', {
    user_id: {
      type: DataTypes.INTEGER,
      allowNull: false,
      primaryKey: true,
      references: {
        model: 'dp_user',
        key: 'id',
      },
      onUpdate: 'cascade',
      onDelete: 'cascade',
    },
    specialty: { type: DataTypes.STRING },
    crm: { type: DataTypes.STRING, allowNull: false },
    telephone: { type: DataTypes.STRING },
    is_premium: { type: DataTypes.BOOLEAN },
    address: { type: DataTypes.STRING },
    neighborhood: { type: DataTypes.STRING },
    city: { type: DataTypes.STRING },
  }, {
    tableName: 'dp_doctor',
  });

  return Doctor;
};
