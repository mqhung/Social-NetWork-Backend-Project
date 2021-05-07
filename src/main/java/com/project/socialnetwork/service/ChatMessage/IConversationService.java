package com.project.socialnetwork.service.ChatMessage;

import com.project.socialnetwork.model.Conversation;
import com.project.socialnetwork.service.IService;

import java.util.List;

public interface IConversationService extends IService<Conversation> {
    Conversation getConversationBySenderIdAndReceiverId(Long senderId, Long receiverId);
    List<Conversation> getConversationByUserId(Long userId);

}
