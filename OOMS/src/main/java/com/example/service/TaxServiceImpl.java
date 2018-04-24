package com.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bo.ResponseBo;
import com.example.bo.Tax;
import com.example.bo.User;
import com.example.dao.TaxRepository;
import com.example.dao.UserRepository;
import com.example.dto.TaxDto;
import com.example.utility.TaxToTaxDtoConvertor;

@Service("taxServiceImpl")
public class TaxServiceImpl implements TaxService {

	@Autowired
	TaxRepository taxRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TaxToTaxDtoConvertor taxToTaxDtoConvertor;
	
	@Override
	public ResponseBo addTax(String userId, Tax tax) {
		int uid = Integer.parseInt(userId);
		User user = userRepository.findOne(uid);
		tax.setCreationDate(new Date());
		tax.setUpdatedDate(new Date());
		if(null != user.getTaxes()) {
			user.getTaxes().add(tax);
		}
		else {
			List<Tax> taxes = new ArrayList<>(1);
			taxes.add(tax);
			user.setTaxes(taxes);
		}
		userRepository.save(user);
		ResponseBo response = new ResponseBo();
		response.setErrorCode("200");
		response.setMessage("Tax added sucesfully");
		return response;
	}

	@Override
	public List<TaxDto> getAllTaxesByUserId(String userId) {
		int uid = Integer.parseInt(userId);
		User user = userRepository.findOne(uid);
		List<Tax> taxes = user.getTaxes();
		List<TaxDto> taxDtos = new ArrayList<>(taxes.size());
		for(Tax tax: taxes) {
			taxDtos.add(taxToTaxDtoConvertor.convert(tax));
		}
		return taxDtos;
	}

	@Override
	public ResponseBo deleteTaxById(String userId, String taxId) {
		int uid = Integer.parseInt(userId);
		int tid = Integer.parseInt(taxId);
		ResponseBo response = new ResponseBo();
		User user = userRepository.findOne(uid);
		for(Tax tax : user.getTaxes()) {
			if(tax.getId() == tid) {
				user.getTaxes().remove(tax);
				userRepository.save(user);
				response.setErrorCode("200");
				response.setMessage("tax deleted succesfully.");
				return response;
			}
		}
		response.setErrorCode("400");
		response.setMessage("tax not found");
		return response;
	}

	@Override
	public ResponseBo updateTax(String userId, String taxId,Tax newTax) {
		int uid = Integer.parseInt(userId);
		int tid = Integer.parseInt(taxId);
		ResponseBo response = new ResponseBo();
		User user = userRepository.findOne(uid);
		for(Tax tax : user.getTaxes()) {
			if(tax.getId() == tid) {
				user.getTaxes().remove(tax);
				newTax.setId(tid);
				newTax.setCreationDate(tax.getCreationDate());
				newTax.setUpdatedDate(new Date());
				newTax.setStatus(tax.getStatus());
				user.getTaxes().add(newTax);
				userRepository.save(user);
				response.setErrorCode("200");
				response.setMessage("tax updated succesfully");
				return response;
			}
		}
		response.setErrorCode("400");
		response.setMessage("tax not found");
		return response;
	}

	

}
