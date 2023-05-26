<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
img {
	height: auto;
}
</style>
<div class="row flex-grow">
  <div class="col-lg-4 mx-auto">
    <div class="auth-form-light text-left p-5">
      <div class="brand-logo">
        <img src="${cPath }/resources/estateAgency/img/jobaralogo.png" width="130px;">
      </div>
      <p></p>
      <h4>관리자 페이지</h4>
      <h6 class="font-weight-light">로그인 후 이용할 수 있습니다.</h6>
      <form class="pt-3" action="${cPath}/admin/loginProcess.do" method="post">
        <div class="form-group">
          <input class="form-control form-control-lg" id="exampleInputEmail1" name="adminId" placeholder="Username">
        </div>
        <div class="form-group">
          <input type="password" class="form-control form-control-lg" id="exampleInputPassword1" name="adminPass" placeholder="Password">
        </div>
        <div class="mt-3">
          <input class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn" type="submit" value="SIGN IN"/>	
        </div>
      </form>
    </div>
  </div>
</div>