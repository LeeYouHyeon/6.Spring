<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ page session="false" %> : 성능 향상을 위해 사용하는 경우가 있음 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp"%>
<div class="container-md mt-3">
	<h1>Hello world!</h1>
	<P>The time on the server is ${serverTime}.</P>
</div>
<script type="text/javascript">
	const modify_msg = `<c:out value="${modify_msg}" />`;
	if (modify_msg === 'ok') alert('개인정보가 수정되었습니다. 다시 로그인해주세요.');
	else if (modify_msg === 'fail') alert('개인정보 수정에 실패했습니다. 다시 시도해주세요.');
	
	const remove_msg = `<c:out value="${remove_msg}" />`;
	if (remove_msg === 'ok') alert('회원 탈퇴가 완료되었습니다.');
	else if (remove_msg === 'fail') alert('회원 탈퇴에 실패했습니다. 다시 시도해주세요.');
</script>

<%@ include file="layout/footer.jsp"%>