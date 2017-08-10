import * as bodyParser from 'body-parser';
import * as express from 'express';
import { Application } from 'express';
import * as morgan from 'morgan';

class Api {

  public express: Application;

  constructor() {
    this.express = express();
  }

  public middleware(): void {
    this.express.use(morgan('dev'));
    this.express.use(bodyParser.urlencoded({ extended: true }));
    this.express.use(bodyParser.json());
  }

}

export default new Api().express;
