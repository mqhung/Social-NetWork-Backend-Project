package com.project.socialnetwork.repository;

import com.project.socialnetwork.model.AppUser;
import com.project.socialnetwork.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IConversationRepository extends JpaRepository<Conversation,Long> {

    @Query( value = "select * from conversation where (first_user_id =?1 and second_user_id =?2" +
            " or first_user_id =?2 and second_user_id =?1) order by update_at", nativeQuery = true)
    Conversation getConversationBySenderIdAndReceiverId(Long senderId, Long receiverId);

    @Query(value = "select * from conversation where (first_user_id =?1 or second_user_id = ?1) order by update_at", nativeQuery = true)
    List<Conversation> getConversationByUserId(Long userId);

}
