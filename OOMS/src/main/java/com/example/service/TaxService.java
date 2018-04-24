package com.example.service;

import java.util.List;

import com.example.bo.ResponseBo;
import com.example.bo.Tax;
import com.example.dto.TaxDto;

public interface TaxService {
	
	ResponseBo addTax(String userId,Tax tax);
	List<TaxDto> getAllTaxesByUserId(String userId);
	ResponseBo deleteTaxById(String userId,String taxId);
	ResponseBo updateTax(String userId,String taxId,Tax tax);

}
