import * as http from 'http';

const server = http.createServer();

server.listen(3000, () => console.log('Server rodando na porta 3000')); // tslint:disable-line
