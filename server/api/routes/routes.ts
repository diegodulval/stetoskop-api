import { Application, Request, Response } from 'express';

class Routes {

  constructor(app: Application) {
    this.getRoutes(app);
  }

  public getRoutes(app: Application): void {
    app.route('/').get((req: Request, res: Response) => res.send('Coooeh rapaziada!'));
    app.route('/ola/:nome').get((req: Request, res: Response) => res.send(`Salve ${req.params.nome}!`));
  }
}

export default Routes;
