package com.project.socialnetwork.service.ChatMessage;

import com.project.socialnetwork.model.ChatMessage;
import com.project.socialnetwork.service.IService;

import java.sql.Timestamp;
import java.util.List;

public interface IChatMessageService extends IService<ChatMessage> {
    List<ChatMessage> getAllChatMessageByConversationId(Long conversationId);
}
