import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Guest } from './guest.model';
import { GuestService } from '../../../service/guest/guest.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-addguest',
  standalone: true,
  imports: [RouterLink,FormsModule,CommonModule],
  templateUrl: './addguest.component.html',
  styleUrl: './addguest.component.css'
})
export class AddguestComponent {

  constructor(private service:GuestService,private toaster:ToastrService,private routes:Router){}

  guestId!:string;
  name!:string;
  phone!:number;
  email!:string;
  gender!:string;
  address!:string;
  guest!:Guest;

  guestArr:Guest[] = [];

  addGuest() {
    this.guest = new Guest(this.guestId,this.name,this.phone,this.email,this.gender,this.address);
    return this.service.addGuest(this.guest).subscribe((data)=>{
      this.guestArr.push(data);
      this.routes.navigate(['/viewguest']);
      this.toaster.success("Guest added successfully","Success");
    },
    (err)=>{
      this.toaster.error(err.error,"Error");
    })
  }

}
