

package com.project.socialnetwork.controller;


import com.project.socialnetwork.model.AppUser;
import com.project.socialnetwork.model.ChatMessage;
import com.project.socialnetwork.model.Conversation;
import com.project.socialnetwork.model.MessageType;
import com.project.socialnetwork.service.ChatMessage.IChatMessageService;
import com.project.socialnetwork.service.ChatMessage.IConversationService;
import com.project.socialnetwork.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@RestController
@CrossOrigin("*")
public class ChatMessageControllerSocket {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserService userService;
    @Autowired
    private IConversationService conversationService;

    @Autowired
    private IChatMessageService chatMessageService;

    @MessageMapping("/messenger/create/{conversationId}/{senderId}")
    public ChatMessage createNewChatMessage(@Payload ChatMessage message, @DestinationVariable Long conversationId, @DestinationVariable Long senderId) {
        AppUser currentUser = userService.findById(senderId);
        Conversation conversation = conversationService.findById(conversationId);

        message.setConversation(conversation);
        message.setSender(currentUser);
        if (currentUser.getId().equals(conversation.getFirstUser().getId())) {
            message.setReceiver(conversation.getSecondUser());
        } else {
            message.setReceiver(conversation.getFirstUser());
        }

        message.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        message.setType(MessageType.CHAT);

        chatMessageService.save(message);

        simpMessagingTemplate.convertAndSend("/message/getMessage/" + message.getReceiver().getId(), message);
        simpMessagingTemplate.convertAndSend("/message/getMessage/" + message.getSender().getId(), message);

        return message;
    }
}
