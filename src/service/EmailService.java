package service;

import annotations.Component;
import annotations.Qualifier;

@Component
@Qualifier("EmailService")
public class EmailService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Message to email: " +message );
    }
}
