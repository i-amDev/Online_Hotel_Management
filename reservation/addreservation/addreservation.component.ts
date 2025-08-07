import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { ReservationService } from '../../../service/reservation/reservation.service';
import { AddReservation } from './addreservation.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-addreservation',
  standalone: true,
  imports: [RouterLink,FormsModule,CommonModule],
  templateUrl: './addreservation.component.html',
  styleUrl: './addreservation.component.css'
})
export class AddreservationComponent {

  name!:string;
  age!:number;
  address!:string;
  phone!:number;
  noofguest!:number;
  status!:string;
  checkin!:Date;
  checkout!:Date;
  roomtype!:string;

  obj!:AddReservation;

  reservationArr:AddReservation[]=[];

  constructor(private service:ReservationService,private routes:Router,private toaster:ToastrService){}

  bookingId!:string;
  noOfRooms!:number;
  noOfNights!:number;
  billAmount!:number;
  msg!:string;

  addReservation(){
    this.obj = new AddReservation(this.bookingId,this.name,this.age,this.address,this.phone,this.noofguest,this.noOfRooms,this.status,this.checkin,this.checkout,this.noOfNights,this.roomtype,this.billAmount);
    if(this.checkin<this.checkout) {
      this.service.addReservation(this.obj).subscribe((data)=>{
        this.reservationArr.push(data);
        this.routes.navigate(['/viewreservation']);
        this.toaster.success("Reservation Done","Done");
        // console.log(data);
      },
      (err)=>{
        this.toaster.error(err.error,"Failed");
        // console.log(err.error);
      });
    }
    else {
      this.toaster.error("Enter valid date","Invalid date");
      // this.msg = "Enter valid date";
    }
  }



}
