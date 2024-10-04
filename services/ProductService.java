package com.jdbc.services;

import com.jdbc.connection.DatabaseConnection;
import com.jdbc.model.Category;
import com.jdbc.model.Product;
import com.jdbc.repository.ProductRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductService implements ProductRepo {

    @Override
    public boolean insertProduct(Product product) {
        String addProductQuery = "INSERT INTO product(name, price, cat_id) VALUES (?, ?, ?)";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(addProductQuery);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getCategory().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        return false;
    }

    @Override
    public boolean updateProductById(Product product) {
        try {
            String update = "UPDATE product SET NAME = ?, price = ?, cat_id = ? WHERE id = ?";
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(update);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getCategory().getId());
            ps.setInt(4, product.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        return false;
    }

    @Override
    public boolean deleteProductByid(int id) {
        String delete = "DELETE FROM product WHERE id = ?";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(delete);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        return false;
    }

    @Override
    public void sortProductDestByCategory() {
        String sortDESC = "SELECT * FROM product p JOIN category c ON p.cat_id = c.id ORDER BY p.price DESC";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sortDESC);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                Category c = new Category();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPrice(rs.getDouble(3));
                p.setCategory(c);
                c.setId(rs.getInt(4));
                c.setId(rs.getInt(5));
                c.setName(rs.getString(6));
                System.out.println(p + " - " + c);
            }

        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
    }

    @Override
    public void sortProductAscByCategory() {
        String sortASC = "SELECT * FROM product p JOIN category c ON p.cat_id = c.id ORDER BY p.price ASC";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sortASC);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                Category c = new Category();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPrice(rs.getDouble(3));
                p.setCategory(c);
                c.setId(rs.getInt(4));
                c.setId(rs.getInt(5));
                c.setName(rs.getString(6));
                System.out.println(p + " - " + c);
            }
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
    }

    @Override
    public void fingByProductNameAndCategoryName(String productName, String categoryName) {
        String selectLike = "SELECT * FROM product p JOIN category c ON p.cat_id = c.id WHERE p.NAME LIKE ? AND c.NAME LIKE ?";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(selectLike);
            ps.setString(1, "%" + productName + "%");
            ps.setString(2, "%" + categoryName + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                Category c = new Category();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPrice(rs.getDouble(3));
                p.setCategory(c);
                c.setId(rs.getInt(4));
                c.setId(rs.getInt(5));
                c.setName(rs.getString(6));
                System.out.println(p + " - " + c);
            }

        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
    }

    public void checkExistCate(Category category, Product product) {
        CategoryService categoryService = new CategoryService();
        ProductService productService = new ProductService();
        if (categoryService.checkIsExistCategory(category.getName())) {
            List<Category> categories = categoryService.findCategoryByName(category.getName());
            Category c = categories.get(0);
            product.setCategory(c);
            if (productService.insertProduct(product)) {
                System.out.println("Insert Product Successfully.");
            } else {
                System.out.println("Insert Product Failed.");
            }
        } else {
            categoryService.save(category);
            List<Category> categories = categoryService.findCategoryByName(category.getName());
            category = categories.get(0);
            product.setCategory(category);
            if (productService.insertProduct(product)) {
                System.out.println("Insert Product Successfully.");
            } else {
                System.out.println("Insert Product Failed.");
            }
        }
    }
}
