package com.johndeere.adzone.admin.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "download_details",schema = "\"order\"")
public class DownloadDetails{
	
    @Id
    @SequenceGenerator(name = "download_dtls", sequenceName = "download_dtls_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "download_dtls" )
    @Column(name = "download_detail_id")
    private BigInteger downloadDetailId;

    @Column(name = "module_name")
    private String moduleName;

    @Column(name = "s3_file_name")
    private String s3FileName;

    @Column(name = "status_fl")
    private Integer statusFlag;

    @Column(name = "is_active")
    private Integer isActive = 1;

    @Column(name = "delete_flag")
    private Integer deleteFlag = 0;

    @Column(name = "upload_master_id")
    private BigInteger uploadMasterId;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "downloaded_by")
	private String downloadedBy;
    
    @Column(name = "is_notified")
    private Integer isNotified = 0;

    // @ManyToOne
    // @JoinColumn(name = "upload_master_id", referencedColumnName = "upload_master_id", insertable = false, updatable = false)
    // private UploadMaster uploadMaster;

    // @ManyToOne
    // @JoinColumn(name = "status_fl", referencedColumnName = "status_id", insertable = false, updatable = false)
    // private Status status;

	public DownloadDetails() {
	}

    public DownloadDetails(BigInteger downloadDetailId, String moduleName, String s3FileName, Integer statusFlag,
            Integer isActive, Integer deleteFlag, BigInteger uploadMasterId, String createdBy, String downloadedBy,
            Integer isNotified) {
        this.downloadDetailId = downloadDetailId;
        this.moduleName = moduleName;
        this.s3FileName = s3FileName;
        this.statusFlag = statusFlag;
        this.isActive = isActive;
        this.deleteFlag = deleteFlag;
        this.uploadMasterId = uploadMasterId;
        this.createdBy = createdBy;
        this.downloadedBy = downloadedBy;
        this.isNotified = isNotified;
    }

    public BigInteger getDownloadDetailId() {
        return downloadDetailId;
    }

    public void setDownloadDetailId(BigInteger downloadDetailId) {
        this.downloadDetailId = downloadDetailId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getS3FileName() {
        return s3FileName;
    }

    public void setS3FileName(String s3FileName) {
        this.s3FileName = s3FileName;
    }

    public Integer getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(Integer statusFlag) {
        this.statusFlag = statusFlag;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public BigInteger getUploadMasterId() {
        return uploadMasterId;
    }

    public void setUploadMasterId(BigInteger uploadMasterId) {
        this.uploadMasterId = uploadMasterId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDownloadedBy() {
        return downloadedBy;
    }

    public void setDownloadedBy(String downloadedBy) {
        this.downloadedBy = downloadedBy;
    }

    public Integer getIsNotified() {
        return isNotified;
    }

    public void setIsNotified(Integer isNotified) {
        this.isNotified = isNotified;
    }


}
