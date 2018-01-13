import {Component, OnInit, ViewChild} from '@angular/core';
import {AuthService} from "../common/auth.service";
import {Contact, Dashboard, DashboardService, File, Note} from "./dashboard.service";
import {MatDialog, MatSnackBar, MatTabGroup} from "@angular/material";
import {NoteDialogComponent} from "./dialogs/note-dialog/note-dialog.component";
import {ContactDialogComponent} from "./dialogs/contact-dialog/contact-dialog.component";
import {FileDialogComponent} from "./dialogs/file-dialog/file-dialog.component";
import {DomSanitizer} from "@angular/platform-browser";

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

  sanitize(file: File) {
    let image = 'data:' + file.contentType + ';base64,' + file.thumbNail;
    return this.sanitizer.bypassSecurityTrustResourceUrl(image);
  }

  removeElement(array: any[], element: any) {
    const index = array.indexOf(element);
    if (index > -1) {
      array.splice(index, 1);
    }
  }

  showNoteDialog(note?: Note) {
    let dialogRef = this.dialog.open(NoteDialogComponent, {
      data: note ? note : {}
    });
    dialogRef.afterClosed().subscribe(n => {
      if (n) this.dashboard.notes.push(n);
    })
  }

  showContactDialog(contact?: Contact) {
    let dialogRef = this.dialog.open(ContactDialogComponent, {
      data: contact ? contact : {}
    });
    dialogRef.afterClosed().subscribe(c => {
      if (c) this.dashboard.contacts.push(c);
    })
  }

  showFileDialog() {
    let dialogRef = this.dialog.open(FileDialogComponent, {
      data: this.dashboard
    });
  }
}
