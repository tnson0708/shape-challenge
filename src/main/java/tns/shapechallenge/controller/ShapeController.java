package tns.shapechallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tns.shapechallenge.model.*;
import tns.shapechallenge.services.ShapeService;
import tns.shapechallenge.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static tns.shapechallenge.utils.ShapeUtils.*;
import static tns.shapechallenge.utils.ShapeUtils.setUserAttributeForView;

@Controller
public class ShapeController {
    @Autowired
    private UserService userService;
    @Autowired
    private ShapeService shapeService;

    @RequestMapping("/load_user_shape")
    public String getSavedShapesForUser(@RequestParam int id, HttpServletRequest request) {
        setUserAttributeForView(request, userService);
        List<SavedShape> savedShapes = getAllSavedShapeWithShapeName(shapeService.getSavedShapesForUser(id));
        request.setAttribute(SHAPES, savedShapes);
        request.setAttribute(MODE, "ALL_SHAPES");
        request.setAttribute("userName", " for " + userService.getUserById(id).getUsername());
        return HOMEPAGE;
    }

    @RequestMapping("/delete-saved-shape")
    public String deleteSavedShape(@RequestParam int id, HttpServletRequest request) {
        shapeService.deleteSavedShapeById(id);
        List<SavedShape> savedShapes = getAllSavedShapeWithShapeName(shapeService.getAllSavedShapes());
        request.setAttribute(SHAPES, savedShapes);
        request.setAttribute(MODE, "ALL_SHAPES");
        return HOMEPAGE;
    }

    @RequestMapping("/show-shapes")
    public String showShapesOnUI(HttpServletRequest request) {
        setUserAttributeForView(request, userService);
        List<SavedShape> savedShapes = getAllSavedShapeWithShapeName(shapeService.getAllSavedShapes());
        request.setAttribute(SHAPES, savedShapes);
        request.setAttribute(MODE, "ALL_SHAPES");
        return HOMEPAGE;
    }

    @PostMapping("/update-saved-shape")
    public String updateSavedShape(@RequestParam Integer id, @RequestParam Integer shapeId, @RequestParam Double width, @RequestParam(required = false) Double height, @RequestParam(required = false) Double high, HttpServletRequest request) throws Exception {
        height = height == null ? 0 : height;
        high = high == null ? 0 : high;
        SavedShape savedShapeById = shapeService.getSavedShapeById(id);
        if (savedShapeById != null) {
            savedShapeById.setWidth(width);
            savedShapeById.setHeight(height);
            savedShapeById.setHigh(high);
            savedShapeById.setArea(submitShape(shapeId, width, height, high).getArea());
            shapeService.saveShape(savedShapeById);
        }
        setUserAttributeForView(request, userService);
        List<SavedShape> savedShapes = getAllSavedShapeWithShapeName(getAllShapes());
        request.setAttribute(SHAPES, savedShapes);
        request.setAttribute(MODE, "ALL_SHAPES");
        return HOMEPAGE;
    }

    @PostMapping("/save-shape")
    public String saveShape(@RequestParam Integer shapeId, @RequestParam Double width, @RequestParam(required = false) Double height, @RequestParam(required = false) Double high, HttpServletRequest request) throws Exception {
        height = height == null ? 0 : height;
        high = high == null ? 0 : high;
        SavedShape savedShapeById = new SavedShape(shapeId, width, height, high);
        savedShapeById.setArea(submitShape(shapeId, width, height, high).getArea());
        Principal userPrincipal = request.getUserPrincipal();
        if (userPrincipal != null) {
            savedShapeById.setUser(userService.findByUserName(userPrincipal.getName()));
        }
        shapeService.saveShape(savedShapeById);
        setUserAttributeForView(request, userService);
        request.setAttribute(MODE, "MODE_HOME");
        return HOMEPAGE;
    }

    @GetMapping(path = "/submitShape")
    public @ResponseBody
    Shape submitShape(@RequestParam Integer id, @RequestParam Double width, @RequestParam Double height, @RequestParam Double high) throws Exception {
        if (width < 0 || height < 0 || high < 0) {
            throw new Exception("The dimension must be greater than 0");
        }
        Shape shape = shapeService.getShapeById(id);
        boolean isGeometryValid = width + height > high && height + high > width && high + width > height;
        switch (id) {
            case 1:
                if (!isGeometryValid) {
                    throw new Exception("Invalid triangle");
                }
                Double halfCircumferenceTriangle = (width + height + high) / 2;
                shape.setArea(Math.sqrt(halfCircumferenceTriangle * (halfCircumferenceTriangle - width) * (halfCircumferenceTriangle - height) * (halfCircumferenceTriangle - high)));
                break;
            case 2:
                shape.setArea(width * width);
                break;
            case 3:
                shape.setArea(width * height);
                break;
            case 4:
            case 5:
                shape.setArea(width * high);
                break;
            case 6:
                if (isGeometryValid) {
                    throw new Exception("Invalid kite");
                }
                Double halfCircumferenceKite = (width + height + high) / 2;
                shape.setArea(2 * Math.sqrt(halfCircumferenceKite * (halfCircumferenceKite - width) * (halfCircumferenceKite - height) * (halfCircumferenceKite - high)));
                break;
            case 7:
                shape.setArea(((width + height) / 2) * high);
                break;
            case 8:
                shape.setArea(Math.PI * width * width);
                break;
            case 9:
                shape.setArea(Math.PI * width * height);
                break;
        }
        return shape;
    }

    private List<SavedShape> getAllSavedShapeWithShapeName(List<SavedShape> allSavedShapes) {
        List<SavedShape> savedShapes = new ArrayList<>();
        for (SavedShape savedShape : allSavedShapes) {
            savedShape.setName(shapeService.getShapeById(savedShape.getShapeId()).getName());
            savedShapes.add(savedShape);
        }
        return savedShapes;
    }

    @GetMapping("/getAllShapes")
    public @ResponseBody
    List<SavedShape> getAllShapes() {
        return getAllSavedShapeWithShapeName(shapeService.getAllSavedShapes());
    }

    @PostMapping(path = "/saveShape")
    public @ResponseBody
    String saveShape(@RequestParam Integer shapeId, @RequestParam Double width, @RequestParam Double height, @RequestParam Double high) throws Exception {
        SavedShape savedShape = new SavedShape(shapeId, width, height, high);
        savedShape.setArea(submitShape(shapeId, width, height, high).getArea());
        shapeService.saveShape(savedShape);
        return "Saved successfully";
    }

}
