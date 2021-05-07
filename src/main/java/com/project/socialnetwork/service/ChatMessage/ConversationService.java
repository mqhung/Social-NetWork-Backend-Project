package com.project.socialnetwork.service.ChatMessage;

import com.project.socialnetwork.model.Conversation;
import com.project.socialnetwork.repository.IConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationService implements IConversationService {

    @Autowired
    IConversationRepository conversationRepository;

    @Override
    public List<Conversation> findALl() {
        return null;
    }

    @Override
    public Page<Conversation> findALl(Pageable pageable) {
        return null;
    }

    @Override
    public Conversation findById(Long id) {
        return conversationRepository.findById(id).get();
    }

    @Override
    public Conversation save(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    @Override
    public void deleteById(Long id) {
        conversationRepository.deleteById(id);
    }

    @Override
    public Conversation getConversationBySenderIdAndReceiverId(Long senderId, Long receiverId) {
        return conversationRepository.getConversationBySenderIdAndReceiverId(senderId, receiverId);
    }

    @Override
    public List<Conversation> getConversationByUserId(Long userId) {
        return conversationRepository.getConversationByUserId(userId);
    }
}
