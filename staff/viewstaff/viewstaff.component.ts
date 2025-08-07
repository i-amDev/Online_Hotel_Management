import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { StaffService } from '../../../service/staff/staff.service';
import { Staff } from '../addstaff/staff.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-viewstaff',
  standalone: true,
  imports: [RouterLink,FormsModule,CommonModule],
  templateUrl: './viewstaff.component.html',
  styleUrl: './viewstaff.component.css'
})
export class ViewstaffComponent {

  constructor(private service:StaffService,private routes:Router,private toaster:ToastrService){}

  logout() {
    sessionStorage.clear();
    this.routes.navigate(['/login']);
  }

  staffArr:Staff[] = []
  show = false;

  getAll() {
    return this.service.getAll().subscribe((data)=>{
      this.staffArr = data;
      this.show = true;
      console.log(this.staffArr);
      this.showaddress = false;
      this.showsalary = false;
      this.showname = false;
      this.showemail = false;
      this.toaster.info("Staff list fetched","Info");
    },
    (err)=>{
      this.toaster.error(err.error.message,"Error");
    })
  }

  deleteMsg!:string;

  deleteStaff(email:string) {
    return this.service.deleteStaff(email).subscribe((data)=>{
      this.deleteMsg = data;
      this.toaster.success(this.deleteMsg,"Success");
    })
  }

  emailObj!:Staff;
  search_email!:string;
  showemail = false;
  showemailtable = false;
  showdiv2() {
    this.showemail = true;
    this.show = false;
    this.showaddress = false;
    this.showsalary = false;
    this.showname = false;
    this.toaster.info("Scroll down to fetch staff using email","Info");
  }

  getByEmail(email:string) {
    return this.service.getByEmail(email).subscribe((data)=>{
      this.emailObj = data;
      this.showemailtable = true;
      this.toaster.success("Staff fetched successsfully for the email " + email,"Success");
    },
    (err)=>{
      this.toaster.error("Invalid inputs","Error");
    })
  }

  salaryObj!:Staff;
  salary_email!:string;
  new_salary!:number;
  showsalary = false;
  showsalarytable = false;

  showdiv4() {
    this.showsalary = true;
    this.show = false;
    this.showname = false;
    this.showemail = false;
    this.showaddress = false;
    this.toaster.info("Scroll down to update staff salary","Info");
  }
  
  updateSalary(email:string,value:number) {
    return this.service.updateSalary(email,value).subscribe((data)=>{
      this.salaryObj = data;
      this.showsalarytable = true;
      this.toaster.success("Staff salary updated successfully","Success");
    },
    (err)=>{
      this.toaster.error("Invalid inputs","Error");
    })
  }

  nameObj!:Staff;
  name_email!:string;
  updatename!:string;
  showname = false;
  shownametable = false;

  showdiv3() {
    this.showname = true;
    this.showsalary = false;
    this.show = false;
    this.showemail = false;
    this.showaddress = false;
    this.toaster.info("Scroll down to update staff name","Info");
  }


  updateName(email:string,value:string) {
    return this.service.updateName(email,value).subscribe((data)=>{
      this.nameObj = data;
      this.staffArr.push(data);
      console.log(this.nameObj);
      this.shownametable = true;
      this.toaster.success("Staff name updated successfully","Success");
    },
    (err)=>{
      this.toaster.error("Invalid inputs","Error");
    })
  }

  addressObj!:Staff;
  address_email!:string;
  new_address!:string;
  showaddress = false;
  showaddresstable = false;

  showdiv5() {
    this.showaddress = true;
    this.showsalary = false;
    this.show = false;
    this.showname = false;
    this.showemail = false;
    this.toaster.info("Scroll down to update staff address","Info");
  }

  updateAddress(email:string,value:string) {
    return this.service.updateAddress(email,value).subscribe((data)=>{
      this.addressObj = data;
      this.showaddresstable = true;
      this.toaster.success("Staff address updated successfully","Success");
    },
    (err)=>{
      this.toaster.error("Invalid inputs","Error");
    })
  }

}
