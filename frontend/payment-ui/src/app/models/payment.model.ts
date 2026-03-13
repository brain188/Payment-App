export interface PaymentRequest {
  studentNumber: string;
  paymentAmount: number;
  paymentDate?: string;
}

export interface PaymentResponse {
  studentNumber: string;
  previousBalance: number;
  paymentAmount: number;
  incentiveRate: number;
  incentiveAmount: number;
  newBalance: number;
  nextDueDate: string;
}
