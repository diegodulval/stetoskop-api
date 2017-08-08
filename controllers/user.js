const UserRepo = require('../repositories/UserRepository.js');

exports.postUser = (req, res) => {
  const errors = req.validationErrors();

  if (errors) {
    req.flash('errors', errors);
    res.send({ msg: errors });
  }

  UserRepo.createUser(req.body.user)
    .then(() => {
      res.send({ tafarel: 'vai batir boku' });
    })
    .catch((err) => {
      res.send({ error: err });
    });
};
