import { IUser } from '../User/interface';

export interface IImage {
  readonly id: number;
  name: string;
  url: string;
  UserId: number;
}

export interface IImageDetail extends IImage {
  id: number;
  name: string;
  url: string;
  UserId: number;
  User: any;
}

export function createImage({ id, name, url, UserId }: any): IImage {
  return {
    id, name, url, UserId,
  };
}

export function createImages(data: any[]): IImage[] {
  return data.map(createImage);
}

export function createImageById({ id, name, url, User, UserId }: any): IImageDetail {
  return {
    id, name, url, User, UserId,
  };
}
