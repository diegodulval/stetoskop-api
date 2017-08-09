const db = require('./../db');

const express = require('express');

const router = express.Router();

router.get('/', (req, res) => {
  res.render('index', { title: 'Doctor Prescription' });
});

// Generic GET handler;
const GET = (url, handler) => {
  router.get(url, (req, res) => {
    handler(req)
      .then((data) => {
        res.json({
          success: true,
          data,
        });
      })
      .catch((error) => {
        res.json({
          success: false,
          error: error.message || error,
        });
      });
  });
};

// create table Users:
GET('/users/create', () => db.users.create());

// add some initial records:
GET('/users/init', () => db.users.init());

// remove all records from the table:
GET('/users/empty', () => db.users.empty());

// drop the table:
GET('/users/drop', () => db.users.drop());

// add a new user, if it doesn't exist yet, and return the object:
GET('/users/add/:name', (req) => {
  const retorno = db.task('add-user', t => t.users.findByName(req.params.name)
    .then(user => (user) || t.users.add(req.params.name)));
  return retorno;
});

// find a user by id:
GET('/users/find/:id', req => db.users.findById(req.params.id));

// remove a user by id:
GET('/users/remove/:id', req => db.users.remove(req.params.id));

// get all users:
GET('/users/all', () => db.users.all());

// count all users:
GET('/users/total', () => db.users.total());

// create table Products:
GET('/products/create', () => db.products.create());

// drop the table:
GET('/products/drop', () => db.products.drop());

// remove all products:
GET('/products/empty', () => db.products.empty());

// find a product by user id + product name id:
GET('/products/find/:userId/:name', req => db.products.find(req.params));

// remove a product by id:
GET('/products/remove/:id', req => db.products.remove(req.params.id));

// get all products:
GET('/products/all', () => db.products.all());

// count all products:
GET('/products/total', () => db.products.total());

module.exports = router;
