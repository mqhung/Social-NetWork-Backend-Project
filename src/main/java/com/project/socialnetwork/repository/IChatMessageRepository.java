package com.project.socialnetwork.repository;

import com.project.socialnetwork.model.ChatMessage;
import com.project.socialnetwork.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IChatMessageRepository extends JpaRepository<ChatMessage,Long> {

    @Query (value = "select * from chat_message where conversation_id =?1 order by created_at  ASC ",nativeQuery = true)
    List<ChatMessage> getAllChatMessageByConversationId(Long conversationId);
}
