import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-owner',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './owner.component.html',
  styleUrl: './owner.component.css'
})
export class OwnerComponent {

  constructor(private routes:Router,private toaster:ToastrService){}

  ngOnInit() {
    if(sessionStorage.getItem("role")!=="OWNER") {
      this.toaster.error("Can't access owner profile","Error");
      this.logout();
    }
  }

  logout() {
    sessionStorage.clear();
    this.routes.navigate(['/login']);
    this.toaster.info("Owner logged out ","Info");
  }

}
