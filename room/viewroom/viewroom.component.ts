import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { RoomService } from '../../../service/room/room.service';
import { Room } from '../addroom/room.model';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-viewroom',
  standalone: true,
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './viewroom.component.html',
  styleUrl: './viewroom.component.css'
})
export class ViewroomComponent {

  constructor(private service: RoomService, private routes: Router,private toaster:ToastrService) { }

  roomType!: string;
  // showdiv2table = false;
  roomCharge!: number;
  updateCharge_type!: string;
  updateCharge_value!: number;
  updateNo_type!: string;
  updateNo_value!: number;

  logout() {
    sessionStorage.clear();
    this.routes.navigate(['/login']);
  }

  deleteMsg!: string;

  deleteRoom(roomType: string) {
    return this.service.deleteRoom(roomType).subscribe((data) => {
      this.deleteMsg = data;
      // this.routes.navigate(['/viewroom']);
      this.toaster.success("Room deleted successfully","Success");
    })
  }

  roomArr: Room[] = [];
  show = false;

  getAll() {
    return this.service.getAll().subscribe((data) => {
      this.roomArr = data;
      console.log(this.roomArr);
      this.show = true;
      this.showcharge = false;
      this.showroom = false;
      this.showroomtype = false;
      this.toaster.info("Room list fetched. Scroll Down","Info");
    },
    (err)=>{
      this.toaster.error(err.error.message,"Error");
    })
  }

  roomTypeObj!: Room;
  showroomtype = false;
  showroomtypetable = false;
  showdiv2() {
    this.showroomtype = true;
    this.show = false;
    this.showcharge = false;
    this.showroom = false;
    this.toaster.info("Scroll Down for fetching room from room type","Info");
  }

  getByRoomType(roomType: string) {
    return this.service.getByRoomType(roomType).subscribe((data) => {
      this.roomTypeObj = data;
      console.log(this.roomTypeObj);
      this.showroomtypetable = true;
      this.toaster.success("Room for type " + roomType + " fetched successfully","Success");
    },
    (err)=>{
      this.toaster.error(err.error,"Error");
    })
  }

  roomChargeObj!: Room;
  showcharge = false;
  showchargetable = false;
  showdiv3() {
    this.showcharge = true;
    this.show = false;
    this.showroom = false;
    this.showroomtype = false;
    this.toaster.info("Scroll down to update room charge","Info");
  }

  updateRoomCharge(roomType: string, value: number) {
    return this.service.updateRoomCharge(roomType, value).subscribe((data) => {
      this.roomChargeObj = data;
      this.roomArr.push(data);
      // console.log(this.roomChargeObj);
      // console.log(this.updateCharge_type);
      // console.log(this.updateCharge_value);
      this.showchargetable = true;
      this.toaster.success("Room charge updated successfully for " + roomType,"Success");
    },
    (err)=>{
      this.toaster.error("Invalid inputs","Error");
    })
  }

  noOfRoomsObj!: Room;
  showroom = false;
  showroomtable = false;
  showdiv4() {
    this.showroom = true;
    this.show = false;
    this.showcharge = false;
    this.showroomtype = false;
    this.toaster.info("Scroll down to update no of rooms","Info");
  }

  updateNoOfRooms(roomType: string, value: number) {
    return this.service.updateNoOfRooms(roomType, value).subscribe((data) => {
      this.noOfRoomsObj = data;
      console.log(this.noOfRoomsObj);
      this.showroomtable = true;
      this.toaster.success("No of rooms updated successfully for " + roomType,"Success");
    },
    (err)=>{
      this.toaster.error("Invalid inputs","Error");
    })
  }

}
