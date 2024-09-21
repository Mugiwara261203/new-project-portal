package com.teamcoffee.appdc.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
@Slf4j
public class TwilioService {

    @Value("${twilio_account_sid}")
    private String accountSid;

    @Value("${twilio_auth_token}")
    private String authToken;

    @Value("${twilio_phone_number}")
    private String fromPhoneNumber;

    @PostConstruct
    public void initService(){
        log.info("Account SID: {}", accountSid);
        log.info("Auth Token: {}", authToken);
        log.info("From phone: {}", fromPhoneNumber);
        Twilio.init(accountSid, authToken);
    }

    public void sendSms(String toPhoneNumber, String message){
        try {
            String formattedPhoneNumber = formatPhoneNumber(toPhoneNumber);
            Message.creator(
                    new PhoneNumber(formattedPhoneNumber),
                    new PhoneNumber(fromPhoneNumber),
                    message
            ).create();
            System.out.println("SMS sent successfully");
        } catch (Exception e){
            System.err.println("Error sending SMS: " + e.getMessage());
        }
    }

    public String formatPhoneNumber(String phoneNumber){
        if (!phoneNumber.startsWith("+505")){
            return "+505" + phoneNumber;
        }
        return phoneNumber;
    }

}
