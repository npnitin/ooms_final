package com.example.mailutil;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.example.utility.OOMSConstants;

import freemarker.template.Configuration;
 
@Service("mailService")
public class MailServiceImpl implements MailService {
 
    @Autowired
    JavaMailSender mailSender;
 
    @Autowired
    Configuration fmConfiguration;
 
    public void sendEmail(Mail mail,String mailAction) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
 
        try {
 
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(mail.getMailFrom());
            mimeMessageHelper.setTo(mail.getMailTo());
            if(mailAction.equalsIgnoreCase(OOMSConstants.SEND_ACC_ACTIVATION_STRING)){
            	 mail.setMailContent(geContentFromTemplate(mail.getModel()));
            }
            else if(mailAction.equalsIgnoreCase(OOMSConstants.SEND_ORDER_TO_CUSTOMER)){  	
            	mail.setMailContent(getContentFromTemplateForCreateOrder(mail.getModel()));
            }
            else if(mailAction.equalsIgnoreCase(OOMSConstants.SEND_PURCHASE_ORDER_TO_SUPPLIER)){
            	mail.setMailContent(getContentFromTemplateForCreatePurchaseOrder(mail.getModel()));
            }
            mimeMessageHelper.setText(mail.getMailContent(), true);
            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
 
    private String getContentFromTemplateForCreateOrder(Map<String, Object> model) {
    	StringBuffer content = new StringBuffer();
    	 
        try {
            content.append(FreeMarkerTemplateUtils
                .processTemplateIntoString(fmConfiguration.getTemplate("order.txt"), model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
	}
    private String getContentFromTemplateForCreatePurchaseOrder(Map<String, Object> model) {
    	StringBuffer content = new StringBuffer();
    	 
        try {
            content.append(FreeMarkerTemplateUtils
                .processTemplateIntoString(fmConfiguration.getTemplate("purchaseorder.txt"), model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
	}

	public String geContentFromTemplate(Map < String, Object > model) {
        StringBuffer content = new StringBuffer();
 
        try {
            content.append(FreeMarkerTemplateUtils
                .processTemplateIntoString(fmConfiguration.getTemplate("email-template.txt"), model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
 
}