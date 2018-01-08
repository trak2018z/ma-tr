import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../common/auth.service";

export interface Note {
  title: string;
  description: string;
}

export interface Address {
  city: string;
  street: string;
  detailed: string;
}

export interface Contact {
  firstName: string;
  lastName: string;
  phoneNumber: string;
  description: string;
  address: Address;
  username?: any;
}

export interface File {
  id: string;
  type: string;
  thumbNail: string;
  uploadDate: Date;
}

export interface Dashboard {
  id: string;
  createdDate: Date;
  lastModifiedDate: Date;
  name: string;
  greetingMessage: string;
  notes: Note[];
  contacts: Contact[];
  files: File[];
}

@Injectable()
export class DashboardService {

  constructor(private httpClient: HttpClient, private authService: AuthService) {
  }

  getDashboard() {
    return this.httpClient.get<Dashboard>("/api/dashboards/findByUsername/" + this.authService.username);
  }

  updateDashboard(dashboard: Dashboard) {
    return this.httpClient.put<Dashboard>("/api/dashboards/" + dashboard.id, dashboard);
  }
}



