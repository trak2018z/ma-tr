import {Component, HostListener, OnInit} from '@angular/core';
import {AuthService} from "../common/auth.service";
import {MatSnackBar} from "@angular/material";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;

  constructor(private authService: AuthService, private router: Router, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
  }

  @HostListener('document:keydown.enter', ['$event'])
  login() {
    console.log(this.username, this.password)
    this.authService.login(this.username, this.password).subscribe(r => {
      this.authService.setToken(r.token);
      this.router.navigate(['/']);
    }, e => {
      this.snackBar.open("Wrong username or password!", null, {
        duration: 3000,
      });
    })

  }

}
