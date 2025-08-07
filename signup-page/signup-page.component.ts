import { Component } from '@angular/core';
import { Customer } from './signup-page.model';
import { SignupServiceService } from '../../service/signup-service.service';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-signup-page',
  standalone: true,
  imports: [FormsModule,RouterLink],
  templateUrl: './signup-page.component.html',
  styleUrl: './signup-page.component.css'
})
export class SignupPageComponent {

  username!:string;
  password!:string;
  email!:string;
  address!:string;
  phone!:number;

  customer!:Customer;
  
  message!:string;
  
  constructor(private signupservice:SignupServiceService,private toaster:ToastrService,private routes:Router){
    
  }
  
  addUser() {
    this.customer = new Customer(this.username,this.password,this.email,this.address,this.phone);
    this.signupservice.addUser(this.customer).subscribe((res:string) => {
      this.message = res;
      console.log(this.message);
      this.toaster.success("User added successfully. Try logging in.","Success");
      this.routes.navigate(['/login']);
    },
    (err)=>{
      this.message = err.error;
      this.toaster.error(this.message,"Error");
    })
  }

}
