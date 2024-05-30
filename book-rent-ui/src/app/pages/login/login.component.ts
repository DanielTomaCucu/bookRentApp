import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticateRequest } from 'src/app/services/models';
import { AuthentificationService } from 'src/app/services/services';
import { TokenService } from 'src/app/services/token/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  constructor(
    private router: Router,
    private authService: AuthentificationService,
    private tokenService: TokenService
  ) {}
  authRequest: AuthenticateRequest = { email: '', password: '' };
  errorMsg: Array<string> = [];

  login() {
    this.errorMsg = [];
    this.authService
      .authenticate({
        body: this.authRequest,
      })
      .subscribe({
        next: (res) => {
          this.tokenService.token = res.token as string;
          this.router.navigate(['books']);
        },
        error: (err) => {
          console.log(err);
          if (err.error.validationErrors) {
            this.errorMsg = err.error.validationErrors;
          } else {
            this.errorMsg.push(err.error.error);
          }
        },
      });
  }

  register() {
    this.router.navigate(['register']);
  }
}
