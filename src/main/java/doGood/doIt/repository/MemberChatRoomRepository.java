package doGood.doIt.repository;

import doGood.doIt.domain.ChatRoom;
import doGood.doIt.domain.Member;
import doGood.doIt.domain.MemberChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberChatRoomRepository extends JpaRepository<MemberChatRoom, Long> {

    List<MemberChatRoom> findMemberChatRoomsByChatRoom(ChatRoom chatRoom);

    List<MemberChatRoom> findMemberChatRoomsByMember(Member member);
}
