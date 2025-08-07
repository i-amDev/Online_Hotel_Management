import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { RoomService } from '../../../service/room/room.service';
import { Room } from './room.model';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-addroom',
  standalone: true,
  imports: [RouterLink,FormsModule,CommonModule],
  templateUrl: './addroom.component.html',
  styleUrl: './addroom.component.css'
})
export class AddroomComponent {

  constructor(private service:RoomService,private toaster:ToastrService,private routes:Router){}

  roomId!:string;
  roomcharge!:number;
  roomtype!:string;
  noOfRooms!:number;

  roomObj!:Room;

  roomArr:Room[] = [];

  addRoom() {
    this.roomObj = new Room(this.roomId,this.roomcharge,this.roomtype,this.noOfRooms);
    return this.service.addRoom(this.roomObj).subscribe((data)=>{
      this.roomArr.push(data);
      this.routes.navigate(['/viewroom']);
      this.toaster.success("Room added successfully","Success");
    },
    (err)=>{
      this.toaster.error(err.error,"Error");
    })
  }

}
