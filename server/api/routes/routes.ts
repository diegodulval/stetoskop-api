import { Application, Request, Response } from 'express';

import TokenRoutes from '../../modules/auth/auth';
import ImageRoutes from '../../modules/Image/routes';
import UserRoutes from '../../modules/User/routes';

class Routes {

  public initRoutes(app: Application, auth: any): void {
    app.route('/api/users/all').all(auth.config().authenticate()).get(UserRoutes.index);
    app.route('/api/users/:id').all(auth.config().authenticate()).get(UserRoutes.findOne);
    app.route('/api/users/create').all(auth.config().authenticate()).post(UserRoutes.create);
    app.route('/api/users/:id/update').all(auth.config().authenticate()).put(UserRoutes.update);
    app.route('/api/users/:id/destroy').all(auth.config().authenticate()).delete(UserRoutes.destroy);

    app.route('/api/image/all').all(auth.config().authenticate()).get(ImageRoutes.index);
    app.route('/api/image/:id').all(auth.config().authenticate()).get(ImageRoutes.findOne);
    app.route('/api/image/create').all(auth.config().authenticate()).post(ImageRoutes.create);
    app.route('/api/image/:id/update').all(auth.config().authenticate()).put(ImageRoutes.update);
    app.route('/api/image/:id/destroy').all(auth.config().authenticate()).delete(ImageRoutes.destroy);

    app.route('/token').post(TokenRoutes.auth);

  }
}

export default new Routes();
