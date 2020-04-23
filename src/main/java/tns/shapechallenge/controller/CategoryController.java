package tns.shapechallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tns.shapechallenge.model.Category;
import tns.shapechallenge.model.CategoryId;
import tns.shapechallenge.services.CategoryService;
import tns.shapechallenge.services.ShapeService;
import tns.shapechallenge.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static tns.shapechallenge.utils.ShapeUtils.*;
import static tns.shapechallenge.utils.ShapeUtils.HOMEPAGE;

@Controller
public class CategoryController {
    @Autowired private UserService userService;
    @Autowired private ShapeService shapeService;
    @Autowired private CategoryService categoryService;

    @RequestMapping("/manage-categories")
    public String getAllCategories(HttpServletRequest request) {
        setUserAttributeForView(request, userService);
        List<Category> allCategories = getAllCategoryWithShapeName();
        request.setAttribute(CATEGORIES, allCategories);
        request.setAttribute(MODE, "ALL_CATEGORIES");
        return HOMEPAGE;
    }

    @RequestMapping("/delete-shape-category")
    public String deleteShapeCategory(@RequestParam int id, @RequestParam String name, HttpServletRequest request) {
        categoryService.deleteShapeCategory(new CategoryId(id, name));
        setUserAttributeForView(request, userService);
        List<Category> allCategories = getAllCategoryWithShapeName();
        request.setAttribute(CATEGORIES, allCategories);
        request.setAttribute(MODE, "ALL_CATEGORIES");
        return HOMEPAGE;
    }

    private List<Category> getAllCategoryWithShapeName() {
        List<Category> allCategories = new ArrayList<>();
        for (Category category : categoryService.getAllCategories()) {
            category.setShapeName(shapeService.getShapeById(category.getCategoryId().getId()).getName());
            allCategories.add(category);
        }
        return allCategories;
    }

}
