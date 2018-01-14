import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {CanActivate, Router} from "@angular/router";

var JWT_LS_KEY = "ab2sda";

interface AuthResponse {
  token: string;
}


@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private router: Router) {
  }

  canActivate() {
    if (!AuthService.getToken()) {
      this.router.navigate(["/login"]);
    }
    return true;
  }
}

@Injectable()
export class AuthService {

  username: string = 'unknown';

  constructor(private httpClient: HttpClient, private router: Router) {
    this.parseUsernameFromToken();
  }

  login(username: string, password: string): Observable<AuthResponse> {
    return this.httpClient.post<AuthResponse>("/api/auth", {username: username, password: password});
  }

  logout() {
    window.localStorage.removeItem(JWT_LS_KEY);
    this.router.navigate(["/login"]);
  }

  parseUsernameFromToken() {
    let token = AuthService.getToken();
    if (token) {
      let slitted = token.split(".");
      let userData = JSON.parse(atob(slitted[1]));
      this.username = userData.username;
    } else {
      this.router.navigate(["/login"]);
    }
  }


  setToken(token: string) {
    window.localStorage.setItem(JWT_LS_KEY, token);
    this.parseUsernameFromToken();
  }

  static getToken(): string {
    return window.localStorage.getItem(JWT_LS_KEY);
  }

}
