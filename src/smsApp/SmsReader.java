package smsApp;

import hms.kite.samples.api.sms.MoSmsListener;
import hms.kite.samples.api.sms.messages.MoSmsReq;

public class SmsReader implements MoSmsListener {

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceivedSms(MoSmsReq message) {
		// TODO Auto-generated method stub
		System.out.println("this is receiver");
		
		Operations operation = new Operations();
		operation.messageCheck(message);
	}

}
