import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../components/signup-page/signup-page.model';

@Injectable({
  providedIn: 'root'
})
export class SignupServiceService {

  url = "http://localhost:8089/auth/addUser"

  constructor(private http:HttpClient) { }

  addUser(customer:Customer) {
    return this.http.post(this.url,customer,{responseType:'text'})
  }
}
