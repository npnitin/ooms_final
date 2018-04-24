package com.example.utility;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.example.bo.Tax;
import com.example.dto.TaxDto;

@Component("taxToTaxDtoConvertor")
public class TaxToTaxDtoConvertor {
	
	public TaxDto convert(Tax tax) {
		TaxDto taxDto = new TaxDto();
		taxDto.setId(tax.getId());
		taxDto.setName(tax.getName());
		taxDto.setDescription(tax.getDescription());
		taxDto.setPercentage(tax.getPercentage());
		
		SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
        String dateText = df2.format(tax.getCreationDate());
        taxDto.setCreationDate(dateText);
        
        dateText = df2.format(tax.getUpdatedDate());
        taxDto.setUpdatedDate(dateText);
        return taxDto;
	}

}
