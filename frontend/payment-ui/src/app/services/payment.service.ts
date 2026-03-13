import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PaymentRequest, PaymentResponse } from '../models/payment.model';
import { environment } from '../../environments/environment';

/**
 * Service responsible for communicating with the Payment API.
 */
@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private apiUrl = `${environment.apiUrl}/one-time-fee-payment`;

  constructor(private http: HttpClient) { }

  /**
   * Processes a fee payment by sending a POST request to the backend.
   * @param request The payment details including student number and amount.
   * @returns An Observable of the payment success response.
   */
  processPayment(request: PaymentRequest): Observable<PaymentResponse> {
    return this.http.post<PaymentResponse>(this.apiUrl, request);
  }
}
