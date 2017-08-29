import { Request, Response } from "express";
import * as HTTPStatus from "http-status";
import * as _ from "lodash";

import Handlers from "../../api/responses/handlers";

import Image from "./service";

class ImageController {
  public getAll(req: Request, res: Response) {
    Image.getAll()
      .then(_.partial(Handlers.onSucess, res))
      .catch(
        _.partial(Handlers.onError, res, "Erro ao buscar todos os imagem")
      );
  }

  public createImage(req: Request, res: Response) {
    Image.create(req.body)
      .then(_.partial(Handlers.onSucess, res))
      .catch(_.partial(Handlers.dbErrorHandler, res))
      .catch(_.partial(Handlers.onError, res, "Erro ao inserir novo imagem"));
  }

  public getById(req: Request, res: Response) {
    const ImageId = parseInt(req.params.id, 10);
    Image.getById(ImageId)
      .then(_.partial(Handlers.onSucess, res))
      .catch(_.partial(Handlers.onError, res, "Imagem não encontrado"));
  }

  public updateImage(req: Request, res: Response) {
    const ImageId = parseInt(req.params.id, 10);
    const props = req.body;
    Image.update(ImageId, props)
      .then(_.partial(Handlers.onSucess, res))
      .catch(_.partial(Handlers.onError, res, "Erro ao atualizar imagem"));
  }

  public deleteImage(req: Request, res: Response) {
    const imageId = parseInt(req.params.id, 10);
    Image.delete(imageId)
      .then(_.partial(Handlers.onSucess, res))
      .catch(_.partial(Handlers.onError, res, "Erro ao excluir imagem"));
  }
}

export default new ImageController();
