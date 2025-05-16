package service;

import annotations.Component;

@Component
public interface MessageService {
    void sendMessage(String message);
}
