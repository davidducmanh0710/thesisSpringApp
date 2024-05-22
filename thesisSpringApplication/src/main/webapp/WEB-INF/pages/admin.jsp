1<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Hello ADMIN</h1>


<div class="container">
	<div>
		<c:url value="/admin/addUser" var="addUserUrl" />
		<a href="${addUserUrl}" class="btn btn-primary mt-1 mb-1">Add new
			user</a>
	</div>
	<table class="table table-striped">
		<thead>
			<tr>
				<th></th>
				<th>Avatar</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Role</th>
				<th>Faculty</th>
				<th>Actions</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="u">
				<tr>
					<td></td>
					<td><img class="card-img-top" src="${u.avatar}"
						style="width: 300px" alt="Card image"></td>
					<td>${u.firstName}</td>
					<td>${u.lastName}</td>
					<td>${formatterColumn.roleParentIdFormatter(u.roleId.id)}</td>
					<td></td>


					<td><button class="btn btn-primary">Update</button>
						<button class="btn btn-danger">Delete</button></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
</div>