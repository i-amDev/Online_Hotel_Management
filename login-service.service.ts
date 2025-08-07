import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthRequest } from '../components/login-page/login-page.model';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  constructor(private http:HttpClient) { }

  url = "http://localhost:8089/auth/generateToken"
  geturl = "http://localhost:8089/auth/getUserDetails/"


  getToken(authRequest:AuthRequest) {
    return this.http.post(this.url,authRequest,{responseType:'text'})
  }

  getUser(username:string) {
    return this.http.get(this.geturl+username);
  }


}
