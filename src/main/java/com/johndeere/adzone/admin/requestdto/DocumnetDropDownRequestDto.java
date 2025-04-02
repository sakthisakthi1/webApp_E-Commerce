package com.johndeere.adzone.admin.requestdto;

import java.math.BigInteger;

public class DocumnetDropDownRequestDto {
    
    private BigInteger docmentId ;

    public BigInteger getDocmentId() {
        return docmentId;
    }

    public void setDocmentId(BigInteger docmentId) {
        this.docmentId = docmentId;
    }

    public DocumnetDropDownRequestDto() {
    }
    
    public DocumnetDropDownRequestDto(BigInteger docmentId) {
        this.docmentId = docmentId;
    }
}
