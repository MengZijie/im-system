import { Injectable } from '@angular/core';
import { Response } from '@angular/http';

@Injectable()
export class ResponseObject {
    private status: String;
    private data: any;

    constructor(public response: Response) {
        let data = response.json();
        this.status = data.status;
        this.data = data.data;
    }

    public getStatus() {
        return this.status;
    }
    public getData() {
        return this.data;
    }
}