export class User {
    public userID: number;
    public username: string;
    public password: string;
    public email: string;

    constructor(username: string, password: string, email: string) {
        this.userID = 0;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}