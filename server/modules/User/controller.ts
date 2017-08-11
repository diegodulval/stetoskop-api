import { Request, Response } from 'express';

class UserController {
  constructor() { }

  public getAll(req: Request, res: Response) {
    res.status(200).json({
      message: 'OK',
    });
  }

  public createUser(req: Request, res: Response) {
    res.status(200).json({
      message: 'OK',
    });
  }

  public getById(req: Request, res: Response) {
    res.status(200).json({
      message: 'OK',
    });
  }

  public updateUser(req: Request, res: Response) {
    res.status(200).json({
      message: 'OK',
    });
  }

  public deleteUser(req: Request, res: Response) {
    res.status(200).json({
      message: 'OK',
    });
  }
}

export default UserController;
