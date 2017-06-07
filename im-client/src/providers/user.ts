import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';
import { Api } from './api';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

/**
 * Most apps have the concept of a User. This is a simple provider
 * with stubs for login/signup/etc.
 *
 * This User provider makes calls to our API at the `login` and `signup` endpoints.
 *
 * By default, it expects `login` and `signup` to return a JSON object of the shape:
 *
 * ```json
 * {
 *   status: 'success',
 *   user: {
 *     // User fields your app needs, like "id", "name", "email", etc.
 *   }
 * }
 * ```
 *
 * If the `status` field is not `success`, then an error is detected and returned.
 */
@Injectable()
export class User {
  private static _user: any;

  requestOptions: RequestOptions;

  constructor(public http: Http, public api: Api) {
    this.requestOptions = new RequestOptions();
    this.requestOptions.headers = new Headers({
      // 'Content-Type': 'application/x-www-form-urlencoded',
    });
  }

  /**
   * Send a POST request to our login endpoint with the data
   * the user entered on the form.
   */
  login(accountInfo: any) {
    let seq = this.api.post('user/login', accountInfo, this.requestOptions).share();

    seq
      .map(res => res.json())
      .subscribe(res => {
        // If the API returned a successful response, mark the user as logged in
        if (res.status == 'success') {
          this._loggedIn(res);
        } else {
        }
      }, err => {
        console.error('ERROR', err);
      });

    return seq;
  }

  /**
   * Send a POST request to our signup endpoint with the data
   * the user entered on the form.
   */
  signup(accountInfo: any) {
    let seq = this.api.post('user/register', accountInfo, this.requestOptions).share();
    seq.map(res => res.json())
    return seq;
  }

  public static getUser(){
    return User._user;
  }

  upload(formData: FormData) {
    let seq = this.api.upload('upload/img', formData, this.requestOptions).share();
    seq.map(res => res.json())
    return seq;
  }

  update(accountInfo: any){
    let seq = this.api.post('user/update', accountInfo, this.requestOptions).share();
    seq.map(res => res.json())
    return seq;
  }

  /**
   * Log the user out, which forgets the session
   */
  logout() {
    User._user = null;
  }

  /**
   * Process a login/signup response to store user data
   */
  _loggedIn(resp) {
    User._user = resp.data;
    console.log(User.getUser());
  }
}
