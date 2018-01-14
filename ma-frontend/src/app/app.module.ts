import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {AuthGuard, AuthService} from './common/auth.service';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AuthInterceptor} from "./infrastructure/auth.interceptor";
import {AppRoutingModule} from './app-routing.module';
import {LoginComponent} from './login/login.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {
  MatButtonModule,
  MatCardModule,
  MatDialogModule,
  MatExpansionModule,
  MatFormFieldModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatSnackBarModule,
  MatTabsModule,
  MatToolbarModule,
  MatTooltipModule
} from "@angular/material";
import {FormsModule} from "@angular/forms";
import {DashboardService} from "./dashboard/dashboard.service";
import {NoteDialogComponent} from './dashboard/dialogs/note-dialog/note-dialog.component';
import {ContactDialogComponent} from "./dashboard/dialogs/contact-dialog/contact-dialog.component";
import {FileDialogComponent} from "./dashboard/dialogs/file-dialog/file-dialog.component";
import {FileService} from "./dashboard/file.service";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    NoteDialogComponent,
    ContactDialogComponent,
    FileDialogComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatGridListModule,
    MatCardModule,
    MatButtonModule,
    MatToolbarModule,
    MatInputModule,
    MatIconModule,
    MatFormFieldModule,
    MatSnackBarModule,
    MatExpansionModule,
    MatTabsModule,
    MatListModule,
    MatDialogModule,
    MatTooltipModule,
    MatProgressSpinnerModule,
    MatProgressBarModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  entryComponents: [NoteDialogComponent, ContactDialogComponent, FileDialogComponent],
  providers: [AuthService, AuthGuard, DashboardService, FileService, {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
