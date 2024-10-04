package com.jdbc.services;

import com.jdbc.connection.DatabaseConnection;
import com.jdbc.model.Category;
import com.jdbc.repository.CategoryRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements CategoryRepo {
    @Override
    public boolean save(Category category) {
        String insert = "INSERT INTO category (NAME) VALUES (?)";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setString(1, category.getName());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        return false;
    }

    @Override
    public boolean udpate(Category category) {
        String udpateCate = "UPDATE category SET NAME = ? WHERE id = ?";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(udpateCate);
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        return false;
    }

    @Override
    public void delete(Category category) {
        String deleteCat = "DELETE FROM category WHERE id = ?";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(deleteCat);
            ps.setInt(1, category.getId());
            int checkDelete = ps.executeUpdate();
            if (checkDelete == 1) {
                System.out.println("Delete Category Success");
            } else {
                System.out.println("Delete Category Failed");
            }
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
    }

    @Override
    public Category findById(int id) {
        String findById = "SELECT * FROM category WHERE id = ?";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(findById);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                return category;
            }
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        return null;
    }

    @Override
    public List<Category> findCategoryByName(String name) {
        String findCatByName = "SELECT * FROM category WHERE NAME LIKE ?";
        List<Category> categoryList = new ArrayList<Category>();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(findCatByName);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categoryList.add(category);
            }
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        return categoryList;
    }

    @Override
    public boolean checkIsExistCategory(String name) {
        String query = "SELECT * FROM category WHERE NAME = ?";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                System.out.println(category);
                return true;
            }
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        return false;
    }
}
