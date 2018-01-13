import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {Contact} from "../../dashboard.service";

@Component({
  selector: 'app-contact-dialog',
  templateUrl: './contact-dialog.component.html'
})
export class ContactDialogComponent {

  contact: Contact = <any> {};

  constructor(public dialogRef: MatDialogRef<ContactDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Contact) {
    this.contact = data;
  }

  save() {
    this.dialogRef.close(this.contact);
  }
}


