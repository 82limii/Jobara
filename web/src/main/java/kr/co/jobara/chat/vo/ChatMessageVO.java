package kr.co.jobara.chat.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageVO {

	private String chatRoomId;
    private String writer;
    private String message;
    private MessageType type;
}
