<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container-md">
	<h3>Board Register Page</h3>

	<!-- enctype : multipart/form-data -->
	<form action="/board/insert"
		method="post"
		enctype="multipart/form-data">
		<!-- bvo area -->
		<div class="mb-3">
			<label for="title" class="form-label">Title</label>
			<input type="text"
				class="form-control"
				id="title"
				placeholder="Title"
				name="title"
				required>
		</div>
		<div class="mb-3">
			<sec:authentication property="principal" var="pri"/>
			<label for="writer" class="form-label">Writer : </label>
			<input type="hidden"
				class="form-control"
				id="writer"
				name="writer"
				value="${pri.uvo.email}">${pri.uvo.nickName}
		</div>
		<div class="mb-3">
			<label for="content" class="form-label">Content</label>
			<textarea class="form-control"
				id="content"
				rows="5"
				name="content"
				required></textarea>
		</div>
		
		<!-- file add area -->
		<div class="mb-3">
			<input type="file"
				class="form-control"
				id="file"
				name="files"
				multiple
				style="display: none">
			<button type="button"
				class="btn btn-outline-primary"
				id="trigger">Upload files</button>
		</div>
		
		<!-- file list area -->
		<div class="mb-3" id="fileZone"></div>
		
		<!-- button -->
		<button type="submit" class="btn btn-success" id="regBtn">등록</button>
	</form>
	
	<script type="text/javascript" src="/resources/js/boardRegisterFile.js"></script>
</div>

<%@ include file="../layout/footer.jsp"%>