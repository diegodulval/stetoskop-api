/* tslint:disable */
import * as path from 'path';

module.exports = {
  env: 'production',
  db: process.env.DATABASE_NAME,
  dialect: 'postgres',
  username: 'dulval-182',
  password: process.env.DATABASE_PASS,
  host: 'localhost',
  serverPort: process.env.PORT,
  pgPort: 5432,
  dbURL: process.env.DATABASE_URL,
  secret: 'S3cr3t',
  uploadPath: path.join(__dirname, '..', '..', '..', 'upload'),
};
