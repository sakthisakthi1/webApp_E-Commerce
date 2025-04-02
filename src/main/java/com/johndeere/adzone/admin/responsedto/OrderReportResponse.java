package com.johndeere.adzone.admin.responsedto;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderReportResponse {

    private BigInteger documentId;
    private BigInteger documentFileId;
    private String racfId;
    private Timestamp createdAt;

    public BigInteger getDocumentId() {
        return documentId;
    }

    public void setDocumentId(BigInteger documentId) {
        this.documentId = documentId;
    }

    public BigInteger getDocumentFileId() {
        return documentFileId;
    }

    public void setDocumentFileId(BigInteger documentFileId) {
        this.documentFileId = documentFileId;
    }

    public String getRacfId() {
        return racfId;
    }

    public void setRacfId(String racfId) {
        this.racfId = racfId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
