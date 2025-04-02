package com.johndeere.adzone.admin.responsedto;

import java.math.BigInteger;

public class DownloadFileDetailResponseDto {

   

    private String documentFileName;

    private BigInteger documentFileId;
    
    public String getDocumentFileName() {
        return documentFileName;
    }

    public void setDocumentFileName(String documentFileName) {
        this.documentFileName = documentFileName;
    }

    public BigInteger getDocumentFileId() {
        return documentFileId;
    }

    public void setDocumentFileId(BigInteger documentFileId) {
        this.documentFileId = documentFileId;
    }
 
    public DownloadFileDetailResponseDto(String documentFileName, BigInteger documentFileId) {
        this.documentFileName = documentFileName;
        this.documentFileId = documentFileId;
    }
}
