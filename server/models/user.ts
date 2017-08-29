import * as bcrypt from 'bcryptjs';

import Image from './image';

export default function (sequelize, DataTypes) {

  const User = sequelize.define('User', {
    id: {
      type: DataTypes.INTEGER,
      primaryKey: true,
      autoIncrement: true,
    },
    name: {
      type: DataTypes.STRING,
      allowNull: false,
      validate: {
        notEmpty: true,
      },
    },
    email: {
      type: DataTypes.STRING,
      allowNull: false,
      validate: {
        notEmpty: true,
      },
    },
    password: {
      type: DataTypes.STRING,
      allowNull: false,
      validate: {
        notEmpty: true,
      },
    },
  }/* ,
  {
    classMethods: {
      associate: models => {
        User.hasMany(models.Image, { as: 'images'});
      },
    },
  } */
  );

  User.associate = (models) => User.hasMany(models.Image);

  User.beforeCreate((user) => {
    return hashPassword(user);
  });

  User.beforeUpdate((user) => {
    return hashPassword(user);
  });

  function hashPassword(user) {
    const salt = bcrypt.genSaltSync(10);
    user.set('password', bcrypt.hashSync(user.password, salt));
  }

  return User;
}
