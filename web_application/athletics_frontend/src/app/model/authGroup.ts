export class AuthGroup {
  idGroup: number;
  groupName: string;
  groupDescription: string;


  constructor(obj: any) {
    this.idGroup = obj.idGroup;
    this.groupName = obj.groupName;
    this.groupDescription = obj.groupDescription;
  }
}
