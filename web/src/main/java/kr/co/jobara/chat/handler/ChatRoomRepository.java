package kr.co.jobara.chat.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import kr.co.jobara.chat.vo.ChatRoomVO;

@Repository
public class ChatRoomRepository {
//	private Map<String, ChatRoomVO> chatRoomMap;
//
//    @PostConstruct
//    private void init(){
//        chatRoomMap = new LinkedHashMap<>();
//    }
//
//    public List<ChatRoomVO> findAllRoom(){
//        List chatRooms = new ArrayList<>(chatRoomMap.values());
//        Collections.reverse(chatRooms);
//        return chatRooms;
//    }
//
//    public ChatRoomVO findRoomById(String id){
//        return chatRoomMap.get(id);
//    }
//
//    public ChatRoomVO createChatRoom(String name){
//    	ChatRoomVO chatRoom = ChatRoomVO.create(name);
//        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
//        return chatRoom;
//    }
}
