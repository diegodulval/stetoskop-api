import { Request, Response } from "express";
import ImageController from "./controller";

class ImageRoutes {
  public index(req: Request, res: Response) {
    return ImageController.getAll(req, res);
  }
  public findOne(req: Request, res: Response) {
    return ImageController.getById(req, res);
  }
  public create(req: Request, res: Response) {
    return ImageController.createImage(req, res);
  }
  public update(req: Request, res: Response) {
    return ImageController.updateImage(req, res);
  }
  public destroy(req: Request, res: Response) {
    return ImageController.deleteImage(req, res);
  }
}

export default new ImageRoutes();
