import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RegistrationRequest } from 'src/app/services/models';
import { AuthentificationService } from 'src/app/services/services';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  constructor(
    private router: Router,
    private authService: AuthentificationService
  ) {}
  registerRequest: RegistrationRequest = {
    email: '',
    firstname: '',
    lastname: '',
    password: '',
  };
  errorMsg: Array<string> = [];

  register() {
    this.errorMsg = [];
    this.authService
      .register({
        body: this.registerRequest,
      })
      .subscribe({
        next: () => {
          this.router.navigate(['activate-account']);
        },
        error: (err) => {
          this.errorMsg = err.error.validationErrors;
        },
      });
  }
  login() {
    this.router.navigate(['login']);
  }
}
