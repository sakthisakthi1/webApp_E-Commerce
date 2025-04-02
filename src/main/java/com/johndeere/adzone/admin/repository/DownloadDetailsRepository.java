package com.johndeere.adzone.admin.repository;

import java.math.BigInteger;

import com.johndeere.adzone.admin.entity.DownloadDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface DownloadDetailsRepository extends JpaRepository<DownloadDetails, BigInteger> {
	
	@Modifying
	@Query("update DownloadDetails d set d.isNotified = 1 where d.isNotified = 0")
	DownloadDetails updateDownloadDetails();
}
