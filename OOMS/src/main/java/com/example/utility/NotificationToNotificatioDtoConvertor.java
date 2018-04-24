package com.example.utility;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.example.bo.Notification;
import com.example.dto.NotificationDto;
@Component("notificationToNotificatioDtoConvertor")
public class NotificationToNotificatioDtoConvertor {
	
	public NotificationDto convert(Notification notification) {
		NotificationDto notificationDto = new NotificationDto();
		notificationDto.setCategory(notification.getCategory());
		notificationDto.setHeader(notification.getHeader());
		notificationDto.setRead(notification.getStatusRead());
		notificationDto.setMessage(notification.getMessage());
		notificationDto.setId(notification.getId());
		SimpleDateFormat df2 = new SimpleDateFormat("EEE, MMM d, ''yy");
        String dateText = df2.format(notification.getDate());
        notificationDto.setDate(dateText);
		return notificationDto;
	}

}
