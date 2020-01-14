import { UtilsService } from './module/service/util.service';
import { ImageState } from './module/store/state/image.state';
import { ImageService } from './module/service/image.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { ImageListingPageComponent } from './module/page/image-listing-page/image-listing-page.component';
import { ImageSearchPanelComponent } from './module/component/image-search-panel/image-search-panel.component';
import { ImageListingPanelComponent } from './module/component/image-listing-panel/image-listing-panel.component';
import { ReactiveFormsModule } from '@angular/forms';
import { NgxsModule } from '@ngxs/store';

@NgModule({
  declarations: [
    AppComponent,
    ImageListingPageComponent,
    ImageSearchPanelComponent,
    ImageListingPanelComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxsModule.forRoot([ImageState])
  ],
  providers: [
    ImageService,
    UtilsService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
