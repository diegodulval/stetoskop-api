import { expect, testDouble } from "./config/helpers";

import Image from "../../server/modules/Image/service";
import User from "../../server/modules/User/service";

const model = require("../../server/models");

describe("Testes Unitarios do Image Service", () => {
  const defaultUser = {
    id: 500,
    name: "DefaultUser",
    email: "default@email.com",
    password: "pinkfloyd"
  };

  const defaulImage = {
    id: 2,
    name: "DefaultImage",
    url: "http://email.com/img/21",
    UserId: defaultUser.id,
  };

  beforeEach(done => {
    model.User
      .destroy({
        where: {}
      })
      .then(() => {
        model.User.create(defaultUser).then(() => {
          console.log("Default User created"); //tslint:disable-line
          model.Image
            .destroy({
              where: {}
            })
            .then(() => {
              model.Image.create(defaulImage).then(() => {
                console.log("Default Image created"); //tslint:disable-line
                done();
              });
            });
        });
      });
  });

  describe("Método Create", () => {
    it("Deve criar um nova Image", () => {
      const newImage = {
        id: 1,
        name: "New Image",
        url: "http://pikachu.com/458",
        UserId: defaultUser.id,
      };
      return Image.create(newImage).then(data => {
        expect(data.dataValues).to.have.all.keys([
          "id",
          "name",
          "url",
          "UserId",
          "updatedAt",
          "createdAt"
        ]);
      });
    });
  });

  describe("Método Update", () => {
    it("Deve atualizar uma Imagem", () => {
      const updateImage = {
        name: "Update Image",
        email: "http://updateemail.com/47"
      };
      return Image.update(defaulImage.id, updateImage).then(data => {
        expect(data[0]).to.be.equal(1);
      });
    });
  });

  describe("Método GET Images", () => {
    it("Deve retornar uma lista com todos as Imagens", () => {
      return Image.getAll().then(data => {
        expect(data).to.be.an("array");
      });
    });
  });

  describe("Método getById", () => {
    it("Deve retornar uma Imagem de acordo com o ID informado", () => {
      return Image.getById(defaulImage.id).then(data => {
        expect(data).to.have.all.keys(["User", "UserId", "id", "name", "url"]);
      });
    });
  });

  describe("Método Delete", () => {
    it("Deve deletar uma Image", () => {
      return Image.delete(defaulImage.id).then(data => {
        expect(data).to.be.equal(1);
      });
    });
  });
});
