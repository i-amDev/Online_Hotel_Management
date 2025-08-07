import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Department } from '../../components/department/adddepartment/department.model';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  constructor(private http:HttpClient) { }

  addUrl = "http://localhost:8089/department/adddepartment";
  deleteUrl = "http://localhost:8089/department/delete/";
  getAllUrl = "http://localhost:8089/department/getall";
  getByDeptUrl = "http://localhost:8089/department/";
  updateDescUrl = "http://localhost:8089/department/updateDescription/";
  updateNoOfEmpUrl = "http://localhost:8089/department/updateNoOfEmployees/";
  
  

  addDepartment(department:Department) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.post<Department>(this.addUrl,department,{headers});
  }

  deleteDepartment(departmentName:string) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.delete(this.deleteUrl+departmentName,{responseType:'text',headers});
  }

  getAll() {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.get<Department[]>(this.getAllUrl,{headers});
  }

  getByDepartmentName(departmentName:string) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.get<Department>(this.getByDeptUrl+departmentName,{headers});
  }

  updateDescription(departmentName:string,value:string) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.put<Department>(this.updateDescUrl+departmentName+"/"+value,null,{headers});
  }

  updateNoOfEmployees(departmentName:string,value:number) {
    let token = sessionStorage.getItem("token");
    const headers = new HttpHeaders({
      'Authorization':`Bearer ${token}`
    })
    return this.http.put<Department>(this.updateNoOfEmpUrl+departmentName+"/"+value,null,{headers});
  }
}
