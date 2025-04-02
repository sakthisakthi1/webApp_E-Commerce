package com.johndeere.adzone.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johndeere.adzone.admin.entity.DownloadDetails;
import com.johndeere.adzone.admin.repository.DownloadDetailsRepository;
import com.johndeere.adzone.admin.service.DownloadService;



@Service
public class DownloadServiceImpl implements DownloadService {

	@Autowired
	private DownloadDetailsRepository detailsRepository;
	
	@Override
	public DownloadDetails updateCreatedByWithRacfId(DownloadDetails downloadDetails) {
		downloadDetails = detailsRepository.save(downloadDetails);
		return downloadDetails;
	}

}
