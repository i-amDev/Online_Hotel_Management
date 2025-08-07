export class AddReservation{
    constructor(public bookingId:string,public name:string,public age:number,public address:string,public phoneNumber:number,public noOfGuests:number,public noOfRooms:number,public status:string,public checkInDate:Date,public checkOutDate:Date,public noOfNights:number,public roomType:string,public billAmount:number){}
}