const bcrypt = require('bcrypt-nodejs');


const beforeSaveHook = function generateHash(user, options, fn) {
  if (user.changed('password')) {
    this.encryptPassword(user.password, (hash, err) => {
      user.password = hash;
      fn(null, user);
    });
    return;
  }
  fn(null, user);
};

module.exports = (db, DataTypes) => {
  const User = db.define('User', {
    id: {
      type: DataTypes.INTEGER,
      autoIncrement: true,
      allowNull: false,
      primaryKey: true,
    },
    name: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    email: {
      type: DataTypes.STRING,
      unique: true,
      allowNull: false,
      isEmail: true,
    },
    password: DataTypes.STRING,
  },
  {
    tableName: 'dp_user',
    classMethods: {
      encryptPassword: (password, cb) => {
        if (!password) {
          cb('', null);
          return;
        }

        bcrypt.genSalt(10, (err, salt) => {
          if (err) { cb(null, err); return; }
          bcrypt.hash(password, salt, null, (hErr, hash) => {
            if (hErr) { cb(null, hErr); return; }
            cb(hash, null);
          });
        });
      },
      findUser: (email, password, cb) => {
        User.findOne({
          where: { email },
        })
          .then((user) => {
            if (user == null || user.password == null || user.password.length === 0) {
              cb('User / Password combination is not correct', null);
              return;
            }
            bcrypt.compare(password, user.password, (err, res) => {
              if (res) cb(null, user);
              else cb(err, null);
            });
          })
          .catch((serr) => { cb(serr, null); });
      },
    },
    hooks: {
      beforeUpdate: beforeSaveHook,
      beforeCreate: beforeSaveHook,
    },
  });

  return User;
};
