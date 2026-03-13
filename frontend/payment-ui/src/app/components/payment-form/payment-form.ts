import { ChangeDetectorRef, Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PaymentService } from '../../services/payment.service';
import { PaymentResponse } from '../../models/payment.model';
import { HttpErrorResponse } from '@angular/common/http';

/**
 * Component for the Student Fee Payment Form.
 * Handles user input, form validation, and payment submission.
 */
@Component({
  selector: 'app-payment-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './payment-form.html',
  styleUrl: './payment-form.css',
})
export class PaymentForm {
  paymentForm: FormGroup;
  paymentResult: PaymentResponse | null = null;
  errorMessage: string | null = null;
  isLoading = false;
  submitted = false;
  today = new Date().toISOString().split('T')[0];

  @Output() paymentSuccess = new EventEmitter<PaymentResponse>();

  constructor(
    private fb: FormBuilder,
    private paymentService: PaymentService,
    private cdr: ChangeDetectorRef
  ) {
    this.paymentForm = this.fb.group({
      studentNumber: ['', [Validators.required]],
      paymentAmount: ['', [Validators.required, Validators.min(0.01)]],
      paymentDate: ['']
    });
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.paymentForm.valid) {
      this.isLoading = true;
      this.errorMessage = null;
      this.paymentResult = null;

      this.paymentService.processPayment(this.paymentForm.value).subscribe({
        next: (response) => {
          this.paymentResult = response;
          this.isLoading = false;
          this.paymentSuccess.emit(response);
          this.cdr.detectChanges();
        },
        error: (error: HttpErrorResponse) => {
          console.error('Payment error:', error);
          
          // Handle various error formats
          if (error.error && typeof error.error === 'object') {
            this.errorMessage = error.error.error || error.error.message || JSON.stringify(error.error);
          } else {
            this.errorMessage = error.message || 'An unexpected error occurred. Please try again.';
          }
          
          this.isLoading = false;
          this.cdr.detectChanges();
          
          // Scroll to top of form to see the error message
          window.scrollTo({ top: 0, behavior: 'smooth' });
        }
      });
    } else {
      this.paymentForm.markAllAsTouched();
    }
  }

  resetForm(): void {
    this.paymentForm.reset();
    this.submitted = false;
    this.paymentResult = null;
    this.errorMessage = null;
  }
}
