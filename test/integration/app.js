const request = require('supertest');
const app = require('../../app.js');

describe('GET /', function () {
  it('should return 200 OK', function (done) {
    request(app)
      .get('/')
      .expect(200, done);
  });
});

describe('GET /api/test', () => {
  it('should return 200 OK', (done) => {
    request(app)
      .get('/api')
      .expect(200, done);
  });
});

describe('GET /random-url', () => {
  it('should return 404', (done) => {
    request(app)
      .get('/random-url')
      .expect(404, done);
  });
});
