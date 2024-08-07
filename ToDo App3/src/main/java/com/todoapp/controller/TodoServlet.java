package com.todoapp.controller;

import javax.servlet.ServletException;   
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todoapp.To.Project;
import com.todoapp.To.Todo;
import com.todoapp.dao.ProjectDao;
import com.todoapp.dao.TodoDao;

import java.io.IOException;
import java.util.List;

@WebServlet("/todo")
public class TodoServlet extends HttpServlet {

    private TodoDao todoDAO;
    private ProjectDao projectDAO;

    @Override
    public void init() throws ServletException {
        todoDAO = new TodoDao();
        projectDAO = new ProjectDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("view".equals(action)) {
            viewProject(request, response);
        } else if ("delete".equals(action)) {
            deleteTodo(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            addTodo(request, response);
        } else if ("updateStatus".equals(action)) {
            updateTodoStatus(request, response);
        } else if ("updateDescription".equals(action)) {
            updateTodoDescription(request, response);
        }
    }

    private void viewProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        Project project = projectDAO.getProjectById(projectId);
        List<Todo> todos = todoDAO.getTodosByProjectId(projectId);

        request.setAttribute("project", project);
        request.setAttribute("todos", todos);
        request.getRequestDispatcher("project.jsp").forward(request, response);
    }

    private void addTodo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        int projectId = Integer.parseInt(request.getParameter("projectId"));

        Todo todo = new Todo();
        todo.setDescription(description);
        todo.setStatus("pending");
        todo.setProjectId(projectId);

        if (todoDAO.addTodo(todo)) {
            response.sendRedirect("todo?action=view&projectId=" + projectId);
        } else {
            request.setAttribute("error", "Todo creation failed");
            request.getRequestDispatcher("project.jsp").forward(request, response);
        }
    }

    private void updateTodoStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int todoId = Integer.parseInt(request.getParameter("todoId"));
        String status = request.getParameter("status");

        if (todoDAO.updateTodoStatus(todoId, status)) {
            int projectId = Integer.parseInt(request.getParameter("projectId"));
            response.sendRedirect("todo?action=view&projectId=" + projectId);
        } else {
            request.setAttribute("error", "Todo status update failed");
            request.getRequestDispatcher("project.jsp").forward(request, response);
        }
    }

    private void updateTodoDescription(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int todoId = Integer.parseInt(request.getParameter("todoId"));
        String description = request.getParameter("description");

        if (todoDAO.updateTodoDescription(todoId, description)) {
            int projectId = Integer.parseInt(request.getParameter("projectId"));
            response.sendRedirect("todo?action=view&projectId=" + projectId);
        } else {
            request.setAttribute("error", "Todo description update failed");
            request.getRequestDispatcher("project.jsp").forward(request, response);
        }
    }

    private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int todoId = Integer.parseInt(request.getParameter("todoId"));
        int projectId = Integer.parseInt(request.getParameter("projectId"));

        if (todoDAO.deleteTodoById(todoId)) {
            response.sendRedirect("todo?action=view&projectId=" + projectId);
        } else {
            request.setAttribute("error", "Todo deletion failed");
            request.getRequestDispatcher("project.jsp").forward(request, response);
        }
    }
}

