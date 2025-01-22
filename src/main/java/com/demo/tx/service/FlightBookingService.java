package com.demo.tx.service;

import com.demo.tx.dto.FlightBookingAcknowledgement;
import com.demo.tx.dto.FlightBookingRequest;
import com.demo.tx.entity.PassengerInfo;
import com.demo.tx.entity.PaymentInfo;
import com.demo.tx.repository.PassengerInfoRepository;
import com.demo.tx.repository.PaymentInfoRepository;
import com.demo.tx.utils.PaymemntUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class FlightBookingService {
    @Autowired
    private PassengerInfoRepository passengerInfoRepository;
    @Autowired
    private PaymentInfoRepository paymentInfoRepository;

    private static final Double GST = 125.67;

    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public FlightBookingAcknowledgement bookFlightTicket(FlightBookingRequest request){
        //testExceptions();
        PassengerInfo passengerInfo = request.getPassengerInfo();
        PaymentInfo paymentInfo = request.getPaymentInfo();

        //SAVE PASSENGER INFO TO PASSENGER_INFO TABLE
        PassengerInfo savedPassengerInfo = passengerInfoRepository.save(passengerInfo);

        //VALIDATE PAYMENT INFO
        if(PaymemntUtils.validateCreditLimit(paymentInfo.getAccountNo(), savedPassengerInfo.getFare())){
            //SAVE PAYMENT INFO TO PAYMENT_INFO TABLE
            paymentInfo.setPassengerId(savedPassengerInfo.getpId());
            paymentInfo.setAmount(savedPassengerInfo.getFare());
            paymentInfoRepository.save(paymentInfo);
        }
        return new FlightBookingAcknowledgement("SUCCESS",
                savedPassengerInfo.getFare() + GST,
                UUID.randomUUID().toString().split("-")[0],
                savedPassengerInfo);
    }

    private void testExceptions() {
        //int i = 100/0;
        String s = null;
        s.getBytes();
    }

}
