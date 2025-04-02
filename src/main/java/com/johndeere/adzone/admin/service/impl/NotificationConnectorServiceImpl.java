package com.johndeere.adzone.admin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.johndeere.adzone.admin.requestdto.NotificationDto;
import com.johndeere.adzone.admin.requestdto.OrderDto;
import com.johndeere.adzone.admin.service.NotificationConnectorService;

@Service
public class NotificationConnectorServiceImpl implements NotificationConnectorService {

	@Autowired
	protected RestTemplate restTemplate;

	private static final Logger log = LoggerFactory.getLogger(NotificationConnectorServiceImpl.class);
	
	@Value("${notification.service.url}")
	private String notificationUrl;
	
    @Value("${notification.service.update.url}")
    private String updateNotifiactionUrl;

	
	public void saveNotification(NotificationDto dto) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(dto, headers);
			ResponseEntity<String> response = restTemplate.exchange(notificationUrl, HttpMethod.PUT, requestEntity,
					new ParameterizedTypeReference<String>() {
					});
			log.info("saveNotification --> response : "+response.getStatusCodeValue());
		} catch (Exception e) {
			e.printStackTrace();

		}
	}


	@Override
	public void updateNotification(NotificationDto dto) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(dto, headers);
			ResponseEntity<String> response = restTemplate.exchange(updateNotifiactionUrl, HttpMethod.POST, requestEntity,
					new ParameterizedTypeReference<String>() {
					});
			log.info("updateNotification --> response : "+response.getStatusCodeValue());
		} catch (Exception e) {
			log.error("Error in updateNotification --> "+e.getLocalizedMessage());
		}		
	}

}





