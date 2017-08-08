module.exports = (db, DataTypes) => {
  const Institute = db.define('Institute', {
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
  }, {
    tableName: 'dp_institute',
  });

  return Institute;
};
