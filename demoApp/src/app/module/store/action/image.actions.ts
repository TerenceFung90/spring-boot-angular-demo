import { GetImageRequest } from './../../model/get-image-request.model';

export class GetImage {
    static readonly type = '[Image] get Image';
    constructor(public request: GetImageRequest) {}
  }