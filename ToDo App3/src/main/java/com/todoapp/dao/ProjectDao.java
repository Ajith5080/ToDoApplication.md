package com.todoapp.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.todoapp.To.Project;
import com.todoapp.util.JDBCUtil;

public class ProjectDao {

    public boolean addProject(Project project) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "INSERT INTO projects (title, user_id) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, project.getTitle());
            statement.setInt(2, project.getUserId());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Project> getProjectsByUserId(int userId) {
        List<Project> projects = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM projects WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setTitle(resultSet.getString("title"));
                project.setCreatedDate(resultSet.getTimestamp("created_date"));
                project.setUserId(resultSet.getInt("user_id"));
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    public Project getProjectById(int projectId) {
        Project project = null;
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM projects WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, projectId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setTitle(resultSet.getString("title"));
                project.setCreatedDate(resultSet.getTimestamp("created_date"));
                project.setUserId(resultSet.getInt("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    public boolean updateProject(Project project) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "UPDATE projects SET title = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, project.getTitle());
            statement.setInt(2, project.getId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}