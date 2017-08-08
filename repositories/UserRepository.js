var db = require('../models/sequelize');

const repo = {};

repo.getUserById = id => db.User.findById(id);

repo.createUser = function (user) {
  return db.User.count({ where: { email: user.email } })
    .then((c) => {
      if (c > 0) throw 'Account with that email address already exists.';
      const dbUser = db.User.build(user);
      return dbUser.save();
    });
};

repo.changeProfileData = function (userId, reqBody) {
  return db.User.findById(userId)
    .then(function (user) {
      user.email = reqBody.email || '';
      user.profile.name = reqBody.name || '';
      user.profile.gender = reqBody.gender || '';
      user.profile.location = reqBody.location || '';
      user.profile.website = reqBody.website || '';
      user.set('profile', user.profile);

      if (user.changed('email')) {
        return db.User.count({ where: { email: user.email } })
          .then(function (c) {
            if (c > 0)
              throw 'Cannot change e-mail address, because address ' + user.email + ' already exists';

            return user.save();
          });
      }
      return user.save();
    });
};

repo.removeUserById = (userId) => db.User.destroy({ where: { id: userId } });

repo.changeUserPassword = function (userId, newPassword) {
  return db.User.findById(userId)
    .then(function (user) {
      if (!user)
        throw 'Account not found';

      user.password = newPassword;

      return user.save();
    });
};

module.exports = repo;
