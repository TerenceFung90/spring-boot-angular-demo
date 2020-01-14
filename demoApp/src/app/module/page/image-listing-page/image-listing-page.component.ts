import { GetImageResponse } from './../../model/get-image-response.model';
import { ImageState } from './../../store/state/image.state';
import { Component, OnInit } from '@angular/core';
import { Store, Select } from '@ngxs/store';
import { GetImage } from '../../store/action/image.actions';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-image-listing-page',
  templateUrl: './image-listing-page.component.html',
  styleUrls: ['./image-listing-page.component.css']
})
export class ImageListingPageComponent implements OnInit {

  @Select(ImageState.getImages) data$: Observable<GetImageResponse[]>;

  constructor(private store: Store) { }
  
  ngOnInit() {
  }

  loadingImageListing(inputForm: any){
    console.log(inputForm);

    this.store.dispatch(new GetImage(inputForm));

  }

}
