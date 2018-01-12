import {Component, OnInit, ViewChild} from '@angular/core';
import {AuthService} from "../common/auth.service";
import {Dashboard, DashboardService, Note} from "./dashboard.service";
import {MatDialog, MatTabGroup} from "@angular/material";
import {NoteDialogComponent} from "./dialogs/note-dialog/note-dialog.component";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {


  @ViewChild("tabGroup") tabGroup: MatTabGroup;
  tabIndex: number = 0;
  dashboard: Dashboard = <any>{};

  constructor(public authService: AuthService,
              private dashboardService: DashboardService,
              public dialog: MatDialog) {

  }

  ngOnInit() {
    this.dashboardService.getDashboard().subscribe(d => {
      this.dashboard = d;
    })
  }


  onTabChange() {
    this.tabIndex = this.tabGroup.selectedIndex;
  }

  showNoteDialog(note?: Note) {
    let dialogRef = this.dialog.open(NoteDialogComponent, {
      data: {note}
    });
  }

}
