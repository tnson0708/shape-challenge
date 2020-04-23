<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<title>Shape Challenge | Home</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">

</head>
<body>
	<div role="navigation">
		<div class="navbar navbar-inverse">
			<a href="/homepage" class="navbar-brand">Shape Challenge</a>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
                    <c:choose>
                        <c:when test="${isAdmin}">
                            <li><a href="/show-users">Users</a></li>
                            <li><a href="/show-shapes">Saved Shapes</a></li>
                            <li><a href="/manage-categories">Categories</a></li>
                            <li><a href="/manage-requirements">Requirements</a></li>
                        </c:when>
                     </c:choose>
                     <c:choose>
                        <c:when test="${empty fullName}">
					        <li><a href="/register">Sign Up</a></li>
                            <li><a href="/login">Sign In</a></li>
                        </c:when>
                        <c:when test="${not empty fullName}">
                            <li><a href="/choose-shape-type">Create New Shape</a></li>
                            <li><a href="/homepage">Welcome: ${fullName}</a></li>
                            <li><a href="/logout">Sign Out</a></li>
                        </c:when>
                     </c:choose>
				</ul>
			</div>
		</div>
	</div>

	<c:choose>
		<c:when test="${mode=='MODE_HOME'}">
			<div class="container" id="homediv">
				<div class="jumbotron text-center">
					<h1>Welcome to Shape Challenge</h1>
					<h2>Admin User</h2>
					<h3>Username: admin</h3>
					<h3>Password: 123456</h3>
				</div>
			</div>

		</c:when>

		<c:when test="${mode=='MODE_REGISTER'}">
			<div class="container text-center">
                <div class="form-group ">
                    <h3>New Registration</h3>
                </div>
				<hr>
				<form class="form-horizontal" method="POST" action="save-user">
					<input type="hidden" name="id" value="${user.id }" />
					<div class="form-group">
						<label class="control-label col-md-3">Username</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="username"
								value="${user.username }" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">First Name</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="firstname"
								value="${user.firstname }" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Last Name</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="lastname"
								value="${user.lastname }" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Age </label>
						<div class="col-md-3">
							<input type="text" class="form-control" name="age"
								value="${user.age }" />
						</div>
					</div>
                    <div class="form-group">
                        <label class="control-label col-md-3">Password</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" name="password"
                                value="${user.password }" />
                        </div>
                    </div>
                    <div class="form-group ">
                        <input type="submit" class="btn btn-primary" value="Register" />
                    </div>
				</form>
			</div>
		</c:when>
		<c:when test="${mode=='ALL_USERS' }">
			<div class="container text-center" id="tasksDiv">
				<h3>All Users</h3>
				<hr>
				<div class="table-responsive">
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th class="text-center">Id</th>
								<th class="text-center">User Name</th>
								<th class="text-center">First Name</th>
								<th class="text-center">LastName</th>
								<th class="text-center">Age</th>
								<th class="text-center">Delete</th>
								<th class="text-center">Edit</th>
								<th class="text-center">Saved Shapes</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="user" items="${users}">
								<tr>
									<td>${user.id}</td>
									<td>${user.username}</td>
									<td>${user.firstname}</td>
									<td>${user.lastname}</td>
									<td>${user.age}</td>
									<td><a href="/delete-user?id=${user.id}"><span
											class="glyphicon glyphicon-trash"></span></a></td>
									<td><a href="/edit-user?id=${user.id}"><span
											class="glyphicon glyphicon-pencil"></span></a></td>
                                    <td><a href="/load_user_shape?id=${user.id}"><span
											class="glyphicon glyphicon-th-large"></span></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:when>

        <c:when test="${mode=='ALL_SHAPES'}">
            <div class="container text-center" id="tasksDiv">
                <h3>All Saved Shapes ${userName}</h3>
                <hr>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th class="text-center">Id</th>
                                <th class="text-center">Shape Name</th>
                                <th class="text-center">Width</th>
                                <th class="text-center">Height</th>
                                <th class="text-center">High</th>
                                <th class="text-center">Area</th>
                                <th class="text-center">Delete</th>
                                <th class="text-center">Edit</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="shape" items="${shapes}">
                                <tr>
                                    <td>${shape.shapeId}</td>
                                     <td>${shape.name}</td>
                                    <td>${shape.width}</td>
                                    <td>${shape.height}</td>
                                    <td>${shape.high}</td>
                                    <td>${shape.area}</td>
                                    <td><a href="/delete-saved-shape?id=${shape.id}"><span
                                            class="glyphicon glyphicon-trash"></span></a></td>
                                    <td><a href="/edit-saved-shape?id=${shape.shapeId}"><span
                                            class="glyphicon glyphicon-pencil"></span></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:when>

        <c:when test="${mode=='ALL_CATEGORIES' }">
            <div class="container text-center" id="tasksDiv">
                <h3>All Categories</h3>
                <hr>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th class="text-center">Id</th>
                                <th class="text-center">Shape Name</th>
                                <th class="text-center">Category</th>
                                <th class="text-center">Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="category" items="${categories}">
                                <tr>
                                    <td>${category.categoryId.id}</td>
                                    <td>${category.shapeName}</td>
                                    <td>${category.categoryId.name}</td>
                                    <td><a href="/delete-shape-category?id=${category.categoryId.id}&name=${category.categoryId.name}"><span
                                            class="glyphicon glyphicon-trash"></span></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:when>

        <c:when test="${mode=='ALL_REQUIREMENTS'}">
            <div class="container text-center" id="tasksDiv">
                <h3>All Requirements</h3>
                <hr>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th class="text-center">Id</th>
                                <th class="text-center">Shape Name</th>
                                <th class="text-center">Width (a)</th>
                                <th class="text-center">Height (b)</th>
                                <th class="text-center">High (c/h)</th>
                                <th class="text-center">Edit</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="requirement" items="${requirements}">
                                <tr>
                                    <td class="id">${requirement.id}</td>
                                    <td>${requirement.name}</td>
                                             <td><input disabled="disabled" type="checkbox" class="width" name="width" value="${requirement.width}" ${requirement.width == null ? '' : requirement.width? 'checked' : ''}/></td>
                                             <td><input disabled="disabled" type="checkbox" class="height" name="height" value="${requirement.height}" ${requirement.height == null ? '' : requirement.height? 'checked' : ''}/></td>
                                             <td><input disabled="disabled" type="checkbox" class="high" name="high" value="${requirement.high}" ${requirement.high == null ? '' : requirement.high? 'checked' : ''}/></td>
                                      <td><a href="/edit-requirement?id=${requirement.id}">
                                      <span class="glyphicon glyphicon-pencil"></span></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:when>

		<c:when test="${mode=='MODE_UPDATE' }">
			<div class="container text-center">
				<h3>Update User</h3>
				<hr>
				<form class="form-horizontal" method="POST" action="update-user">
					<input type="hidden" name="id" value="${user.id }" />
					<div class="form-group">
						<label class="control-label col-md-3">Username</label>
						<div class="col-md-7">
							<input type="text" readonly="true" class="form-control" name="username"
								value="${user.username }" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">First Name</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="firstname"
								value="${user.firstname }" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Last Name</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="lastname"
								value="${user.lastname }" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Age </label>
						<div class="col-md-3">
							<input type="text" class="form-control" name="age"
								value="${user.age }" />
						</div>
					</div>
					 <div class="form-group">
                        <label class="control-label col-md-3">Administrator </label>
                        <div class="col-md-3">
                          <select name="isAdminRole">
                            <option value="ROLE_USER">USER</option>
                            <option value="ROLE_ADMIN">ADMIN</option>
                          </select>
                         </div>
                    </div>
					<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="Update" />
					</div>
				</form>
			</div>
		</c:when>

		<c:when test="${mode=='MODE_REGISTER_SHAPE' }">
			<div class="container text-center">
				<h3>Create a new shape</h3>
				<hr>
				<form class="form-horizontal" method="POST" action="save-shape">
                    <input type="hidden" name="shapeId" value="${shapeId}" />
                    <c:choose>
                        <c:when test="${isWidthRequire}">
                            <div class="form-group">
                                <label class="control-label col-md-3">Width (a)</label>
                                <div class="col-md-7">
                                    <input type="number" class="form-control" name="width"
                                        value="${savedShape.width}" />
                                </div>
                            </div>
                        </c:when>
                     </c:choose>
                      <c:choose>
                        <c:when test="${isHeightRequire}">
                            <div class="form-group">
                                <label class="control-label col-md-3">Height (b)</label>
                                <div class="col-md-7">
                                    <input type="number" class="form-control" name="height"
                                        value="${savedShape.height }" />
                                </div>
                            </div>
                        </c:when>
                     </c:choose>
                     <c:choose>
                        <c:when test="${isHighRequire}">
                            <div class="form-group">
                                <label class="control-label col-md-3">High (c, h)</label>
                                <div class="col-md-7">
                                    <input type="number" class="form-control" name="high"
                                        value="${savedShape.high }" />
                                </div>
                            </div>
                        </c:when>
                    </c:choose>
					<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="Save" />
					</div>
				</form>
			</div>
		</c:when>

		<c:when test="${mode=='CHOOSE_SHAPE_TYPE' }">
			<div class="container text-center">
				<h3>Update Saved Shape</h3>
				<hr>
				<form class="form-horizontal" method="POST" action="create-new-shape">
					 <div class="form-group">
                        <label class="control-label col-md-3">Geometry</label>
                        <div class="col-md-3">
                          <select name="shapeId">
                            <option value="1">triangle</option>
                            <option value="2">square</option>
                            <option value="3">rectangle</option>
                            <option value="4">parallelogram</option>
                            <option value="5">rhombus</option>
                            <option value="6">kite</option>
                            <option value="7">trapezium</option>
                            <option value="8">circle</option>
                            <option value="9">ellipse</option>
                          </select>
                         </div>
                    </div>
					<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="Apply" />
					</div>
				</form>
			</div>
		</c:when>

        <c:when test="${mode=='MODE_UPDATE_SHAPE' }">
			<div class="container text-center">
				<h3>Update Saved Shape</h3>
				<hr>
				<form class="form-horizontal" method="POST" action="update-saved-shape">
					<input type="hidden" name="id" value="${savedShape.id }" />
					<input type="hidden" name="shapeId" value="${savedShape.shapeId }" />
					<div class="form-group">
					    <h3>${savedShape.name}</h3>
					</div>
					 <c:choose>
                        <c:when test="${isWidthRequire}">
                            <div class="form-group">
                                <label class="control-label col-md-3">Width (a)</label>
                                <div class="col-md-7">
                                    <input type="number" class="form-control" name="width"
                                        value="${savedShape.width}" />
                                </div>
                            </div>
                        </c:when>
                     </c:choose>
                      <c:choose>
                        <c:when test="${isHeightRequire}">
                            <div class="form-group">
                                <label class="control-label col-md-3">Height (b)</label>
                                <div class="col-md-7">
                                    <input type="number" class="form-control" name="height"
                                        value="${savedShape.height }" />
                                </div>
                            </div>
                        </c:when>
                     </c:choose>
                     <c:choose>
                        <c:when test="${isHighRequire}">
                            <div class="form-group">
                                <label class="control-label col-md-3">High (c, h)</label>
                                <div class="col-md-7">
                                    <input type="number" class="form-control" name="high"
                                        value="${savedShape.high }" />
                                </div>
                            </div>
                        </c:when>
                    </c:choose>

					<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="Update" />
					</div>
				</form>
			</div>
		</c:when>

		<c:when test="${mode=='MODE_UPDATE_REQUIREMENT'}">
			<div class="container text-center">
				<h3>Update Requirement</h3>
				<hr>
				<form class="form-horizontal" method="POST" action="update-requirements">
					<input type="hidden" name="id" value="${requirement.id}" />
					<div class="form-group">
					    <h3>${savedShape.name}</h3>
					</div>
					<div class="form-group">
					    <label class="control-label col-md-3">Width (a)</label>
					    <div class="col-md-7">
                              <select name="width">
                                 <option value="true">REQUIRE</option>
                                 <option value="false" ${requirement.width == false? 'selected="selected"' : ''}>NOT REQUIRE</option>
                               </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">Height (b)</label>
                        <div class="col-md-7">
                              <select name="height">
                                 <option value="true">REQUIRE</option>
                                 <option value="false" ${requirement.height == false? 'selected="selected"' : ''}>NOT REQUIRE</option>
                               </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">High (c/h)</label>
                        <div class="col-md-7">
                              <select name="high">
                                 <option value="true" >REQUIRE</option>
                                 <option value="false" ${requirement.high == false? 'selected="selected"' : ''}>NOT REQUIRE</option>
                               </select>
                        </div>
                    </div>
					<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="Update" />
					</div>
				</form>
			</div>
		</c:when>

		<c:when test="${mode=='MODE_LOGIN' }">
			<div class="container text-center">
				<h3>User Login</h3>
				<hr>

				<form class="form-horizontal" method="POST" action="/j_spring_security_check" >
					<c:if test="${not empty error }">
						<div class= "alert alert-danger">
							<c:out value="${error }"></c:out>
							</div>
					</c:if>
					<div class="form-group">
						<label class="control-label col-md-3">Username</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="username"
								value="${user.username }" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Password</label>
						<div class="col-md-7">
							<input type="password" class="form-control" name="password"
								value="${user.password }" />
						</div>
					</div>
					<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="Login" />
					</div>
					</form>
					</div>
					</c:when>
	</c:choose>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="static/js/jquery-1.11.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
</body>
</html>