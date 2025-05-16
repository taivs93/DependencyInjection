    package service;

    import annotations.Autowired;
    import annotations.Component;
    import annotations.Qualifier;

    @Component
    public class NotificationService {
        @Autowired
        @Qualifier("EmailService")
        private MessageService messageService;

        public void send(String message) {
            messageService.sendMessage(message);
        }
    }
