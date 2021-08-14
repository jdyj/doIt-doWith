package doGood.doIt.controller;

import doGood.doIt.dto.ChatMessageRequest;
import doGood.doIt.service.ChatMessageService;
import doGood.doIt.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.BinaryMessage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final ChatMessageService chatMessageService;
    private final ImageService imageService;
    private static final String FILE_UPLOAD_PATH = "C:\\Users\\user\\prac\\src\\main\\resources\\images\\";

    @MessageMapping("/chat/text/{roomId}")
    @SendTo("/topic/rooms/{roomId}")
    public String enter(ChatMessageRequest request, @DestinationVariable String roomId) {
        chatMessageService.save(request, roomId);
        log.info("웹소켓 통신");
        return request.getMessage();
    }

    @MessageMapping("/chat/image/{roomId}/{memberId}")
    @SendTo("/topic/rooms/{roomId}")
    public BinaryMessage enter(BinaryMessage message, @DestinationVariable String memberId, @DestinationVariable String roomId) {
        log.info("웹소켓 통신시작");
        ByteBuffer byteBuffer = message.getPayload();
        System.out.println(message.getPayload());
        System.out.println(byteBuffer);
        String fileName = "temp.jpg";
        File dir = new File(FILE_UPLOAD_PATH);
        if(!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(FILE_UPLOAD_PATH, fileName);
        FileOutputStream out = null;
        FileChannel outChannel = null;
        try {
            byteBuffer.flip(); //byteBuffer를 읽기 위해 세팅
            out = new FileOutputStream(file, true); //생성을 위해 OutputStream을 연다.
            outChannel = out.getChannel(); //채널을 열고
            byteBuffer.compact(); //파일을 복사한다.
            outChannel.write(byteBuffer); //파일을 쓴다.
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(out != null) {
                    out.close();
                }
                if(outChannel != null) {
                    outChannel.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        byteBuffer.position(0); //파일을 저장하면서 position값이 변경되었으므로 0으로 초기화한다.
        //파일쓰기가 끝나면 이미지를 발송한다.
        imageService.upload(memberId, roomId);
        log.info("웹소켓 통신완료");
        return new BinaryMessage(byteBuffer);
    }



}
