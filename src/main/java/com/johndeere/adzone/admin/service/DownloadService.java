package com.johndeere.adzone.admin.service;

import com.johndeere.adzone.admin.entity.DownloadDetails;

import org.springframework.stereotype.Service;



@Service
public interface DownloadService {

    DownloadDetails updateCreatedByWithRacfId(DownloadDetails downloadDetails);
}