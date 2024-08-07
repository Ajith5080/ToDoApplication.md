package com.todoapp.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.todoapp.To.Todo;
import com.todoapp.util.JDBCUtil;

public class TodoDao {

    public boolean addTodo(Todo todo) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "INSERT INTO todos (description, status, project_id) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, todo.getDescription());
            statement.setString(2, todo.getStatus());
            statement.setInt(3, todo.getProjectId());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Todo> getTodosByProjectId(int projectId) {
        List<Todo> todos = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM todos WHERE project_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, projectId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Todo todo = new Todo();
                todo.setId(resultSet.getInt("id"));
                todo.setDescription(resultSet.getString("description"));
                todo.setStatus(resultSet.getString("status"));
                todo.setCreatedDate(resultSet.getTimestamp("created_date"));
                todo.setUpdatedDate(resultSet.getTimestamp("updated_date"));
                todo.setProjectId(resultSet.getInt("project_id"));
                todos.add(todo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }

    public boolean updateTodoStatus(int todoId, String status) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "UPDATE todos SET status = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, status);
            statement.setInt(2, todoId);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateTodoDescription(int todoId, String description) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "UPDATE todos SET description = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, description);
            statement.setInt(2, todoId);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteTodoById(int todoId) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "DELETE FROM todos WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, todoId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}