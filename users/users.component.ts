import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent {

  constructor(private routes:Router,private toaster:ToastrService){}

  ngOnInit() {
    if(sessionStorage.getItem("role")!=="USER") {
      this.toaster.error("Can't access manager profile","Error");
      this.logout();
    }
  }

  logout() {
    sessionStorage.clear();
    this.routes.navigate(['/login']);
    this.toaster.info("User logged out","Info");
  }

}
