import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { DepartmentService } from '../../../service/department/department.service';
import { Department } from '../adddepartment/department.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-viewdepartment',
  standalone: true,
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './viewdepartment.component.html',
  styleUrl: './viewdepartment.component.css'
})
export class ViewdepartmentComponent {

  constructor(private service: DepartmentService, private routes: Router,private toaster:ToastrService) { };

  logout() {
    sessionStorage.clear();
    this.routes.navigate(['/login']);
  }

  departmentArr: Department[] = [];
  show = false;

  getAll() {
    return this.service.getAll().subscribe((data) => {
      this.departmentArr = data;
      this.show = true;
      this.showdepartment = false;
      this.showdesc = false;
      this.showno = false;
      this.toaster.info("Department list fetched successfully","Info");
    },
    (err)=>{
      this.toaster.error(err.error.message,"Error");
    })
  }

  deleteMsg!: string;

  deleteDepartment(departmentName: string) {
    return this.service.deleteDepartment(departmentName).subscribe((data) => {
      this.deleteMsg = data;
      this.toaster.success("Department deleted successfully for department name " + departmentName ,"Success");
      this.routes.navigate(['/viewdepartment']);
    },
    (err)=>{
      this.toaster.error(err.error,"Error");
    })
  }

  departmentObj!: Department;
  showdepartment = false;
  showdepartmenttable = false;
  name!:string;

  showdiv2() {
    this.showdepartment = true;
    this.show = false;
    this.showdesc = false;
    this.showno = false;
    this.toaster.info("Scroll down to fetch department by name","Info");
  }

  getByDepartmentName(departmentName: string) {
    return this.service.getByDepartmentName(departmentName).subscribe((data) => {
      this.departmentObj = data;
      this.showdepartmenttable = true;
      this.toaster.success(departmentName + " department fetched successfully","Success");
    },
    (err)=>{
      this.toaster.error(err.error,"Error");
    })
  }

  updateDescObj!: Department;
  showdesc = false;
  showdesctable = false;
  desc!:string;
  desc_name!:string;

  showdiv3() {
    this.showdesc = true;
    this.show = false;
    this.showdepartment = false;
    this.showno = false;
    this.toaster.info("Scroll down to update department description","Info");
  }

  updateDescription(departmentName: string, value: string) {
    return this.service.updateDescription(departmentName, value).subscribe((data) => {
      this.updateDescObj = data;
      this.showdesctable = true;
      this.toaster.success("Department description updated successfully","Success");
    },
    (err)=>{
      this.toaster.error("Invalid inputs","Error");
    })
  }

  updateNoOfEmpObj!: Department;
  showno = false;
  shownotable = false;
  no_name!:string;
  no!:number;

  showdiv4() {
    this.showno = true;
    this.show = false;
    this.showdepartment = false;
    this.showdesc = false;
    this.toaster.info("Scroll down to update no of employees in a department","Info");
  }

  updateNoOfEmployees(departmentName: string, value: number) {
    return this.service.updateNoOfEmployees(departmentName, value).subscribe((data) => {
      this.updateNoOfEmpObj = data;
      this.shownotable = true;
      this.toaster.success("Department description updated successfully","Success");
    },
    (err)=>{
      this.toaster.error("Invalid inputs","Error");
    })
  }


}
