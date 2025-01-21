package com.demo.tx.dto;

import com.demo.tx.entity.PassengerInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
public class FlightBookingAcknowledgement {
    private String status;
    private Double totalFare;
    private String pnrNo;
    private PassengerInfo passengerInfo;

    public FlightBookingAcknowledgement() {
    }

    public FlightBookingAcknowledgement(String status, Double totalFare, String pnrNo, PassengerInfo passengerInfo) {
        this.status = status;
        this.totalFare = totalFare;
        this.pnrNo = pnrNo;
        this.passengerInfo = passengerInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(Double totalFare) {
        this.totalFare = totalFare;
    }

    public String getPnrNo() {
        return pnrNo;
    }

    public void setPnrNo(String pnrNo) {
        this.pnrNo = pnrNo;
    }

    public PassengerInfo getPassengerInfo() {
        return passengerInfo;
    }

    public void setPassengerInfo(PassengerInfo passengerInfo) {
        this.passengerInfo = passengerInfo;
    }

    @Override
    public String toString() {
        return "FlightBookingAcknowledgement{" +
                "status='" + status + '\'' +
                ", totalFare=" + totalFare +
                ", pnrNo='" + pnrNo + '\'' +
                ", passengerInfo=" + passengerInfo +
                '}';
    }
}
