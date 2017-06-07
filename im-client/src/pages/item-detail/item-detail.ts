import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { FriendList } from '../../providers/providers';
import { Friend } from '../../models/friend';
import { MessagePage } from '../message/message';

@Component({
  selector: 'page-item-detail',
  templateUrl: 'item-detail.html'
})
export class ItemDetailPage {
  friend: any;

  constructor(public navCtrl: NavController, navParams: NavParams, friendList: FriendList) {
    this.friend = navParams.get('friend');
  }

  startTalking(friend: Friend) {
    this.navCtrl.push(MessagePage, {
      friend: Friend
    });
  }

}
