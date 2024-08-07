<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
    <h2>Welcome, ${user.username}</h2>
    <h3>Create a new project</h3>
    <form action="project" method="post">
        <input type="hidden" name="action" value="create" />
        <div>
            <label for="title">Project Title:</label>
            <input type="text" id="title" name="title" required />
        </div>
        <button type="submit">Create Project</button>
    </form>
    <h3>Your Projects</h3>
    <c:forEach var="project" items="${projects}">
        <div>
            <a href="todo?action=view&projectId=${project.id}">${project.title}</a>
        </div>
    </c:forEach>
</body>
</html>