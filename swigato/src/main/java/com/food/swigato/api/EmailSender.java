package com.food.swigato.api;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;

public  class EmailSender {
	public static void sendEmail(String emailId, String message, String name) throws MailjetException, MailjetSocketTimeoutException {
		MailjetClient client;
		MailjetRequest request;
		MailjetResponse response;
		client = new MailjetClient("3e292e1e3e850abe850793dbb22554b9",
				"2fa15000afb8c7ad2cd676c9828bcd5e", new ClientOptions("v3.1"));
		request = new MailjetRequest(Emailv31.resource).property(Emailv31.MESSAGES,
				new JSONArray().put(new JSONObject()
						.put(Emailv31.Message.FROM, new JSONObject().put("Email", "tahanasim3001@gmail.com")
								.put("Name", "Swigato"))
						.put(Emailv31.Message.TO,
								new JSONArray().put(
										new JSONObject().put("Email", emailId).put(name, "Hello")))
						.put(Emailv31.Message.SUBJECT, "Order confirmation from Swigato.")
						.put(Emailv31.Message.TEXTPART, "My first Mailjet email")
						.put(Emailv31.Message.HTMLPART,
								"<h3>"+message)
						.put(Emailv31.Message.CUSTOMID, "AppGettingStartedTest")));
		response = client.post(request);
		System.out.println(response.getStatus());
		System.out.println(response.getData());
	}
	
	public static void sendEmailForOrderConfirmations(String emailId, String name, Double totalAmount) {
		
		String message= "Hi! Mr. "+name+". Your order worth of "+String.valueOf(totalAmount)+ " is now confirmed. We are glad to serve you."; 
		try {
			sendEmail(emailId, message, name);
		} catch (MailjetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MailjetSocketTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}