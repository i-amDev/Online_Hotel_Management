import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AddReservation } from '../../components/reservation/addreservation/addreservation.model';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http:HttpClient) { }

  addUrl = "http://localhost:8089/reservation/addreservation";
  getAll = "http://localhost:8089/reservation/getall";
  delete = "http://localhost:8089/reservation/delete/";
  getByphoneUrl = "http://localhost:8089/reservation/getByPhone/";
  updateNameUrl = "http://localhost:8089/reservation/updateName/";
  updateAddressUrl = "http://localhost:8089/reservation/updateAddress/";

  addReservation(reservation:AddReservation) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.post<AddReservation>(this.addUrl,reservation,{headers});
  }

  getAllReservation() {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.get<AddReservation[]>(this.getAll,{headers});
  }

  deleteReservation(phoneNumber:number) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.delete(this.delete+phoneNumber,{responseType:'text',headers});
  }

  getByphoneNumber(phoneNumber:number){
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.get<AddReservation>(this.getByphoneUrl+phoneNumber,{headers});
  }

  updateName(phoneNumber:number,name:string) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.put<AddReservation>(this.updateNameUrl+phoneNumber+"/"+name,null,{headers});
  }

  updateAddress(phoneNumber:number,address:string) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.put<AddReservation>(this.updateAddressUrl+phoneNumber+"/"+address,null,{headers});
  }
}
