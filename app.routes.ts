import { Routes } from '@angular/router';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { SignupPageComponent } from './components/signup-page/signup-page.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { DepartmentComponent } from './components/department/department.component';
import { GuestComponent } from './components/guest/guest.component';
import { ReceptionistComponent } from './components/receptionist/receptionist.component';
import { OwnerComponent } from './components/owner/owner.component';
import { ManagerComponent } from './components/manager/manager.component';
import { UsersComponent } from './components/users/users.component';
import { ReservationComponent } from './components/reservation/reservation.component';
import { AddreservationComponent } from './components/reservation/addreservation/addreservation.component';
import { AddroomComponent } from './components/room/addroom/addroom.component';
import { ViewroomComponent } from './components/room/viewroom/viewroom.component';
import { AddguestComponent } from './components/guest/addguest/addguest.component';
import { ViewguestComponent } from './components/guest/viewguest/viewguest.component';
import { ViewreservationComponent } from './components/reservation/viewreservation/viewreservation.component';
import { AdddepartmentComponent } from './components/department/adddepartment/adddepartment.component';
import { ViewdepartmentComponent } from './components/department/viewdepartment/viewdepartment.component';
import { AddstaffComponent } from './components/staff/addstaff/addstaff.component';
import { ViewstaffComponent } from './components/staff/viewstaff/viewstaff.component';
import { RoomforuserComponent } from './components/roomforuser/roomforuser.component';
import { authGuard } from './auth/auth.guard';

export const routes: Routes = [
    {path:'login',component:LoginPageComponent},
    {path:'signup',component:SignupPageComponent},
    {path:'home',component:HomePageComponent},
    {path:'department',component:DepartmentComponent,canActivate:[authGuard]},
    {path:'guest',component:GuestComponent,canActivate:[authGuard]},
    {path:'receptionist',component:ReceptionistComponent,canActivate:[authGuard]},
    {path:'owner',component:OwnerComponent,canActivate:[authGuard]},
    {path:'manager',component:ManagerComponent,canActivate:[authGuard]},
    {path:'users',component:UsersComponent,canActivate:[authGuard]},
    {path:'reservation',component:ReservationComponent,canActivate:[authGuard]},
    {path:'addreservation',component:AddreservationComponent,canActivate:[authGuard]},
    {path:'viewreservation',component:ViewreservationComponent,canActivate:[authGuard]},
    {path:'addroom',component:AddroomComponent,canActivate:[authGuard]},
    {path:'viewroom',component:ViewroomComponent,canActivate:[authGuard]},
    {path:'addguest',component:AddguestComponent,canActivate:[authGuard]},
    {path:'viewguest',component:ViewguestComponent,canActivate:[authGuard]},
    {path:'adddepartment',component:AdddepartmentComponent,canActivate:[authGuard]},
    {path:'viewdepartment',component:ViewdepartmentComponent,canActivate:[authGuard]},
    {path:'addstaff',component:AddstaffComponent,canActivate:[authGuard]},
    {path:'viewstaff',component:ViewstaffComponent,canActivate:[authGuard]},
    {path:'roomforuser',component:RoomforuserComponent,canActivate:[authGuard]},
    {path:'',component:HomePageComponent}  
];
