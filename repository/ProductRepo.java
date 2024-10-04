package com.jdbc.repository;

import com.jdbc.model.Category;
import com.jdbc.model.Product;

public interface ProductRepo {
    boolean insertProduct(Product product);

    boolean updateProductById(Product product);

    boolean deleteProductByid(int id);

    void sortProductDestByCategory();

    void sortProductAscByCategory();

    void fingByProductNameAndCategoryName(String productName, String categoryName);
}
