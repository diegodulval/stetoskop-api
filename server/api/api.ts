import * as bodyParser from 'body-parser';
import * as cors from 'cors';
import * as express from 'express';
import { Application } from 'express';
import * as morgan from 'morgan';
import * as path from 'path';

import Auth from '../auth';
import Handlers from './responses/handlers';
import Routes from './routes/routes';

class Api {

  public express: Application;
  public config;

  constructor() {
    this.config = require("../../server/config/env/config")();
    this.express = express();
    this.middleware();
  }

  public middleware(): void {
    this.express.use(morgan('dev'));
    this.express.use(bodyParser.urlencoded({ extended: true }));
    this.express.use(bodyParser.json());
    this.express.use(express.static(this.config.uploadPath));
    this.express.use(cors());
    this.express.use(Handlers.errorHandlerApi);
    this.express.use(Auth.config().initialize());
    this.router(this.express, Auth);
  }

  public router(app: Application, auth: any): void {
    Routes.initRoutes(app, auth);
  }

}

export default new Api().express;
