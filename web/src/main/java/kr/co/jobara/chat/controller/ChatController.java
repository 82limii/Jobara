package kr.co.jobara.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatController {

	@RequestMapping("rooms.do")
	public String rooms() {	
		return "project/rooms";
	}
	@RequestMapping("room.do")
	public String room() {	
		return "project/room";
	}
	@RequestMapping("chat.do")
	public String chat() {	
		return "project/stompChat";
	}
	
//	private final ChatRoomRepository chatRoomRepository;
//
//    @GetMapping("/rooms")
//    public String rooms(Model model){
//        model.addAttribute("rooms",chatRoomRepository.findAllRoom());
//        return "chat/rooms";
//    }
//
//    @GetMapping("/rooms/{id}")
//    public String room(@PathVariable String id, Model model){
//        ChatRoomVO room = chatRoomRepository.findRoomById(id);
//        model.addAttribute("room",room);
//        return "chat/room";
//    }
//
//    @GetMapping("/new")
//    public String make(Model model){
//        ChatRoomFormVO form = new ChatRoomFormVO();
//        model.addAttribute("form",form);
//        return "chat/newRoom";
//    }
//
//    @PostMapping("/room/new")
//    public String makeRoom(ChatRoomFormVO form){
//        chatRoomRepository.createChatRoom(form.getName());
//
//        return "redirect:/rooms";
//    }
}
