const fs = require('fs');
const path = require('path');
const Sequelize = require('sequelize');
const _ = require('lodash');

const db = {};
const config = require('../../config/secrets');

const sequelize = new Sequelize(config.postgres, { maxConcurrentQueries: 100 });

fs
  .readdirSync(__dirname)
  .filter(file => (file.indexOf('.') !== 0) && (file !== 'index.js'))
  .forEach((file) => {
    const model = sequelize.import(path.join(__dirname, file));
    db[model.name] = model;
  });

Object.keys(db).forEach((modelName) => {
  if ('associate' in db[modelName]) {
    db[modelName].associate(db);
  }
});

module.exports = _.extend({
  sequelize,
  Sequelize,
}, db);
