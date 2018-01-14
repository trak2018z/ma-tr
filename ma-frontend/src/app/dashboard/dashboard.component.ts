import {Component, OnInit, ViewChild} from '@angular/core';
import {AuthService} from "../common/auth.service";
import {Contact, Dashboard, DashboardService, Note} from "./dashboard.service";
import {MatDialog, MatSnackBar, MatTabGroup} from "@angular/material";
import {NoteDialogComponent} from "./dialogs/note-dialog/note-dialog.component";
import {ContactDialogComponent} from "./dialogs/contact-dialog/contact-dialog.component";
import {FileDialogComponent} from "./dialogs/file-dialog/file-dialog.component";
import {DomSanitizer} from "@angular/platform-browser";
import {FileMetadata, FileService} from "./file.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {


  @ViewChild("tabGroup") tabGroup: MatTabGroup;
  tabIndex: number = 0;
  nameEdited: boolean;
  greetingMessageEdited: boolean;


  dashboard: Dashboard = <any>{};

  constructor(public authService: AuthService,
              private fileService: FileService,
              private dashboardService: DashboardService,
              private sanitizer: DomSanitizer,
              private snackBar: MatSnackBar,
              public dialog: MatDialog) {

  }

  ngOnInit() {
    this.loadData();
  }

  loadData() {
    this.dashboardService.getDashboard().subscribe(d => {
      this.dashboard = d;
    }, e => {
      this.dashboard = {
        name: "Dashboard",
        greetingMessage: 'Witaj' + this.authService.username + "!",
        notes: [],
        contacts: [],
        files: [],
      };
      this.save();
    });
  }

  onTabChange() {
    this.tabIndex = this.tabGroup.selectedIndex;
  }

  save() {
    this.nameEdited = false;
    this.greetingMessageEdited = false;
    this.dashboardService.updateDashboard(this.dashboard).subscribe(d => {
      this.dashboard = d;
      this.snackBar.open("Dashboard został zapisany!", null, {
        duration: 3000,
      });
    });
  }

  sanitize(file: FileMetadata) {
    let image = 'data:' + file.contentType + ';base64,' + file.thumbNail;
    return this.sanitizer.bypassSecurityTrustResourceUrl(image);
  }

  download(file: FileMetadata) {
    this.fileService.download(file);
  }

  remove(file: FileMetadata) {
    this.fileService.remove(this.dashboard._links.self.href, file).subscribe(r => {
      this.removeElement(this.dashboard.files, file);
    });
  }

  removeElement(array: any[], element: any) {
    const index = array.indexOf(element);
    if (index > -1) {
      array.splice(index, 1);
      this.save();
    }
  }

  showNoteDialog(note?: Note) {
    let dialogRef = this.dialog.open(NoteDialogComponent, {
      data: note ? note : {}
    });
    dialogRef.afterClosed().subscribe(n => {
      if (n) {
        this.dashboard.notes.push(n);
        this.save();
      }
    })
  }

  showContactDialog(contact?: Contact) {
    let dialogRef = this.dialog.open(ContactDialogComponent, {
      data: contact ? contact : {}
    });
    dialogRef.afterClosed().subscribe(c => {
      if (c) {
        this.dashboard.contacts.push(c);
        this.save();
      }
    })
  }

  showFileDialog() {
    let dialogRef = this.dialog.open(FileDialogComponent, {
      data: this.dashboard
    });
    dialogRef.afterClosed().subscribe(f => {
      if (f) this.dashboard.files.push(f);
    })
  }
}
