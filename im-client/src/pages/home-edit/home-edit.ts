import { TranslateService } from '@ngx-translate/core';
import { ResponseObject } from './../../providers/response-object';
import { Util } from './../../providers/util';
import { User } from './../../providers/user';
import { Component, ViewChild } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { NavController, ViewController, ToastController } from 'ionic-angular';

import { Camera } from '@ionic-native/camera';



@Component({
    selector: 'page-home-edit',
    templateUrl: 'home-edit.html'
})
export class HomeEditPage {
    @ViewChild('fileInput') fileInput;

    account: any;

    isReadyToSave: boolean;

    item: any;

    form: FormGroup;

    uploadFileError;

    /**
     * Creates an instance of HomeEditPage.
     * @param {NavController} navCtrl 
     * @param {ViewController} viewCtrl 
     * @param {FormBuilder} formBuilder 
     * @param {Camera} camera 
     * @param {User} user 
     * @param {ToastController} toastCtrl 
     * @param {TranslateService} translateService 
     * @param {Util} util 
     * 
     * @memberof HomeEditPage
     */
    constructor(public navCtrl: NavController,
        public viewCtrl: ViewController,
        formBuilder: FormBuilder,
        public camera: Camera,
        public user: User,
        public toastCtrl: ToastController,
        public translateService: TranslateService,
        public util: Util) {
        this.account = User.getUser();
        this.form = formBuilder.group({
            photo: [''],
            username: ['', Validators.required],
            nickname: [''],
            sign: [''],
            email: ['']
        });

        // Watch the form for changes, and
        this.form.valueChanges.subscribe((v) => {
            this.isReadyToSave = this.form.valid;
        });

        this.translateService.get('UPLOAD_FILE_ERROR').subscribe((value) => {
            this.uploadFileError = value;
        })
    }

    ionViewDidLoad() {

    }

    getPicture() {
        if (Camera['installed']()) {
            this.camera.getPicture({
                destinationType: this.camera.DestinationType.DATA_URL,
                targetWidth: 96,
                targetHeight: 96
            }).then((data) => {
                this.form.patchValue({ 'photo': 'data:image/jpg;base64,' + data });
            }, (err) => {
                alert('Unable to take photo');
            })
        } else {
            this.fileInput.nativeElement.click();
        }
    }

    processWebImage(event) {
        if (event.preventDefault) {
            event.preventDefault();
        } else {
            event.returnValue = false;
        }

        // var form = document.getElementById("imgUploadForm");
        // var formData = new FormData(form);
        // let reader = new FileReader();
        // reader.onload = (readerEvent) => {

        //     let imageData = (readerEvent.target as any).result;
        //     this.form.patchValue({ 'photo': imageData });
        //     console.log(imageData);
        // };
        // console.log(event.target.files[0]);

        let formdata = new FormData(); 
        let resp: any;
        formdata.append("file", event.target.files[0]);
        this.user.upload(formdata).share().subscribe((resp => {
            let response = new ResponseObject(resp);
            if (response.getStatus() == 'success') {
                this.account.photo = response.getData();
            }
        }), (error => {
            // Unable to log in
            let toast = this.toastCtrl.create({
                message: this.uploadFileError,
                duration: 3000,
                position: 'top'
            });
            toast.present();
        }));
        // this.user.update(formdata).share().subscribe();
        // reader.readAsBinaryString(event.target.files[0]);
        // console.log(event.target.files[0]);

    }

    getProfileImageStyle() {
        if (this.form.controls['photo'].value) {
            return 'url(' + this.form.controls['photo'].value + ')';
        }
        return 'url(' + this.util.getImgSrc(this.account.photo) + ')';
    }

    /**
     * The user cancelled, so we dismiss without sending data back.
     */
    cancel() {
        this.viewCtrl.dismiss();
    }

    /**
     * The user is done and wants to create the item, so return it
     * back to the presenter.
     */
    done() {
        if (!this.form.valid) { return; }
        this.viewCtrl.dismiss(this.form.value);
    }

    updateUser() {
        console.log(this.form.value);
        this.user.update(this.form.value).subscribe((resp) => {

        }, (err) => {

        });
    }
}
