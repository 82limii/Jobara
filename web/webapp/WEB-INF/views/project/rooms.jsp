<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/extras/spring-security">

<th:block th:replace="~{/layout/basic :: setContent(~{this :: content})}">
    <th:block th:fragment="content">
        <div class="container">
            <div>
                <ul th:each="room : ${list}">
                    <li><a th:href="@{/chat/room(roomId=${room.roomId})}">[[${room.name}]]</a></li>
                </ul>
            </div>
        </div>
        <form th:action="@{/chat/room}" method="post">
            <input type="text" name="name" class="form-control">
            <button class="btn btn-secondary">개설하기</button>
        </form>
    </th:block>
</th:block>

</html>

 <script th:inline="javascript">
            $(document).ready(function(){

                var roomName = [[${roomName}]];

                if(roomName != null)
                    alert(roomName + "방이 개설되었습니다.");

                $(".btn-create").on("click", function (e){
                    e.preventDefault();

                    var name = $("input[name='name']").val();

                    if(name == "")
                        alert("Please write the name.")
                    else
                        $("form").submit();
                });

            });
        </script>