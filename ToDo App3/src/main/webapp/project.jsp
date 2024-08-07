<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Project</title>
<style>
    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
    }
    table, th, td {
        border: 1px solid black;
    }
    th, td {
        padding: 10px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
    }
    form {
        display: inline;
    }
</style>
</head>
<body>
    <h2>${project.title}</h2>
    <h3>Add a new Todo</h3>
    <form action="todo" method="post">
        <input type="hidden" name="action" value="add" />
        <input type="hidden" name="projectId" value="${project.id}" />
        <div>
            <label for="description">Description:</label>
            <input type="text" id="description" name="description" required />
        </div>
        <button type="submit">Add Todo</button>
    </form>

    <h3>Todos</h3>
    <h4>Pending</h4>
    <table>
        <tr>
            <th>Description</th>
            <th>Created Date</th>
            <th>Updated Date</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="todo" items="${todos}">
            <c:if test="${todo.status == 'pending'}">
                <tr>
                    <td>${todo.description}</td>
                    <td>${todo.createdDate}</td>
                    <td>${todo.updatedDate}</td>
                    <td>
                        <form action="todo" method="post">
                            <input type="hidden" name="action" value="updateStatus" />
                            <input type="hidden" name="todoId" value="${todo.id}" />
                            <input type="hidden" name="status" value="complete" />
                            <button type="submit">Mark as Complete</button>
                        </form>
                        <form action="todo" method="post">
                            <input type="hidden" name="action" value="updateDescription" />
                            <input type="hidden" name="todoId" value="${todo.id}" />
                            <input type="text" name="description" value="${todo.description}" required />
                            <button type="submit">Update Description</button>
                        </form>
                        <form action="todo" method="get">
                            <input type="hidden" name="action" value="delete" />
                            <input type="hidden" name="todoId" value="${todo.id}" />
                            <input type="hidden" name="projectId" value="${project.id}" />
                            <button type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
    </table>

    <h4>Completed</h4>
    <table>
        <tr>
            <th>Description</th>
            <th>Created Date</th>
            <th>Updated Date</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="todo" items="${todos}">
            <c:if test="${todo.status == 'complete'}">
                <tr>
                    <td>${todo.description}</td>
                    <td>${todo.createdDate}</td>
                    <td>${todo.updatedDate}</td>
                    <td>
                        <form action="todo" method="post">
                            <input type="hidden" name="action" value="updateStatus" />
                            <input type="hidden" name="todoId" value="${todo.id}" />
                            <input type="hidden" name="status" value="pending" />
                            <button type="submit">Mark as Pending</button>
                        </form>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</body>
</html>
