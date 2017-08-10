import * as bodyParser from 'body-parser';
import * as express from 'express';
import { Application } from 'express';
import * as morgan from 'morgan';

import Routes from './routes/routes';

class Api {

  public express: Application;

  constructor() {
    this.express = express();
    this.middleware();
  }

  private middleware(): void {
    this.express.use(morgan('dev'));
    this.express.use(bodyParser.urlencoded({ extended: true }));
    this.express.use(bodyParser.json());
    this.router(this.express);
  }

  private router(app: Application): void {
    new Routes(app); //tslint:disable-line
  }

}

export default new Api().express;
