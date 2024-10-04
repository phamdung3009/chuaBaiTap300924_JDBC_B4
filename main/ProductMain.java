package com.jdbc.main;

import com.jdbc.model.Category;
import com.jdbc.model.Product;
import com.jdbc.repository.ProductRepo;
import com.jdbc.services.ProductService;

public class ProductMain {


    public static void main(String[] args) {
        ProductRepo productRepo = new ProductService();
        ProductService productService = new ProductService();

        // ### - Insert
        Category category = new Category("Thuc Pham");
        Product product = new Product("Product New", 100.05);
        productService.checkExistCate(category,product);

        // ####-Update
        /*Category category = new Category(5);
        Product product = new Product(29, "Lo Vi", 29.3, category);
        if (productRepo.updateProductById(product)) {
            System.out.println("update product successful");
        } else {
            System.out.println("update product failed");
        }*/

        // ####-Delete
        /*if (productRepo.deleteProductByid(30)) {
            System.out.println("Delete product successfully");
        } else {
            System.out.println("Delete product failed");
        }*/

        // ####-Sort DESC
        /*productRepo.sortProductDestByCategory();*/
        // ####-Sort ASC
        /*productRepo.sortProductAscByCategory();*/

        // ####-search by name
        /*productRepo.fingByProductNameAndCategoryName("t", "th");*/
    }
}
