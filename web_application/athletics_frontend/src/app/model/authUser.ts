import { AuthGroup } from './authGroup';

export class AuthUser {
  idUser: number;
  taxCode: string;
  name: string;
  surname: string;
  username: string;
  email: string;
  group: AuthGroup[];

  constructor(obj: any) {
    this.idUser = obj.idUser;
    this.taxCode = obj.taxCode;
    this.name = obj.name;
    this.surname = obj.surname;
    this.username = obj.username;
    this.email = obj.email;
    this.group = obj.group;
    }
}
