"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var HTTPStatus = require("http-status");
var jwt = require("jwt-simple");
var helpers_1 = require("./config/helpers");
var model = require("../../server/models");
describe("Testes de Integração - Token", function () {
    "use strict";
    var config = require("../../server/config/env/config")();
    var token;
    var userDefault = {
        id: 1,
        name: "Dulval",
        email: "diego@email.com",
        password: "123"
    };
    beforeEach(function (done) {
        model.User
            .destroy({
            where: {}
        })
            .then(function () { return model.User.create(userDefault); })
            .then(function (user) {
            token = jwt.encode({ id: user.id }, config.secret);
            done();
        });
    });
    describe("POST /token", function () {
        it("Deve receber um JWT", function (done) {
            var credentials = {
                email: userDefault.email,
                password: userDefault.password
            };
            helpers_1.request(helpers_1.app)
                .post("/token")
                .send(credentials)
                .end(function (error, res) {
                helpers_1.expect(res.status).to.equal(HTTPStatus.OK);
                helpers_1.expect(res.body.token).to.equal("" + token);
                done(error);
            });
        });
        it("Não deve gerar Token", function (done) {
            var credentials = {
                email: "dito@email.com",
                password: "galocego"
            };
            helpers_1.request(helpers_1.app)
                .post("/token")
                .send(credentials)
                .end(function (error, res) {
                helpers_1.expect(res.status).to.equal(HTTPStatus.UNAUTHORIZED);
                helpers_1.expect(res.body).to.empty; // tslint:disable-line
                done(error);
            });
        });
    });
});
