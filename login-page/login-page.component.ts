import { Component } from '@angular/core';
import { AuthRequest } from './login-page.model';
import { FormsModule } from '@angular/forms';
import { LoginServiceService } from '../../service/login-service.service';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { jwtDecode } from 'jwt-decode';
import { TokenDetails } from './login.model';
import { UserDetails } from './userdetail.model';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [FormsModule,RouterLink,RouterOutlet],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})
export class LoginPageComponent {

  auth:AuthRequest = new AuthRequest("","");

  token!:string;
  message!:string;

  tokenvalue!:any;

  userdetails!:any;

  constructor(private loginservice:LoginServiceService,private routes:Router,private toaster:ToastrService){
    
  }

  getToken() {
    this.loginservice.getToken(this.auth).subscribe((res:string) => {
      this.token = res;
      this.tokenvalue = jwtDecode(res);
      if(this.tokenvalue.ROLE=="RECEPTIONIST") {
        this.routes.navigate(['/receptionist']);
      }
      else if(this.tokenvalue.ROLE==="OWNER") {
        this.routes.navigate(['/owner']);
      }
      else if(this.tokenvalue.ROLE==="MANAGER") {
        this.routes.navigate(['/manager']);
      }
      else{
        this.routes.navigate(['/users']);
      }
      this.loginservice.getUser(this.tokenvalue.sub).subscribe((data)=>{
        this.userdetails = data;
        console.log(this.userdetails);
        this.toaster.success("Login Successful","Success");
      })
      console.log(this.tokenvalue);
      sessionStorage.setItem("token",this.token);
      sessionStorage.setItem("role",this.tokenvalue.ROLE);
      console.log(this.token);
    },(error)=>{
      this.toaster.error("Invalid username and password","Error");
    })
  }


}
