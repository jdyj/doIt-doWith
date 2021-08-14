package doGood.doIt.controller;

import doGood.doIt.dto.FriendAcceptRequest;
import doGood.doIt.dto.FriendAddRequest;
import doGood.doIt.dto.response.FriendListResponse;
import doGood.doIt.dto.response.FriendResponse;
import doGood.doIt.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @PostMapping("/add")
    public ResponseEntity<FriendResponse> addFriend(@RequestBody FriendAddRequest request) {
        return ResponseEntity.ok()
                .body(new FriendResponse(friendService.addFriend(request)));
    }

    @PostMapping("/accept")
    public ResponseEntity<FriendResponse> acceptFriend(@RequestBody FriendAcceptRequest request) {
        return ResponseEntity.ok()
                .body(new FriendResponse(friendService.acceptFriend(request)));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<FriendListResponse> friendList(@PathVariable Long memberId) {
        return ResponseEntity.ok()
                .body(friendService.friendList(memberId));
    }

}
