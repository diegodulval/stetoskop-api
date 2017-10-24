import * as HTTPStatus from "http-status";
import * as jwt from "jwt-simple";

import { app, expect, request } from "./config/helpers";

const model = require("../../server/models");

describe("Testes de Integração - Token", () => {
  "use strict";
  const config = require("../../server/config/env/config")();

  let token;

  const userDefault = {
    id: 1,
    name: "Dulval",
    email: "diego@email.com",
    password: "123"
  };

  beforeEach(done => {
    model.User
      .destroy({
        where: {}
      })
      .then(() => model.User.create(userDefault))
      .then(user => {
        token = jwt.encode({ id: user.id }, config.secret);
        done();
      });
  });

  describe("POST /token", () => {
    it("Deve receber um JWT", done => {
      const credentials = {
        email: userDefault.email,
        password: userDefault.password
      };
      request(app)
        .post("/token")
        .send(credentials)
        .end((error, res) => {
          expect(res.status).to.equal(HTTPStatus.OK);
          expect(res.body.token).to.equal(`${token}`);
          done(error);
        });
    });

    it("Não deve gerar Token", done => {
      const credentials = {
        email: "dito@email.com",
        password: "galocego"
      };
      request(app)
        .post("/token")
        .send(credentials)
        .end((error, res) => {
          expect(res.status).to.equal(HTTPStatus.UNAUTHORIZED);
          expect(res.body).to.empty; // tslint:disable-line
          done(error);
        });
    });
  });
});
