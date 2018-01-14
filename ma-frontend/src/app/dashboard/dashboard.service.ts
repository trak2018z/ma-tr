import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../common/auth.service";
import {FileMetadata} from "./file.service";

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

export interface Dashboard {
  id?: string;
  createdDate?: Date;
  lastModifiedDate?: Date;
  name: string;
  greetingMessage: string;
  notes: Note[];
  contacts: Contact[];
  files: FileMetadata[];
  _links?: Links;
}

export interface Links {
  self: SelfOrDashboard;
  dashboard: SelfOrDashboard;
}

export interface SelfOrDashboard {
  href: string;
}


@Injectable()
export class DashboardService {

  baseUrl = '/api/dashboards/';

  constructor(private httpClient: HttpClient, private authService: AuthService) {
  }

  getDashboard() {
    return this.httpClient.get<Dashboard>(this.baseUrl + 'search/findByUsername?username=' + this.authService.username);
  }

  updateDashboard(dashboard: Dashboard) {
    if (dashboard._links) {
      return this.httpClient.put<Dashboard>(dashboard._links.self.href, dashboard);
    } else {
      return this.httpClient.post<Dashboard>(this.baseUrl, dashboard);
    }
  }
}



