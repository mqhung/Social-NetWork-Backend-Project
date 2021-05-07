package com.project.socialnetwork.service.ChatMessage;

import com.project.socialnetwork.model.ChatMessage;
import com.project.socialnetwork.repository.IChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService implements IChatMessageService {

    @Autowired
    IChatMessageRepository chatMessageRepository;

    @Override
    public List<ChatMessage> findALl() {
        return null;
    }

    @Override
    public Page<ChatMessage> findALl(Pageable pageable) {
        return null;
    }

    @Override
    public ChatMessage findById(Long id) {
        return null;
    }

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        ChatMessage chatMessage1 = chatMessageRepository.save(chatMessage);
        return chatMessage1;
    }

    @Override
    public void deleteById(Long id) {
        chatMessageRepository.deleteById(id);
    }

    @Override
    public List<ChatMessage> getAllChatMessageByConversationId(Long conversationId) {
        return chatMessageRepository.getAllChatMessageByConversationId(conversationId);
    }
}
