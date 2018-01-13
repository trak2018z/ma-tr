import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {Note} from "../../dashboard.service";

@Component({
  selector: 'app-note-dialog',
  templateUrl: './note-dialog.component.html'
})
export class NoteDialogComponent {

  note: Note = <any> {};

  constructor(public dialogRef: MatDialogRef<NoteDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Note) {
    this.note = data;
  }

  save() {
    this.dialogRef.close(this.note);
  }
}


