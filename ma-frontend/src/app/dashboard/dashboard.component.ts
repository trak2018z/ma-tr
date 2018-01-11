import {Component, OnInit} from '@angular/core';
import {AuthService} from "../common/auth.service";
import {Dashboard, DashboardService} from "./dashboard.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  dashboard: Dashboard

  constructor(public authService: AuthService, private dashboardService: DashboardService) {

  }

  ngOnInit() {
    this.dashboardService.getDashboard().subscribe(d => {
      this.dashboard = d;
    })
  }

}
