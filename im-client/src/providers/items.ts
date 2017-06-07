import { Friend } from './../models/friend';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';

import { Api } from './api';


@Injectable()
export class Items {

  constructor(public http: Http, public api: Api) {
  }

  query(params?: any) {
    return this.api.get('/user/friend/get', params)
      .map(resp => resp.json());
  }

  add(item: Friend) {
  }

  delete(item: Friend) {
  }

}
