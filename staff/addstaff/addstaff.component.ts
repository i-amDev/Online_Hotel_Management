import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { StaffService } from '../../../service/staff/staff.service';
import { Staff } from './staff.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-addstaff',
  standalone: true,
  imports: [RouterLink,FormsModule,CommonModule],
  templateUrl: './addstaff.component.html',
  styleUrl: './addstaff.component.css'
})
export class AddstaffComponent {

  constructor(private service:StaffService,private toaster:ToastrService,private routes:Router){}

  id!:string;
  name!:string;
  gender!:string;
  email!:string;
  address!:string;
  post!:string;
  salary!:number;

  staffObj!:Staff;

  staffArr:Staff[] = [];

  addStaff() {
    this.staffObj = new Staff(this.id,this.name,this.gender,this.email,this.address,this.post,this.salary);
    return this.service.addStaff(this.staffObj).subscribe((data)=>{
      this.staffArr.push(data);
      this.toaster.success("Staff added successfully","Success");
      this.routes.navigate(['/viewstaff']);
    },
    (err)=>{
      this.toaster.error(err.error,"Error");
    })
  }

}
