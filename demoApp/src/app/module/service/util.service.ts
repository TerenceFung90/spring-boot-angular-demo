import { Injectable } from '@angular/core';
import { HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {
  static buildQueryParams(source: Object): HttpParams {
      let target: HttpParams = new HttpParams();
      Object.keys(source).forEach((key: string) => {
          const value: string | number | boolean | Date = source[key];
          if ((typeof value !== 'undefined') && (value !== null)) {
              target = target.append(key, value.toString());
          }
      });
      return target;
  }
}
