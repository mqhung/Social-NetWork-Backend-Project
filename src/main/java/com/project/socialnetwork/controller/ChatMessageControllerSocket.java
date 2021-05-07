//package com.project.socialnetwork.controller;
//
//
//import com.project.socialnetwork.model.AppUser;
//import com.project.socialnetwork.model.ChatMessage;
//import com.project.socialnetwork.model.Conversation;
//import com.project.socialnetwork.model.MessageType;
//import com.project.socialnetwork.service.ChatMessage.ConversationService;
//import com.project.socialnetwork.service.ChatMessage.IChatMessageService;
//import com.project.socialnetwork.service.ChatMessage.IConversationService;
//import com.project.socialnetwork.service.user.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.DestinationVariable;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.sql.Date;
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@CrossOrigin("*")
//@RestController
//public class ChatMessageController {
//
//    @Autowired
//    private SimpMessagingTemplate simpMessagingTemplate;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private IConversationService conversationService;
//
//    @Autowired
//    private IChatMessageService chatMessageService;
//
//
//    @MessageMapping("/message")
//    public List<Conversation> getAllConversation() {
//        AppUser currentUser = userService.getCurrentUser();
//        List<Conversation> conversationList = conversationService.getConversationByUserId(currentUser.getId());
//
//        this.simpMessagingTemplate.convertAndSend("/message", conversationList);
//        return conversationList;
//    }
//
//    @MessageMapping("/message/{receiverId}")
//    public Conversation getConversation(@DestinationVariable Long receiverId) {
//        AppUser currentUser = userService.getCurrentUser();
//        AppUser receiver = userService.findById(receiverId);
//        Conversation conversation = new Conversation();
//        conversation = conversationService.getConversationBySenderIdAndReceiverId(currentUser.getId(), receiverId);
//        if (conversation == null) {
//            conversation.setFirstUser(currentUser);
//            conversation.setSecondUser(receiver);
//            conversation.setName(currentUser.getFirstName() + "," + receiver.getFirstName());
//            conversation.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
//            conversation.setUpdateAt(Timestamp.valueOf(LocalDateTime.now()));
//            conversationService.save(conversation);
//        } else {
//            conversation.setUpdateAt(Timestamp.valueOf(LocalDateTime.now()));
//        }
//        simpMessagingTemplate.convertAndSend("/message", conversation);
//        return conversation;
//    }
//
//    @MessageMapping("/message/{conversationId}")
//    public List<ChatMessage> getMessage(@DestinationVariable Long conversationId) {
//        List<ChatMessage> chatMessageList = chatMessageService.getAllChatMessageByConversationId(conversationId);
//        simpMessagingTemplate.convertAndSend("/message", chatMessageList);
//        return chatMessageList;
//    }
//
//    @MessageMapping("/message/send/{conversationId}")
//    public ChatMessage sendMessage(@Payload ChatMessage message, @DestinationVariable Long conversationId){
//
//        AppUser currentUser = userService.getCurrentUser();
//        Conversation conversation = conversationService.findById(conversationId);
//
//        message.setConversation(conversation);
//        message.setSender(currentUser);
//        message.setReceiver(conversation.getSecondUser());
//        message.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
//        message.setType(MessageType.CHAT);
//
//        chatMessageService.save(message);
//
//        simpMessagingTemplate.convertAndSend("/message",message);
//
//        return message;
//    }
//
//
//}

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
import java.util.List;

@RestController
@CrossOrigin("*")
//@MessageMapping("/messenger/")
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
