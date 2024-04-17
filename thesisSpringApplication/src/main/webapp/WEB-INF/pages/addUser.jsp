<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
body {
	padding-top: 70px; /* Để tránh bị navbar che phủ phần nội dung */
}

.form-background {
	background-color: #d1ecf1; /* Màu nền xanh */
	padding: 20px;
	border-radius: 10px;
	margin-top: 50px;
}

h1 {
	text-align: center;
	margin-bottom: 30px;
}

.form-floating {
	margin-bottom: 1rem;
}

.form-floating label {
	color: #495057;
}
</style>

<div class="container-fluid">
	<div class="form-background">
		<h1 class="text-center">Add User</h1>
		<div class="row justify-content-center">
			<div class="col-md-6">
				<c:url value="/admin/add/user" var="actionAddUser" />

				<form:form method="post" action="${actionAddUser}"
					modelAttribute="user">

					<div class="row g-3">
						<div class="col">
							<div class="form-floating">
								<form:input path="firstName" type="text" class="form-control"
									id="firstNameUser" placeholder="First Name" required="true" />
								<label for="firstNameUser">First Name <span
									class="text-danger">*</span></label>
							</div>
						</div>
						<div class="col">
							<div class="form-floating">
								<form:input path="lastName" type="text" class="form-control"
									id="lastNameUser" placeholder="Last Name" required="true" />
								<label for="lastNameUser">Last Name <span
									class="text-danger">*</span>
								</label>
							</div>
						</div>
					</div>
					<div class="row g-3">
						<div class="col">
							<div class="form-floating">
								<form:input path="email" type="email" class="form-control"
									id="email" placeholder="Email" required="true" name="email" />
								<label for="email">Email <span class="text-danger">*</span>
								</label>
							</div>

						</div>
						<div class="col">
							<div class="form-floating">
								<form:input path="phone" type="text" class="form-control"
									id="phone" placeholder="Phone" required="true" />
								<label for="phone">Phone <span class="text-danger">*</span>
								</label>
							</div>
						</div>
					</div>
					<div class="row g-3">
						<div class="col">
							<div class="form-floating">
								<form:input path="address" type="text" class="form-control"
									id="address" placeholder="Address" required="true" />
								<label for="address">Address <span class="text-danger">*</span>
								</label>
							</div>
						</div>
						<div class="col">
							<div class="form-floating">
								<form:select path="gender" class="form-select" id="gender"
									required="true">
									<option value="">Choose Gender...</option>
									<option value="male">Male</option>
									<option value="female">Female</option>
								</form:select>
								<label for="gender">Gender <span class="text-danger">*</span></label>
							</div>
						</div>
					</div>

					<div class="row g-3">
						<div class="col">
							<div class="form-floating">
								<select class="form-select" name="roleIdName" id="roleId" required>
									<c:forEach items="${roles}" var="r">
										<option value="${r.id}">${r.name}</option>
									</c:forEach>
								</select> <label for="roleId">ROLE ID <span class="text-danger">*</span></label>
							</div>
						</div>
						<div class="col">
							<div class="form-floating">
								<select class="form-select" name="facultyIdName" id="facultyId" required>
									<c:forEach items="${faculties}" var="f">
										<option value="${f.id}">${f.name}</option>
									</c:forEach>
								</select> <label for="facultyId">FACULTY ID <span class="text-danger">*</span></label>
							</div>
						</div>
					</div>
					<div class="d-grid mb-3">
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>