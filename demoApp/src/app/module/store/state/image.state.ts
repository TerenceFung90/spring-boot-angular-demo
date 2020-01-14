import { ImageService } from './../../service/image.service';
import { GetImageResponse } from './../../model/get-image-response.model';
import { GetImage } from './../action/image.actions';

import { GetImageRequest } from './../../model/get-image-request.model';
import { State, Action, StateContext, Selector } from '@ngxs/store';
import { tap } from 'rxjs/operators';

export interface ImageStateModel {
    requestParam: GetImageRequest;
    imageListing: GetImageResponse[];
}

const imageStateModelDefault: ImageStateModel = {
    requestParam: null,
    imageListing: []
}

@State<ImageStateModel>({
  name: 'image',
  defaults: imageStateModelDefault
})
export class ImageState {

    constructor(private imageService: ImageService) {}

    @Selector()  public static getImages(state: ImageStateModel){
        return state.imageListing;
    }

    @Action(GetImage, { cancelUncompleted: true })
    feedZebra(ctx: StateContext<ImageStateModel>, action: GetImage) {

        return this.imageService.getImage(action.request)
        .pipe(
            tap((res: GetImageResponse[]) => {

                console.log('terence', res);
                const state = ctx.getState();
                state.imageListing = res;
                ctx.patchState(state);
            }
        ));
      }
}