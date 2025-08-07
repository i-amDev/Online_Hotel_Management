import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-receptionist',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './receptionist.component.html',
  styleUrl: './receptionist.component.css'
})
export class ReceptionistComponent {

  constructor(private routes:Router,private toaster:ToastrService){}

  ngOnInit() {
    if(sessionStorage.getItem("role")!=="RECEPTIONIST") {
      this.toaster.error("Can't access receptionist profile","Error");
      this.logout();
    }
  }

  logout() {
    sessionStorage.clear();
    this.routes.navigate(['/login']);
    this.toaster.info("Receptionist logged out","Info");
  }

}
