package doGood.doIt.service;

import doGood.doIt.domain.ChatRoom;
import doGood.doIt.domain.Image;
import doGood.doIt.domain.MemberChatRoom;
import doGood.doIt.dto.response.ChatRoomListResponse;
import doGood.doIt.dto.ChatRoomMemberStatusDto;
import doGood.doIt.dto.response.Response;
import doGood.doIt.repository.ChatRoomRepository;
import doGood.doIt.repository.MemberChatRoomRepository;
import doGood.doIt.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;
    private final MemberChatRoomRepository memberChatRoomRepository;

    public ChatRoomListResponse listAll() {
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();

        ChatRoomListResponse response = new ChatRoomListResponse(chatRooms.size(), chatRooms);

        return response;
    }

    public Response listMemberStatus(Long roomId) {

        ChatRoom chatRoom = chatRoomRepository.findById(roomId).get();
        List<MemberChatRoom> memberChatRooms = memberChatRoomRepository.findMemberChatRoomsByChatRoom(chatRoom);
        List<ChatRoomMemberStatusDto> collect = memberChatRooms.stream()
                .map(memberChatRoom -> new ChatRoomMemberStatusDto(memberChatRoom.getMember().getName(),
                        memberChatRoom.getMember().getProfileUrl(),
                        memberChatRoom.getImages().stream().map(Image::getUrl).collect(Collectors.toList())))
                .collect(Collectors.toList());

        return new Response(collect.size(), collect);

    }

//    public ChatRoomCreateResponse createRoom(ChatRoomCreateRequest request) {
//        Optional<Member> memberOptional = memberRepository.findById(request.getMemberId());
//
//        if(memberOptional.isEmpty()) {
//            throw new IllegalStateException("존재하지 않는 회원입니다.");
//        }
//
//        ChatRoom chatRoom = ChatRoom.builder()
//                .name(request.getName())
//                .creator(memberOptional.get())
//                .roomStatus(RoomStatus.WILLDO)
//                .startDate(request.getStartDate())
//                .endDate(request.getEndDate())
//                .build();
//
//        ChatRoom savedRoom = chatRoomRepository.save(chatRoom);
//
//        ChatRoomCreateResponse response = new ChatRoomCreateResponse(savedRoom.getId(), savedRoom.getName());
//
//        return response;
//    }

}
