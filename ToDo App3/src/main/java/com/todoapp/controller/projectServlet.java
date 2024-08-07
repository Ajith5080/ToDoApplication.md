package com.todoapp.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todoapp.To.Project;
import com.todoapp.To.User;
import com.todoapp.dao.ProjectDao;

import java.io.IOException;
import java.util.List;

@WebServlet("/project")
public class projectServlet extends HttpServlet {

    private ProjectDao projectDAO;

    @Override
    public void init() throws ServletException {
        projectDAO = new ProjectDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<Project> projects = projectDAO.getProjectsByUserId(user.getId());
        request.setAttribute("projects", projects);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("create".equals(action)) {
            createProject(request, response);
        } else if ("update".equals(action)) {
            updateProject(request, response);
        }
    }

    private void createProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        User user = (User) request.getSession().getAttribute("user");

        Project project = new Project();
        project.setTitle(title);
        project.setUserId(user.getId());

        if (projectDAO.addProject(project)) {
            response.sendRedirect("project?action=view&projectId=" + project.getId());
        } else {
            request.setAttribute("error", "Project creation failed");
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }

    private void updateProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        String title = request.getParameter("title");

        Project project = projectDAO.getProjectById(projectId);
        project.setTitle(title);

        if (projectDAO.updateProject(project)) {
            response.sendRedirect("project?action=view&projectId=" + projectId);
        } else {
            request.setAttribute("error", "Project update failed");
            request.getRequestDispatcher("project.jsp").forward(request, response);
        }
    }
}

