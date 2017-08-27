"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var helpers_1 = require("./config/helpers");
var service_1 = require("../../server/modules/User/service");
describe('Testes Unitarios do Controller', function () {
    /*
    describe('Método Create', () => {
      it('Deve criar um Usúario', () => {
        const newUser = {
          id: 1,
          name: 'NewUser',
          email: 'newuser@email.com',
          password: 'pinkfloyd',
        };
        const user = new User();
        return user.create(newUser)
          .then((data) => {
            expect(data.dataValues).to.have.all.keys(
              ['email', 'id', 'name', 'password', 'updatedAt', 'createdAt'],
            );
          });
      });
    });
  
      describe('Método GET Users', () => {
        it('Deve retornar uma lista com todos os Usúario', () => {
          const user = new User();
          return user.getAll().then((data) => {
            expect(data).to.be.an('array');
            expect(data[0]).to.have.all.keys(
              ['email', 'id', 'name', 'password'],
            );
          });
        });
      });
  
      describe('Método Update', () => {
        it('Deve atualizar um Usúario', () => {
          const updateUser = {
            name: 'UpdateUser',
            email: 'updateuser@email.com',
          };
          const user = new User();
          return user.update(1, updateUser).then((data) => {
            expect(data[0]).to.be.equal(1);
          });
        });
      });
       });
  
    describe('Método Delete', () => {
      it('Deve deletar um Usúario', () => {
        const user = new User();
        return user.delete(1).then((data) => {
          expect(data).to.be.equal(1);
        });
      });
    });
  */
    describe('Método getById', function () {
        it('Deve retornar um Usúario de acordo com o ID informado', function () {
            var user = new service_1.default();
            return user.getById(1).then(function (data) {
                helpers_1.expect(data).to.have.all.keys(['email', 'id', 'name', 'password']);
            });
        });
    });
    describe('Método getByEmail', function () {
        it('Deve retornar um Usúario de acordo com o ID informado', function () {
            var user = new service_1.default();
            return user.getByEmail('newuser@email.com').then(function (data) {
                helpers_1.expect(data).to.have.all.keys(['email', 'id', 'name', 'password']);
            });
        });
    });
});
