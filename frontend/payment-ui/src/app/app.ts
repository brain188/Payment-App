import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PaymentForm } from './components/payment-form/payment-form';
import { PaymentResponse } from './models/payment.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, PaymentForm],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('payment-ui');
  view: 'landing' | 'portal' | 'result' = 'landing';
  lastResult: PaymentResponse | null = null;
  today = new Date();

  abs(val: number): number {
    return Math.abs(val);
  }

  showPortal() {
    this.view = 'portal';
    window.scrollTo(0, 0);
  }

  showLanding() {
    this.view = 'landing';
    this.lastResult = null;
    window.scrollTo(0, 0);
  }

  handlePaymentSuccess(result: PaymentResponse) {
    this.lastResult = result;
    this.view = 'result';
    window.scrollTo(0, 0);
  }

}
