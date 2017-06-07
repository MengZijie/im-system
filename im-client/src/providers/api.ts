import { Upload } from 'angular-file-upload/';
import { Injectable } from '@angular/core';
import { Http, RequestOptions, URLSearchParams, } from '@angular/http';
import 'rxjs/add/operator/map';

/**
 * Api is a generic REST Api handler. Set your API url first.
 */
@Injectable()
export class Api {
  static url: string = 'http://127.0.0.1:8000';
  static webSocketUrl: string = 'ws://127.0.0.1:8000';

  private static webSocket: WebSocket;

  constructor(public http: Http) {
  }

  get(endpoint: string, params?: any, options?: RequestOptions) {
    if (!options) {
      options = new RequestOptions();
    }
    options.withCredentials = true;
    // Support easy query params for GET requests
    if (params) {
      let p = new URLSearchParams();
      for (let k in params) {
        p.set(k, params[k]);
      }
      // Set the search field if we have params and don't already have
      // a search field set in options.
      options.search = !options.search && p || options.search;
    }

    return this.http.get(Api.url + '/' + endpoint, options);
  }

  getLocal(endpoint: string, params?: any, options?: RequestOptions) {
    if (!options) {
      options = new RequestOptions();
    }
    options.withCredentials = true;
    // Support easy query params for GET requests
    if (params) {
      let p = new URLSearchParams();
      for (let k in params) {
        p.set(k, params[k]);
      }
      // Set the search field if we have params and don't already have
      // a search field set in options.
      options.search = !options.search && p || options.search;
    }

    return this.http.get(endpoint, options);
  }

  post(endpoint: string, body: any, options?: RequestOptions) {
    options.withCredentials = true;
    return this.http.post(Api.url + '/' + endpoint, body, options);
  }

  put(endpoint: string, body: any, options?: RequestOptions) {
    return this.http.put(Api.url + '/' + endpoint, body, options);
  }

  delete(endpoint: string, options?: RequestOptions) {
    return this.http.delete(Api.url + '/' + endpoint, options);
  }

  patch(endpoint: string, body: any, options?: RequestOptions) {
    return this.http.put(Api.url + '/' + endpoint, body, options);
  }

  upload(endpoint: string, formData: FormData, options?: RequestOptions) {
    options.withCredentials = true;
    return this.http.post(Api.url + '/' + endpoint, formData, options);
  }

  private static initWebSocket() {
    if ('WebSocket' in window) {
      Api.webSocket = new WebSocket(Api.webSocketUrl + '/websocket');
    }
    Api.webSocket.onclose = Api.onClose;
    Api.webSocket.onerror = Api.onError;
    Api.webSocket.onmessage = Api.onMessage;
    Api.webSocket.onopen = Api.onOpen;
  }
  public static onClose() {
    console.log('Websocket is close');
  }

  public static onOpen(event) {
    console.log('Websocket is open');
  }

  public static onMessage(event) {
    console.log('messaging: ' + event.data);
  }

  public static onError(event) {
    console.log(event);
  }

  public static doSend(msg?:any) {
    console.log(Api.webSocket.readyState);
    if (Api.webSocket.readyState == WebSocket.OPEN) {
      if (!msg) {
        msg = 'hello world';
      }
      Api.webSocket.send(msg);
    } else {
      console.log('连接状态出错');
    }
  }

  public static disconnect() {
    if (Api.webSocket != null) {
      Api.webSocket.close();
      Api.webSocket = null;
    }
  }

  public static connect() {
    Api.disconnect();
    Api.initWebSocket();
  }

}
