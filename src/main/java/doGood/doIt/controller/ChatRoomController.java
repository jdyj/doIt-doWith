package doGood.doIt.controller;

import doGood.doIt.dto.response.ChatRoomListResponse;
import doGood.doIt.dto.ChatRoomMemberStatusDto;
import doGood.doIt.dto.response.Response;
import doGood.doIt.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat/rooms")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping
    public ResponseEntity<ChatRoomListResponse> findAllChatRooms() {
        return ResponseEntity.ok()
                .body(chatRoomService.listAll());
    }

    @GetMapping("/status/{roomId}")
    public ResponseEntity<Response> listMemberStatus(@PathVariable Long roomId) {
        return ResponseEntity.ok().body(chatRoomService.listMemberStatus(roomId));
    }

}
