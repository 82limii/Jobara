package kr.co.jobara.chat.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import lombok.RequiredArgsConstructor;

//@Configuration
//@EnableWebSocket
//@RequiredArgsConstructor
public class StompWebSocketConfig implements WebSocketConfigurer {
	
//	private final TextEchoWebSocketHandler webSocketHandler;
//	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//		registry.addHandler(webSocketHandler,"/chat");
		
	}

//    //endpoint를 /stomp로 하고, allowedOrigins를 "*"로 하면 페이지에서
//    //Get /info 404 Error가 발생한다. 그래서 아래와 같이 2개의 계층으로 분리하고
//    //origins를 개발 도메인으로 변경하니 잘 동작하였다.
//    //이유는 왜 그런지 아직 찾지 못함
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/stomp/chat")
//                .setAllowedOrigins("${cPath }")
//                .withSockJS();
//    }
//
//    /*어플리케이션 내부에서 사용할 path를 지정할 수 있음*/
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        registry.setApplicationDestinationPrefixes("/pub");
//        registry.enableSimpleBroker("/sub");
//    }
//
//	@Override
//	public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void configureClientInboundChannel(ChannelRegistration registration) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void configureClientOutboundChannel(ChannelRegistration registration) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
