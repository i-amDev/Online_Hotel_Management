import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { GuestService } from '../../../service/guest/guest.service';
import { Guest } from '../addguest/guest.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-viewguest',
  standalone: true,
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './viewguest.component.html',
  styleUrl: './viewguest.component.css'
})
export class ViewguestComponent {

  constructor(private service: GuestService, private routes: Router,private toaster:ToastrService) { }

  logout() {
    sessionStorage.clear();
    this.routes.navigate(['/login']);
  }

  deleteMsg!: string;

  deleteGuest(phoneNumber: number) {
    return this.service.deleteGuest(phoneNumber).subscribe((data) => {
      this.deleteMsg = data;
      this.toaster.success(this.deleteMsg,"Success");
    })
  }

  guestArr: Guest[] = [];
  show = false;

  getAll() {
    return this.service.getAll().subscribe((data) => {
      this.guestArr = data;
      this.show = true;
      this.showemail = false;
      this.showguest = false;
      this.showname = false;
      this.toaster.info("Guest list fetched successfully","Info");
    },
    (err)=>{
      this.toaster.error(err.error.message,"Error");
    })
  }

  guestByPhoneObj!: Guest;
  showguest = false;
  showguesttable = false;
  phone!:number;

  showdiv2() {
    this.showguest = true;
    this.show = false;
    this.showemail = false;
    this.showname = false;
    this.toaster.info("Scroll down to fetch guest by phone number","Info");
  }

  getByPhoneNumber(phoneNumber: number) {
    return this.service.getByPhoneNumber(phoneNumber).subscribe((data) => {
      this.guestByPhoneObj = data;
      this.showguesttable = true;
      this.toaster.success("Guest fetched successfully for phone number " + phoneNumber,"Success");
    },
    (err)=>{
      this.toaster.error("Invalid inputs","Error");
    })
  }

  updateNameObj!: Guest;
  showname = false;
  shownametable = false;
  name_phone!:number;
  name!:string;

  showdiv3() {
    this.showname = true;
    this.show = false;
    this.showemail = false;
    this.showguest = false;
    this.toaster.info("Scroll down to update guest name","Info");
  }

  updateName(phoneNumber: number, value: string) {
    return this.service.updateName(phoneNumber, value).subscribe((data) => {
      this.updateNameObj = data;
      this.shownametable = true;
      this.toaster.success("Guest name updated successfully","Success");
    },
    (err)=>{
      this.toaster.error("Invalid inputs","Error");
    })
  }

  updateEmailObj!: Guest;
  showemail = false;
  showemailtable = false;
  email_phone!:number;
  email!:string;

  showdiv4() {
    this.showemail = true;
    this.show = false;
    this.showguest = false;
    this.showname = false;
    this.toaster.info("Scroll down to update guest email","Info");
  }

  updateEmail(phoneNumber: number, value: string) {
    return this.service.updateEmail(phoneNumber, value).subscribe((data) => {
      this.updateEmailObj = data;
      this.showemailtable = true;
      this.toaster.success("Guest email updated successfully","Success");
    },
    (err)=>{
      this.toaster.error("Invalid inputs","Error");
    })
  }

}
