import { GetImageResponse } from './../model/get-image-response.model';
import { UtilsService } from './util.service';
import { GetImageRequest } from './../model/get-image-request.model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private http: HttpClient, private util: UtilsService) { }


  getImage(input: GetImageRequest): Observable<GetImageResponse[]>{
    const queryParams: HttpParams = UtilsService.buildQueryParams(input);
    return this.http.get<GetImageResponse[]>('http://localhost:8080/findImages', {params: queryParams})
  }
}
