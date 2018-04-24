package com.example.utility;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.example.bo.User;
import com.example.dto.UserDto;

@Component("userToUserDtoConvertor")
public class UserToUserDtoConvertor {
	
	public UserDto convert(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setAddress(user.getAddress());
		userDto.setPhone(user.getPhone());
		userDto.setEmail(user.getEmail());
		
		if(user.getStatus() == 1) {
			userDto.setStatus("ACTIVE");
		}
		else {
			userDto.setStatus("INACTIVE");
		}
		
		SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
        String dateText = df2.format(user.getDateOfRegistration());
        userDto.setDateOfRegistration(dateText);
        
        
		return userDto;
	}

}
