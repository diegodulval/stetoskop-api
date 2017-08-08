/**
 * global requires
 */
/**
const secrets = require('../config/secrets');
const querystring = require('querystring');
const validator = require('validator');
const async = require('neo-async');
const request = require('request');
const _ = require('lodash');
 */

/**
 * GET /api
 * List of API examples.
 */
exports.getApiTeste = (req, res) => {
  const json = { teste: { 1: 'teste' } };
  res.send(json);
};
