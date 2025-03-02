package com.demo.tx.dto;

import com.demo.tx.entity.PassengerInfo;
import com.demo.tx.entity.PaymentInfo;

public class FlightBookingRequest {
      private PassengerInfo passengerInfo;
      private PaymentInfo paymentInfo;

      public FlightBookingRequest() {
      }

      public FlightBookingRequest(PassengerInfo passengerInfo, PaymentInfo paymentInfo) {
            this.passengerInfo = passengerInfo;
            this.paymentInfo = paymentInfo;
      }

      public PassengerInfo getPassengerInfo() {
            return passengerInfo;
      }

      public void setPassengerInfo(PassengerInfo passengerInfo) {
            this.passengerInfo = passengerInfo;
      }

      public PaymentInfo getPaymentInfo() {
            return paymentInfo;
      }

      public void setPaymentInfo(PaymentInfo paymentInfo) {
            this.paymentInfo = paymentInfo;
      }

      @Override
      public String toString() {
            return "FlightBookingRequest{" +
                    "passengerInfo=" + passengerInfo +
                    ", paymentInfo=" + paymentInfo +
                    '}';
      }
}
