package smsApp;

import httpRequests.RegisterRequest;
import model.Users;
import mappers.UsersMapper;

import java.sql.SQLException;

import hms.kite.samples.api.sms.messages.MoSmsReq;

public class Operations {
	
	public void messageCheck(MoSmsReq smsReq) {
		
		String smsMessage = smsReq.getMessage();
		String [] messageParts = smsMessage.split(" ");
		
		SendMessage send = new SendMessage();
		
		if (messageParts[0].equalsIgnoreCase("REG") && messageParts[1].equalsIgnoreCase("CRICKET")) {
			
			String [] msisdnParts = smsReq.getSourceAddress().split(":");
			String msisdn = msisdnParts[1];
			
			Users user = new Users();
			user.setMsisdn(msisdn);
			user.setType("Register");
			
			RegisterRequest registerRequest = new RegisterRequest();
			registerRequest.registerUser(user);
			
		}
		else if(messageParts[0].equalsIgnoreCase("CRIC") && messageParts[1].equalsIgnoreCase("INFO")) {
			
			String [] msisdnParts = smsReq.getSourceAddress().split(":");
			String msisdn = msisdnParts[1];
			
			Users user = new Users();
			user.setMsisdn(msisdn);
			user.setType("Register");
			
			UsersMapper usersMapper = new UsersMapper();
			
			try {
				if(usersMapper.findUser(user) && usersMapper.findRegister(user)) {					
					send.sendMessage("SL vs IND 273/7", "APP_000001", smsReq.getSourceAddress(), "password", "http://127.0.0.1:7000/sms/send");
					
				} 
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			System.out.println(smsMessage);
			send.sendMessage("Your Message Format is wrong", "APP_000001", smsReq.getSourceAddress(), "password", "http://127.0.0.1:7000/sms/send");
		}
	}
}
