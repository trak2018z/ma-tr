import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

var JWT_LS_KEY = "ab2sda";

interface AuthResponse {
  token: string;
}

@Injectable()
export class AuthService {

  constructor(private httpClient: HttpClient) {
    console.log(AuthService.getToken());
  }

  login(username: string, password: string): Observable<AuthResponse> {
    return this.httpClient.post<AuthResponse>("/api/auth", {username: username, password: password});
  }

  static getToken(): string {
    return window.localStorage.getItem(JWT_LS_KEY);
  }

  static setToken(token: string) {
    window.localStorage.setItem(JWT_LS_KEY, token);
  }

}
