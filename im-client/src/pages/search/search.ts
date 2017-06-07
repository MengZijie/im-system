import { Friend } from './../../models/friend';
import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { ItemDetailPage } from '../item-detail/item-detail';

import { FriendList } from '../../providers/providers';


@Component({
  selector: 'page-search',
  templateUrl: 'search.html'
})
export class SearchPage {
  
  currentItems: any = [];

  constructor(public navCtrl: NavController, public navParams: NavParams, public friendList: FriendList) { }

  /**
   * Perform a service for the proper items.
   */
  getItems(ev) {
    let val = ev.target.value;
    if (!val || !val.trim()) {
      this.currentItems = FriendList.friendList;
      return;
    }
    this.currentItems = this.friendList.query({
      name: val
    });
  }

  /**
   * Navigate to the detail page for this item.
   */
  openItem(friend: Friend) {
    this.navCtrl.push(ItemDetailPage, {
      friend: Friend
    });
  }

}
