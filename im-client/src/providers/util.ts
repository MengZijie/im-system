import { Injectable } from '@angular/core';
import { Api } from './api';

@Injectable()
export class Util {
    private static config = null;

    constructor(public api: Api) {
        this.init();
    }

    getImgSrc(src: string) {
        return Util.config.HOST_PATH + Util.config.STATIC_PATH + Util.config.IMG_PATH + src;
    }

    init() {
        if (Util.config == null) {
            this.api.getLocal('assets/config/config.json').subscribe(resp => {
                Util.config = resp.json();
            });
        }
    }
}