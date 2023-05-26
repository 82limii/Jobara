<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script type="text/javascript" src="${cPath }/resources/js/sockjs.min.js"></script>
<script type="text/javascript" src="${cPath }/resources/js/stomp.min.js"></script>	
	
	
<style type="text/css">
.my {
	background-color: yellow;
}
</style>

<!-- <div class="container"> -->
<!-- 	<div class="row"> -->
		<div id="messagesArea"></div>
		<input type="text" id="message" onchange="messageSend(event);" />
		<input type="button" value="종료" onclick="disconnect(event);">
<!-- 	</div> -->
<!-- </div> -->
<script type="text/javascript">
	let client = null;
	let headers = {}
	let messageArea = document.querySelector("#messagesArea");
	(function () {
		var sockJS = new SockJS("${cPath}/stomp/echo");
		client = Stomp.over(sockJS);
		client.connect(headers, function(connectFrame) {
			client.subscribe("/app/handledEcho", function(messageFrame) {
				let SUB_ID = messageFrame.body;
				headers.id = SUB_ID;
				client.subscribe("/topic/echoed", function(messageFrame) {
					let body = JSON.parse(messageFrame.body);
					let msgTag = document.createElement("p");
					if (body.sender == SUB_ID)
						msgTag.classList.add("my");
					msgTag.innerHTML = body.message + "[" + body.sender + "]";
					messageArea.appendChild(msgTag);
				}, headers);
			});
			let msgTag = document.createElement("p");
			msgTag.innerHTML = "연결수립";
			messageArea.appendChild(msgTag);
		}, function(error) {
			console.log(error);
			alert(error.headers.message);
		});
	})();
	
	function messageSend(event) {
		if (!client || !client.connected)
			throw "stomp 연결 수립 전";
		let body = {
			sender : headers.id,
			message : event.target.value
		}
		client.send("/app/handledEcho", headers, JSON.stringify(body));
		event.target.value = "";
		event.target.focus();
	}
	
	function disconnect(event) {
		if (!client || !client.connected)
			throw "stomp 연결 수립 전";
		client.disconnect();
		let msgTag = document.createElement("p");
		msgTag.innerHTML = "연결종료";
		messageArea.appendChild(msgTag);
	}
</script>