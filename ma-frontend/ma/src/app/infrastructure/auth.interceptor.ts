import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import 'rxjs/add/observable/throw'
import 'rxjs/add/operator/catch';
import {AuthService} from "../common/auth.service";


@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor() {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let token = AuthService.getToken();
    if (token) {
      const authReq = req.clone({headers: req.headers.set("Authentication", token)});
      return next.handle(authReq)
    } else {
      // TODO: redirect
      return next.handle(req);
    }
  }
}
