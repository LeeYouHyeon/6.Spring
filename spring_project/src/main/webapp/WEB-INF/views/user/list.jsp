<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<div class="container-md">
	<table class="table">
		<thead>
			<tr>
				<th scope="col">Email</th>
				<th scope="col">NickName</th>
				<th scope="col">Registered Date</th>
				<th scope="col">Last Login</th>
				<th scope="col">IsAdmin</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="uvo">
				<tr>
					<td>${uvo.email}</td>
					<td>${uvo.nickName}</td>
					<td>${uvo.regDate}</td>
					<td>${uvo.lastLogin}</td>
					<td>
						<c:if test="${uvo.authList.stream().anyMatch(a -> a.auth.equals('ROLE_ADMIN')).get()}">Y</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- paging area -->
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
			<li class="page-item">
				<a class="page-link ${ph.prev ? '' : 'disabled'}"
					href="/user/list?pageNo=${ph.startPage - 1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"
					aria-label="Previous">
					<span aria-hidden="true">&laquo;</span>
				</a>
			</li>

			<c:forEach var="i" begin="${ph.startPage}" end="${ph.endPage}">
				<li class="page-item">
					<a class="page-link ${ph.pgvo.pageNo == i ? 'active' : ''}"
						href="/user/list?pageNo=${i}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">
						${i}
					</a>
				</li>
			</c:forEach>

			<li class="page-item">
				<a class="page-link ${ph.next ? '' : 'disabled'}"
					href="/user/list?pageNo=${ph.endPage + 1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"
					aria-label="Next">
					<span aria-hidden="true">&raquo;</span>
				</a>
			</li>
		</ul>
	</nav>
</div>

<%@ include file="../layout/footer.jsp"%>