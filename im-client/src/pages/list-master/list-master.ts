import { Util } from './../../providers/util';
// import { FriendList } from './../../mocks/providers/friend-list';
import { Friend } from './../../models/friend';
import { User } from './../../providers/user';

import { ResponseObject } from './../../providers/response-object';
import { Component } from '@angular/core';
import { NavController, ModalController, ToastController } from 'ionic-angular';

import { ItemCreatePage } from '../item-create/item-create';
import { ItemDetailPage } from '../item-detail/item-detail';

import { FriendList } from '../../providers/providers';

import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'page-list-master',
  templateUrl: 'list-master.html'
})
export class ListMasterPage {
  currentFriendList: Friend[];
  toastCtrl: ToastController;
  requestFailErrorString: string;

  constructor(public navCtrl: NavController,
              public friendList: FriendList,
              public modalCtrl: ModalController,
              public translateService: TranslateService,
              public util: Util ) {
    this.translateService.get('SIGNUP_ERROR').subscribe((value) => {
      this.requestFailErrorString = value;
    })
    this.friendList.get(User.getUser()).subscribe((resp) => {
      let responseObject = new ResponseObject(resp);
      if (responseObject.getStatus() == 'success') {
        for (let item in responseObject.getData()) {
          FriendList.friendList.push(new Friend(responseObject.getData()[item]));
          this.currentFriendList = FriendList.friendList;
        }
      } else {
        let toast = this.toastCtrl.create({
          message: this.requestFailErrorString,
          duration: 3000,
          position: 'top'
        });
        toast.present();
      }
    }, (err) => {
      let toast = this.toastCtrl.create({
        message: this.requestFailErrorString,
        duration: 3000,
        position: 'top'
      });
      toast.present();
    })

  }

  /**
   * The view loaded, let's query our friendList for the list
   */
  ionViewDidLoad() {
  }

  /**
   * Prompt the user to add a new item. This shows our ItemCreatePage in a
   * modal and then adds the new item to our data source if the user created one.
   */
  addItem() {
    let addModal = this.modalCtrl.create(ItemCreatePage);
    addModal.onDidDismiss(item => {
      if (item) {
        this.friendList.add(item);
      }
    })
    addModal.present();
  }

  getFriendList(ev) {
    let val = ev.target.value;
    if (!val || !val.trim()) {
      this.currentFriendList = FriendList.friendList;
      return;
    }
    this.currentFriendList = this.friendList.query({
      name: val
    });
  }

  /**
   * Delete an item from the list of friendList.
   */
  deleteItem(item) {
    this.friendList.delete(item);
  }

  /**
   * Navigate to the detail page for this item.
   */
  openItem(friend: Friend) {
    this.navCtrl.push(ItemCreatePage, {
      friend: friend
    });
  }

  getImgSrc(src: string) {
    // return 'http://127.0.0.1:8000/static/img/'+src;
    return this.util.getImgSrc(src);
  }
}
