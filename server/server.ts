import * as http from 'http';
import Api from './api/api';
import { errorHandlerApi } from './api/errorHandlerApi';

const config = require('./config/env/config')();

const server = http.createServer(Api);

Api.use(errorHandlerApi);

server.listen(config.serverPort, () => console.log(`Server rodando na porta ${config.serverPort}`)); // tslint:disable-line
