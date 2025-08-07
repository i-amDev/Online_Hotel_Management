import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { ReservationService } from '../../../service/reservation/reservation.service';
import { AddReservation } from '../addreservation/addreservation.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-viewreservation',
  standalone: true,
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './viewreservation.component.html',
  styleUrl: './viewreservation.component.css'
})
export class ViewreservationComponent {

  constructor(private service: ReservationService, private routes: Router,private toaster:ToastrService ) { }

  logout() {
    sessionStorage.clear();
    this.routes.navigate(['/login']);
  }

  reservationArr: AddReservation[] = [];
  bId!: string;
  show = false;
  showgetbyphone = false;
  showdiv2table = false;
  showupdatename = false;
  showdiv3table = false;
  showupdateAddress = false;
  showdiv4table = false;

  getAllReservation() {
    return this.service.getAllReservation().subscribe((data) => {
      this.reservationArr = data;
      this.show = true;
      this.showgetbyphone = false;
      this.showdiv2table = false;
      this.showupdatename = false;
      this.showdiv3table = false;
      this.showupdateAddress = false;
      this.showdiv4table = false;
      this.toaster.info("Reservation list fetched.","Done");
    },
    (err)=>{
      this.toaster.error(err.error.message,"Error");
    });
  }

  deleteMessage!: any;

  deleteReservation(phoneNumber: number) {
    return this.service.deleteReservation(phoneNumber).subscribe((data) => {
      // console.log(phoneNumber);
      this.deleteMessage = data;
      this.routes.navigate(['/viewreservation']);
      this.toaster.success(this.deleteMessage,"Deleted");
      // console.log(this.deleteMessage);
    })
  }

  phone!: number;
  obj!: AddReservation;

  showdiv2() {
    this.showgetbyphone = true;
    this.show = false;
    this.showupdatename = false;
    this.showdiv3table = false;
    this.showupdateAddress = false;
    this.showdiv4table = false;
    this.toaster.info("PLEASE SCROLL DOWN FOR FETCHING DATA THROUGH PHONE NUMBER","INFO")
  }

  getByPhoneNumber(phoneNumber: number) {
    return this.service.getByphoneNumber(phoneNumber).subscribe((data) => {
      this.obj = data;
      console.log(this.obj);
      this.showdiv2table = true;
      this.toaster.success("Reservation for phone number " + phoneNumber + " fetched successfully","Success");
    },
    (err)=>{
      this.toaster.error("Please provide a valid input","Error");
    });
  }

  updatedNameObj!: AddReservation;
  updateName_phone!: number;
  updateName_name!: string;

  showdiv3() {
    this.showupdatename = true;
    this.show = false;
    this.showgetbyphone = false;
    this.showupdateAddress = false;
    this.showdiv4table = false;
    this.toaster.info("PLEASE SCROLL DOWN FOR NAME UPDATION","INFO");
  }

  updateName(phoneNumber: number, name: string) {
    return this.service.updateName(phoneNumber, name).subscribe((data) => {
      this.updatedNameObj = data;
      this.reservationArr.push(data);
      this.showdiv3table = true;
      this.toaster.success("Name updated successfully","Success");
    },
    (err)=>{
      this.toaster.error("Pease provide valid inputs.","Error");
    });
  }

  updateAddressObj!: AddReservation;
  updateAddress_Phone!: number;
  updateAddress_Address!: string;

  showdiv4() {
    this.showupdatename = false;
    this.show = false;
    this.showgetbyphone = false;
    this.showupdateAddress = true;
    this.toaster.info("PLEASE SCROLL DOWN FOR ADDRESS UPDATION","INFO");
  }
  
  updateAddress(phoneNumber: number, address: string) {
    return this.service.updateAddress(phoneNumber, address).subscribe((data) => {
      this.updateAddressObj = data;
      this.reservationArr.push(data);
      this.showdiv4table = true;
      this.toaster.success("Address updated successfully","Success");
    },
    (err)=>{
      this.toaster.error("Pease provide valid inputs.","Error");
    });
  }

}
