import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { FriendList } from '../../providers/providers';

@Component({
    selector: 'page-message',
    templateUrl: 'message.html'
})

export class MessagePage {
    item: any;
    constructor(public navCtrl: NavController, navParams: NavParams, friend: FriendList) {
        this.item = navParams.get('item');
    }
}