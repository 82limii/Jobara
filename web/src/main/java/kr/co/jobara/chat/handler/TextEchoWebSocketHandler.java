package kr.co.jobara.chat.handler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TextEchoWebSocketHandler extends TextWebSocketHandler {
	
	@Resource(name="wsSessionList")
	private List<WebSocketSession> wsSessionList;
	
	private void broadcasting(String message) throws IOException {
		for(WebSocketSession session : wsSessionList) {
			session.sendMessage(new TextMessage(message));
		}
	}

	@Override // @OnMessage 참고
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		broadcasting(message.getPayload());
	}

	@Override // @OnOpen 참고
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		InetSocketAddress clientAddr = session.getRemoteAddress();
		log.info("{} 과의 websocket 연결 성공", clientAddr);
		broadcasting(String.format("%s 님 입장!", clientAddr));
		wsSessionList.add(session);
	}

	@Override // @OnClose 참고
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		InetSocketAddress clientAddr = session.getRemoteAddress();
		int code = status.getCode();
		String reason = status.getReason();
		log.info("{} 과의 websocket 연결 종료, 종료코드 : {}, 종료사유 : {}", clientAddr, code, reason);
		wsSessionList.remove(session);
		broadcasting(String.format("%s 님 퇴장!", clientAddr));
	}
}