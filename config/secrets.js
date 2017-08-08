module.exports = {

  sessionSecret: process.env.SESSION_SECRET || 'Your Session Secret goes here',

  postgres: {},
  sessionTable: 'session',
  googleAnalyticsCode: process.env.GOOGLE_ANALYTICS_CODE || null,
  sendgrid: {
    api_key: process.env.SENDGRID_APIKEY || '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',
  },

};

/**
 * constructing Postgres connection string
 */
if (process.env.NODE_ENV === 'test-travis') {
  module.exports.postgres = 'postgres://postgres@127.0.0.1/test_travis_ci';
} else if (process.env.NODE_ENV === 'test') {
  module.exports.postgres = 'postgres://postgres:postgres@127.0.0.1/test';
} else {
  module.exports.postgres = process.env.DATABASE_URL || 'postgres://postgres:postgres@127.0.0.1/prod';
}
