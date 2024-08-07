<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
    body {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
    }
    .container {
        text-align: center;
    }
    h1 {
        margin-bottom: 30px;
    }
    a {
        display: inline-block;
        font-weight: bold;
        font-style: italic;
        margin: 20px;
        border: 2px solid black;
        border-radius: 15px;
        height: 50px;
        width: 150px;
        line-height: 50px;
        font-size: 25px;
        background-color: blue;
        color: white;
        text-align: center;
        text-decoration: none;
    }
</style>
<title>ToDo Application</title>
</head>
<body>
    <div class="container">
        <h1>Welcome to the ToDo Application</h1>
        <a href="login.jsp">Login</a>
        <a href="register.jsp">Register</a>
    </div>
</body>
</html>
