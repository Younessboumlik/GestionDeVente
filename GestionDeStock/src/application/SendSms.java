package application;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class SendSms {
  // Find your Account Sid and Token at twilio.com/console

//570f47e2ec833f38a8f1849eed3c48cb
//AC6ab3b891fac19c6aca18fb8624fa4550 sid


//
// sid
  public static final String ACCOUNT_SID = "AC6ab3b891fac19c6aca18fb8624fa4550";
  public static final String AUTH_TOKEN = "57d58f4c79b91fe7be0a9ceed1b5e109";


  public static void smssend(String smsmessage) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message message = Message.creator(
      new com.twilio.type.PhoneNumber("+212651484070"),
      new com.twilio.type.PhoneNumber("+18283445137"),
      smsmessage).create();

    System.out.println(message.getSid());
	  		
		
  }
}