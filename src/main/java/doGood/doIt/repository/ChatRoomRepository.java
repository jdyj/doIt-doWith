package doGood.doIt.repository;

import doGood.doIt.domain.ChatRoom;
import doGood.doIt.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {

    ChatRoom findChatRoomById (Long id);

    List<ChatRoom> findChatRoomsByMission (Mission mission);

}
