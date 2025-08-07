import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { DepartmentService } from '../../../service/department/department.service';
import { Department } from './department.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-adddepartment',
  standalone: true,
  imports: [RouterLink,FormsModule,CommonModule],
  templateUrl: './adddepartment.component.html',
  styleUrl: './adddepartment.component.css'
})
export class AdddepartmentComponent {

  constructor(private service:DepartmentService,private routes:Router,private toaster:ToastrService){}

  id!:string;
  name!:string;
  description!:string;
  noOfEmp!:number;
  department!:Department;

  departmentArr:Department[] = [];

  addDepartment() {
    this.department = new Department(this.id,this.name,this.description,this.noOfEmp);
    return this.service.addDepartment(this.department).subscribe((data)=>{
      this.departmentArr.push(data);
      this.routes.navigate(['/viewdepartment']);
      this.toaster.success("Department added successfully","Success");
    },
    (err)=>{
      this.toaster.error(err.error,"Error");
      console.log(err.error);
    })

  }

}
