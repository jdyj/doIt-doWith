package doGood.doIt.controller;

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

    @GetMapping("/{memberId}")
    public ResponseEntity<Response> findAllChatRooms(@PathVariable Long memberId) {
        return ResponseEntity.ok()
                .body(chatRoomService.listAll(memberId));
    }

    @GetMapping("/status/{roomId}")
    public ResponseEntity<Response> listMemberStatus(@PathVariable Long roomId) {
        return ResponseEntity.ok().body(chatRoomService.listMemberStatus(roomId));
    }

}
