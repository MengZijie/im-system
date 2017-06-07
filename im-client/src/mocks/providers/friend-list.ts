import { Friend } from './../../models/friend';
import { Api } from './../../providers/api';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class FriendList {
  static friendList: Friend[] = [];

  constructor(public http: Http, public api: Api) {
    // let items = [
    //   {
    //     "name": "Burt Bear",
    //     "profilePic": "assets/img/speakers/bear.jpg",
    //     "about": "Burt is a Bear."
    //   },
    //   {
    //     "name": "Charlie Cheetah",
    //     "profilePic": "assets/img/speakers/cheetah.jpg",
    //     "about": "Charlie is a Cheetah."
    //   },
    //   {
    //     "name": "Donald Duck",
    //     "profilePic": "assets/img/speakers/duck.jpg",
    //     "about": "Donald is a Duck."
    //   },
    //   {
    //     "name": "Eva Eagle",
    //     "profilePic": "assets/img/speakers/eagle.jpg",
    //     "about": "Eva is an Eagle."
    //   },
    //   {
    //     "name": "Ellie Elephant",
    //     "profilePic": "assets/img/speakers/elephant.jpg",
    //     "about": "Ellie is an Elephant."
    //   },
    //   {
    //     "name": "Molly Mouse",
    //     "profilePic": "assets/img/speakers/mouse.jpg",
    //     "about": "Molly is a Mouse."
    //   },
    //   {
    //     "name": "Paul Puppy",
    //     "profilePic": "assets/img/speakers/puppy.jpg",
    //     "about": "Paul is a Puppy."
    //   }
    // ];

    // for (let item of items) {
    //   Items.items.push(new Item(item));
    // }
  }

  query(params?: any) {
    if(!params || params == '') {
      return FriendList.friendList;
    }

    return FriendList.friendList.filter((friend) => {
      for (let key in params) {
        let field = friend['friend']['username'];
        if (typeof field == 'string' && field.toLowerCase().indexOf(params[key].toLowerCase()) >= 0) {
          return friend;
        } else if (field == params[key]) {
          return friend;
        }
      }
      return null;
    });
  }

  get(params?: any) {
    let seq = this.api.get('/user/friend/get', params).share();
    seq.map(res => res.json())
    return seq;
  }

  add(item: Friend) {
    FriendList.friendList.push(item);
  }

  delete(item: Friend) {
    FriendList.friendList.splice(FriendList.friendList.indexOf(item), 1);
  }
}
