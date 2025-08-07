import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Guest } from '../../components/guest/addguest/guest.model';

@Injectable({
  providedIn: 'root'
})
export class GuestService {

  constructor(private http:HttpClient) { }

  addUrl = "http://localhost:8089/guest/addguest";
  deleteUrl = "http://localhost:8089/guest/delete/";
  getAllUrl = "http://localhost:8089/guest/getall";
  getByPhoneUrl = "http://localhost:8089/guest/";
  updateNameUrl = "http://localhost:8089/guest/updateName/";
  updateEmailUrl = "http://localhost:8089/guest/updateEmail/";
  

  addGuest(guest:Guest) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.post<Guest>(this.addUrl,guest,{headers});
  }

  deleteGuest(phoneNumber:number) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.delete(this.deleteUrl+phoneNumber,{responseType:'text',headers});
  }

  getAll() {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.get<Guest[]>(this.getAllUrl,{headers});
  }

  getByPhoneNumber(phoneNumber:number) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.get<Guest>(this.getByPhoneUrl+phoneNumber,{headers});
  }

  updateName(phoneNumber:number,value:string) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.put<Guest>(this.updateNameUrl+phoneNumber+"/"+value,null,{headers});
  }

  updateEmail(phoneNumber:number,value:string) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.put<Guest>(this.updateEmailUrl+phoneNumber+"/"+value,null,{headers});
  }
}
