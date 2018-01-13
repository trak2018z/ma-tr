import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {FileService} from "../../file.service";
import {Dashboard} from "../../dashboard.service";

@Component({
  selector: 'app-file-dialog',
  templateUrl: './file-dialog.component.html',
  styleUrls: ['./file-dialog.component.scss']
})
export class FileDialogComponent {

  dashboardUrl: string;
  files: File[] = [];
  sending: boolean;
  progress = 0;

  constructor(public dialogRef: MatDialogRef<FileDialogComponent>,
              private fileService: FileService,
              @Inject(MAT_DIALOG_DATA) public data: Dashboard) {
    this.dashboardUrl = data._links ? data._links.self.href : '';
  }

  fileChange(event) {
    this.files = event.target.files;
    console.log(this.files);
  }

  send() {
    this.sending = true;
    for (let i = 0; i < this.files.length; i++) {
      let file = this.files[i];
      this.fileService.upload(this.dashboardUrl, file, (progress) => {
        this.progress = progress;
      }).then(fileMetadata => {
        console.log(fileMetadata);
        this.dialogRef.close();
      });
    }
  }

}


