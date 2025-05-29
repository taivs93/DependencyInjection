    package service;

    import annotations.Autowired;
    import annotations.Component;
    import annotations.Qualifier;

    @Component
    public class NotificationService {

        private MessageService messageService;

        @Autowired
        @Qualifier("EmailService")
        public void setMessageService(MessageService messageService) {
            this.messageService = messageService;
        }

        public void send(String message) {
            messageService.sendMessage(message);
        }
    }
