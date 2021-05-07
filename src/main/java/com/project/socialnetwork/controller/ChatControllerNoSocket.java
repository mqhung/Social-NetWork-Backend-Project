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
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/message")
public class ChatControllerNoSocket {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private IConversationService conversationService;

    @Autowired
    private IChatMessageService chatMessageService;


    @GetMapping("/getAll")
    public List<Conversation> getAllConversation() {
        AppUser currentUser = userService.getCurrentUser();
        List<Conversation> conversationList = conversationService.getConversationByUserId(currentUser.getId());

        return conversationList;
    }

    @GetMapping("/get-message-by-conversation-id/{conversationId}")
    public List<ChatMessage> getAllMessageByConversationId(@PathVariable Long conversationId) {
        return chatMessageService.getAllChatMessageByConversationId(conversationId);
    }

    @GetMapping("/{receiverId}")
    public Conversation getConversation(@PathVariable Long receiverId) {
        AppUser currentUser = userService.getCurrentUser();
        AppUser receiver = userService.findById(receiverId);
        Conversation conversation;
        conversation = conversationService.getConversationBySenderIdAndReceiverId(currentUser.getId(), receiverId);
        if (conversation == null) {
            conversation  = new Conversation();
            conversation.setFirstUser(currentUser);
            conversation.setSecondUser(receiver);
            conversation.setName(currentUser.getFirstName() + "," + receiver.getFirstName());
            conversation.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            conversation.setUpdateAt(Timestamp.valueOf(LocalDateTime.now()));
            conversationService.save(conversation);
        } else {
            conversation.setUpdateAt(Timestamp.valueOf(LocalDateTime.now()));
        }

        return conversation;
    }


//    @PostMapping("/create/{conversationId}")
//    public ChatMessage createNewChatMessage(@RequestBody ChatMessage message, @PathVariable Long conversationId){
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
//        return message;
//    }

}
