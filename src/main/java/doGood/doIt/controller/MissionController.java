package doGood.doIt.controller;

import doGood.doIt.dto.AcceptMissionRequest;
import doGood.doIt.dto.MissionFriendAddRequest;
import doGood.doIt.dto.response.MissionDetailResponse;
import doGood.doIt.dto.response.MissionFriendListResponse;
import doGood.doIt.dto.response.MissionListResponse;
import doGood.doIt.service.MissionService;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/{memberId}")
    public ResponseEntity<MissionListResponse> listAll(@PathVariable Long memberId) {
        return ResponseEntity.ok()
                .body(missionService.listAll(memberId));
    }

    @GetMapping("/{missionId}/{memberId}")
    public ResponseEntity<MissionDetailResponse> listDetail(@PathVariable("missionId") Long missionId,
                                                            @PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok()
                .body(missionService.detailAll(memberId , missionId));
    }

    @GetMapping("/{missionId}/{memberId}/friends")
    public ResponseEntity<MissionFriendListResponse> missionFriendList(@PathVariable("missionId") Long missionId, @PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok()
                .body(missionService.myfriendListAll(missionId,memberId));
    }

    @PostMapping("/accept")
    public void acceptFriendMission(@RequestBody AcceptMissionRequest request) {
        missionService.acceptMissionFriend(request);
    }

    @PostMapping("/invite")
    public void inviteFriendMission(@RequestBody MissionFriendAddRequest request) {
        missionService.MissionInviteFriend(request);
    }

//    @PostMapping("/mission/room")
//    public ResponseEntity<> missionAddFriend(@RequestBody MissionFriendAddRequest request) {
//        return ResponseEntity.ok()
//                .body();
//    }



}
