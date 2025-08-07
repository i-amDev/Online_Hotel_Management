import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Staff } from '../../components/staff/addstaff/staff.model';

@Injectable({
  providedIn: 'root'
})
export class StaffService {

  constructor(private http:HttpClient) { }

  addUrl = "http://localhost:8089/staff/addstaff";
  deleteUrl = "http://localhost:8089/staff/delete/";
  getallUrl = "http://localhost:8089/staff/getall";
  getByEmailUrl = "http://localhost:8089/staff/";
  updateSalaryUrl = "http://localhost:8089/staff/updateSalary/";
  updateNameUrl = "http://localhost:8089/staff/updateName/";
  updateAddressUrl = "http://localhost:8089/staff/updateAddress/";
  
  

  addStaff(staff:Staff) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.post<Staff>(this.addUrl,staff,{headers});
  }

  deleteStaff(email:string) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.delete(this.deleteUrl+email,{responseType:'text',headers});
  }

  getAll() {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.get<Staff[]>(this.getallUrl,{headers});
  }

  getByEmail(email:string) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.get<Staff>(this.getByEmailUrl+email,{headers});
  }

  updateSalary(email:string,value:number) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.put<Staff>(this.updateSalaryUrl+email+"/"+value,null,{headers});
  }

  updateName(email:string,value:string) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.put<Staff>(this.updateNameUrl+email+"/"+value,null,{headers});
  }
  updateAddress(email:string,value:string) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.put<Staff>(this.updateAddressUrl+email+"/"+value,null,{headers});
  }


}
