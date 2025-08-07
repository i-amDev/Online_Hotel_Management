import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Room } from '../../components/room/addroom/room.model';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  constructor(private http:HttpClient) { }

  addUrl = "http://localhost:8089/room/addroom";
  deleteUrl = "http://localhost:8089/room/delete/";
  getAllUrl = "http://localhost:8089/room/getall";
  getByRoomTypeUrl = "http://localhost:8089/room/";
  roomChargeUrl = "http://localhost:8089/room/updateRoomCharge/";
  noOfRoomsUrl = "http://localhost:8089/room/updateNoOfRooms/";

  addRoom(room:Room) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.post<Room>(this.addUrl,room,{headers});
  }

  deleteRoom(roomType:string) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.delete(this.deleteUrl+roomType,{responseType:'text',headers});
  }

  getAll() {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.get<Room[]>(this.getAllUrl,{headers});
  }

  getByRoomType(roomType:string) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.get<Room>(this.getByRoomTypeUrl+roomType,{headers});
  }

  updateRoomCharge(roomType:string,value:number) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.put<Room>(this.roomChargeUrl+roomType+"/"+value,null,{headers});
  }

  updateNoOfRooms(roomType:string,value:number) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.put<Room>(this.noOfRoomsUrl+roomType+"/"+value,null,{headers});
  }

}
