import { Request, Response } from 'express';
import UserController from './controller'

let UserCtrl;

class UserRoutes {
  constructor() {
    UserCtrl = new UserController();
  }

  public index(req: Request, res: Response) {
    return UserCtrl.getAll(req, res);
  }
  public findOne(req: Request, res: Response) {
    return UserCtrl.getById(req, res);
  }
  public create(req: Request, res: Response) {
    return UserCtrl.getCreateUser(req, res);
  }
  public update(req: Request, res: Response) {
    return UserCtrl.update(req, res);
  }
  public destroy(req: Request, res: Response) {
    return UserCtrl.deleteUser(req, res);
  }
}

export default UserRoutes;
