export class Customer {

    username!:string;
    password!:string;
    email!:string;
    address!:string;
    phoneNumber!:number;


    constructor(username: string, password: string, email: string, address: string, phoneNumber: number) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
}