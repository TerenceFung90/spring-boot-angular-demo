import { GetImageResponse } from './../../model/get-image-response.model';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-image-listing-panel',
  templateUrl: './image-listing-panel.component.html',
  styleUrls: ['./image-listing-panel.component.css']
})
export class ImageListingPanelComponent implements OnInit {

  @Input() data: GetImageResponse[];

  constructor() { }

  ngOnInit() {
    

  }

}
