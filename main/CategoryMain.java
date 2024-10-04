package com.jdbc.main;

import com.jdbc.model.Category;
import com.jdbc.repository.CategoryRepo;
import com.jdbc.services.CategoryService;

import java.util.List;

public class CategoryMain {
    public static void main(String[] args) {
        CategoryRepo categoryRepo = new CategoryService();
        System.out.println("---Inser---");
/*        Category category = new Category("Category Insert New 1");
        if (categoryRepo.save(category)) {
            System.out.println("Category Inserted");
        } else {
            System.out.println("Category Not Inserted");
        }*/

        System.out.println("---Update---");
/*        Category update = new Category(13, "Category Insert - Update");
        if (categoryRepo.udpate(update)) {
            System.out.println("Updated Successfully");
        } else {
            System.out.println("Update Failed");
        }*/
        System.out.println("---delete---");
        /*Category delete = new Category(12);
        categoryRepo.delete(delete);*/
        System.out.println("---findbyid---");
        // C1:
        /*Category category = categoryRepo.findById(9);
        System.out.println(category);*/
        //C2:
        /*CategoryService categoryService = new CategoryService();
        Category category = categoryService.findById(9);
        System.out.println(category);*/

        System.out.println("---findbyName---");
        List<Category> categories = categoryRepo.findCategoryByName("cat");
        System.out.println(categories);


        System.out.println("---CheckExistByName---");
        Category checkExist = new Category("Gia dung");
        if (categoryRepo.checkIsExistCategory(checkExist.getName())) {
            System.out.println("Find success");
        } else {
            System.out.println("Find fail");
        }
    }
}
