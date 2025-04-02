package com.johndeere.adzone.admin.service;

import org.springframework.stereotype.Service;

import com.johndeere.adzone.admin.requestdto.NotificationDto;

@Service
public interface NotificationConnectorService {
	
	public void saveNotification(NotificationDto dto);

	public void updateNotification(NotificationDto dto);
	
}