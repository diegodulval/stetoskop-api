/**
 * Module dependencies.
 */
const express = require('express');
require('dotenv').config();
const cookieParser = require('cookie-parser');
const compress = require('compression');
const favicon = require('serve-favicon');
const session = require('express-session');
const PgSession = require('connect-pg-simple')(session);
const bodyParser = require('body-parser');
const logger = require('morgan');
const errorHandler = require('errorhandler');
const lusca = require('lusca');
const methodOverride = require('method-override');
const multer = require('multer');
const ejsEngine = require('ejs-mate');
const Promise = require('bluebird');

const flash = require('express-flash');
const path = require('path');
const expressValidator = require('express-validator');
const connectAssets = require('connect-assets');

/**
 * Controllers (route handlers).
 */
const homeController = require('./controllers/home');
const apiController = require('./controllers/api');
const userController = require('./controllers/user');
/**
 * API keys .
 */
const secrets = require('./config/secrets');

/**
 * Create Express server.
 */
const app = express();

/**
 * Express configuration.
 */
app.set('port', process.env.PORT || 3000);
app.engine('ejs', ejsEngine);
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');
app.enable('trust proxy');
app.use(compress());
app.use(connectAssets({
  paths: [path.join(__dirname, 'public/css'), path.join(__dirname, 'public/js')],
}));
app.use(logger('dev'));
app.use(favicon(path.join(__dirname, 'public/favicon.png')));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(multer({ dest: path.join(__dirname, 'uploads') }).single());
app.use(expressValidator());
app.use(methodOverride());
app.use(cookieParser());

Promise.longStackTraces();

const db = require('./models/sequelize');

/**
* PostgreSQL Store
*/
app.use(session({
  store: new PgSession({
    conString: secrets.postgres,
    tableName: secrets.sessionTable,
  }),
  secret: secrets.sessionSecret,
  saveUninitialized: true,
  resave: false,
  cookie: {
    maxAge: 30 * 24 * 60 * 60 * 1000, // 30 days
    httpOnly: true,
    /**
     * secure: true // only when on HTTPS
     */
  },
}));

app.use(flash());
app.use((req, res, next) => {
  res.locals.user = req.user;
  res.locals.gaCode = secrets.googleAnalyticsCode;
  next();
});
app.use((req, res, next) => {
  if (/api/i.test(req.path)) req.session.returnTo = req.path;
  next();
});
app.use(express.static(path.join(__dirname, 'public'), { maxAge: 31557600000 }));


/**
 * Primary app routes.
 */
app.get('/', homeController.index);

/**
 * API routes.
 */
app.get('/api/teste', apiController.getApiTeste);
app.post('/api/user', userController.postUser);
/**
 * Error Handler.
 */
app.use(errorHandler());

/**
 * Start Express server.
 */
db
  .sequelize
  .sync({ force: false })
  .then(() => {
    app.listen(app.get('port'), () => {
      console.log('Express server listening on port %d in %s mode', app.get('port'), app.get('env'));
    });
  });

module.exports = app;
