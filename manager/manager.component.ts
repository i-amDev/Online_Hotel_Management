import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-manager',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './manager.component.html',
  styleUrl: './manager.component.css'
})
export class ManagerComponent {

  constructor(private routes:Router,private toaster:ToastrService){}

  ngOnInit() {
    if(sessionStorage.getItem("role")!=="MANAGER") {
      this.toaster.error("Can't access manager profile","Error");
      this.logout();
    }
  }

  logout() {
    sessionStorage.clear();
    this.routes.navigate(['/login']);
    this.toaster.info("Manager logged out","Info");
  }

}
