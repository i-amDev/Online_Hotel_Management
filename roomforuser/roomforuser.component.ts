import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-roomforuser',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './roomforuser.component.html',
  styleUrl: './roomforuser.component.css'
})
export class RoomforuserComponent {

  constructor(private routes:Router){}

  logout() {
    sessionStorage.clear();
    this.routes.navigate(['/login']);
  }

}
