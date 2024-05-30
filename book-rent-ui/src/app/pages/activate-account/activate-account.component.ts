import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthentificationService } from 'src/app/services/services';

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrls: ['./activate-account.component.scss'],
})
export class ActivateAccountComponent {
  message: string = '';
  isOk: boolean = true;
  submitted: boolean = false;
  constructor(
    private router: Router,
    private authService: AuthentificationService
  ) {}

  onCodeCompleted(token: string) {
    this.confirmAccount(token);
  }
  confirmAccount(token: string) {
    this.authService.confirm({ token }).subscribe({
      next: () => {
        this.message = 'Yout account has been succesfully activated';
        this.submitted = true;
        this.isOk = true;
      },
      error: () => {
        this.message = 'Token has been expired';
        this.submitted = true;
        this.isOk = false;
      },
    });
  }
  redirectToLogin() {
    this.router.navigate(['login']);
  }
}
