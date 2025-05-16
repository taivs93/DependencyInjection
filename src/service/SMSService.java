package service;

import annotations.Component;
import annotations.Qualifier;

@Component
@Qualifier("SMSService")
public class SMSService implements MessageService{
    @Override
    public void sendMessage(String message) {
        System.out.println("Message to SMS: " + message);
    }
}
