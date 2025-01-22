package com.demo.tx.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "AUDIT_LOGS")
public class AuditLogs {
    @Id
    private String rc;
    private String api;
    private int httpStatusCode;
    private String status;
    private String creationTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTimeStamp;

    public AuditLogs() {
    }

    public AuditLogs(String rc, String api, int httpStatusCode, String status, String creationTime, Date creationTimeStamp) {
        this.rc = rc;
        this.api = api;
        this.httpStatusCode = httpStatusCode;
        this.status = status;
        this.creationTime = creationTime;
        this.creationTimeStamp = creationTimeStamp;
    }

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public Date getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public void setCreationTimeStamp(Date creationTimeStamp) {
        this.creationTimeStamp = creationTimeStamp;
    }

    @Override
    public String toString() {
        return "AuditLogs{" +
                "rc='" + rc + '\'' +
                ", api='" + api + '\'' +
                ", httpStatusCode=" + httpStatusCode +
                ", status='" + status + '\'' +
                ", creationTime='" + creationTime + '\'' +
                ", creationTimeStamp=" + creationTimeStamp +
                '}';
    }
}
