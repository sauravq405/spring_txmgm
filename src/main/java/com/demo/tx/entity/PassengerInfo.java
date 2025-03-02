package com.demo.tx.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table (name = "PASSENGER_INFO")
public class PassengerInfo {
    @Id
    @GeneratedValue
    private Long pId;
    private String name;
    private String email;
    private String source;
    private String destination;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date travelDate;
    private String pickupTime;
    private String arrivalTime;
    private Double fare;

    public PassengerInfo() {
    }

    public PassengerInfo(Long pId, String name, String email, String source, String destination, Date travelDate, String pickupTime, String arrivalTime, Double fare) {
        this.pId = pId;
        this.name = name;
        this.email = email;
        this.source = source;
        this.destination = destination;
        this.travelDate = travelDate;
        this.pickupTime = pickupTime;
        this.arrivalTime = arrivalTime;
        this.fare = fare;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }
    @Override
    public String toString() {
        return "PassengerInfo{" +
                "pId=" + pId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", travelDate=" + travelDate +
                ", pickupTime='" + pickupTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", fare=" + fare +
                '}';
    }
}
