package tns.shapechallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tns.shapechallenge.model.Requirement;
import tns.shapechallenge.model.SavedShape;
import tns.shapechallenge.model.User;
import tns.shapechallenge.repository.SavedShapeRepository;
import tns.shapechallenge.services.RequirementService;
import tns.shapechallenge.services.ShapeService;
import tns.shapechallenge.services.UserService;

import javax.servlet.http.HttpServletRequest;

import static tns.shapechallenge.utils.ShapeUtils.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ShapeService shapeService;
    @Autowired private RequirementService requirementService;
    @Autowired
    private SavedShapeRepository savedShapeRepository;

    @RequestMapping(path = {"/homepage", "/"})
    public String homepage(HttpServletRequest request) {
        setUserAttributeForView(request, userService);
        request.setAttribute(MODE, "MODE_HOME");
        return HOMEPAGE;
    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request) {
        setUserAttributeForView(request, userService);
        request.setAttribute(MODE, "MODE_REGISTER");
        return HOMEPAGE;
    }

    @RequestMapping("/choose-shape-type")
    public String chooseShapeType(HttpServletRequest request) {
        setUserAttributeForView(request, userService);
        request.setAttribute(MODE, "CHOOSE_SHAPE_TYPE");
        return HOMEPAGE;
    }

    @RequestMapping("/create-new-shape")
    public String createNewShape(@RequestParam Integer shapeId, HttpServletRequest request) {
        Requirement requirementById = requirementService.getRequirementById(shapeId);
        if (requirementById != null) {
            request.setAttribute(IS_WIDTH_REQUIRED, requirementById.getWidth());
            request.setAttribute(IS_HEIGHT_REQUIRED, requirementById.getHeight());
            request.setAttribute(IS_HIGH_REQUIRED, requirementById.getHigh());
        }
        setUserAttributeForView(request, userService);
        request.setAttribute(MODE, "MODE_REGISTER_SHAPE");
        request.setAttribute("shapeId", shapeId);
        return HOMEPAGE;
    }

    @RequestMapping("/show-users")
    public String showAllUsers(HttpServletRequest request) {
        setUserAttributeForView(request, userService);
        request.setAttribute(USERS, userService.getAllUsers());
        request.setAttribute(MODE, "ALL_USERS");
        return HOMEPAGE;
    }

    @RequestMapping("/edit-saved-shape")
    public String editSavedShape(@RequestParam int id, HttpServletRequest request) {
        SavedShape savedShapeById = shapeService.getSavedShapeById(id);
        savedShapeById.setName(shapeService.getShapeById(savedShapeById.getShapeId()).getName());
        setUserAttributeForView(request, userService);
        Requirement requirementById = requirementService.getRequirementById(id);
        if (requirementById != null) {
            request.setAttribute(IS_WIDTH_REQUIRED, requirementById.getWidth());
            request.setAttribute(IS_HEIGHT_REQUIRED, requirementById.getHeight());
            request.setAttribute(IS_HIGH_REQUIRED, requirementById.getHigh());
        }
        request.setAttribute("savedShape", savedShapeById);
        request.setAttribute(MODE, "MODE_UPDATE_SHAPE");
        return HOMEPAGE;
    }

    @RequestMapping("/edit-requirement")
    public String editRequirement(@RequestParam int id, HttpServletRequest request) {
        Requirement requirementById = requirementService.getRequirementById(id);
        requirementById.setName(shapeService.getShapeById(requirementById.getId()).getName());
        setUserAttributeForView(request, userService);
        request.setAttribute("requirement", requirementById);
        request.setAttribute(MODE, "MODE_UPDATE_REQUIREMENT");
        return HOMEPAGE;
    }


    @GetMapping(path = "/getSavedShapeById")
    public @ResponseBody
    SavedShape getSavedShapeSById(@RequestParam Integer id, @RequestParam Double width, @RequestParam Double height, @RequestParam Double high) {
        return shapeService.getSavedShapeById(id);
    }

    @PostMapping("/save-user")
    public String saveUser(@RequestParam String username, @RequestParam String firstname, @RequestParam String lastname, @RequestParam int age, @RequestParam String password, HttpServletRequest request) throws Exception {
        if (userService.findByUserName(username) != null) {
            throw new Exception("User already exited!");
        }
        User user = new User(username, firstname, lastname, age, password);
        userService.saveMyUser(user, "ROLE_USER", false);
        setUserAttributeForView(request, userService);
        request.setAttribute(MODE, "MODE_HOME");
        return HOMEPAGE;
    }

    @PostMapping("/update-user")
    public String updateUser(@RequestParam String username, @RequestParam String firstname, @RequestParam String lastname, @RequestParam int age, @RequestParam String isAdminRole, HttpServletRequest request) {
        User userByName = userService.findByUserName(username);
        if (userByName != null) {
            userByName.setFirstname(firstname);
            userByName.setLastname(lastname);
            userByName.setAge(age);
            userService.saveMyUser(userByName, isAdminRole, true);
        }
        setUserAttributeForView(request, userService);
        request.setAttribute(MODE, "MODE_HOME");
        return HOMEPAGE;
    }

    @RequestMapping("/delete-user")
    public String deleteUser(@RequestParam int id, HttpServletRequest request) {
        userService.deleteMyUser(id);
        request.setAttribute(USERS, userService.getAllUsers());
        request.setAttribute(MODE, "ALL_USERS");
        return HOMEPAGE;
    }

    @RequestMapping("/edit-user")
    public String editUser(@RequestParam int id, HttpServletRequest request) {
        User userById = userService.getUserById(id);
        setUserAttributeForView(request, userService);
        request.setAttribute("user", userById);
        request.setAttribute(MODE, "MODE_UPDATE");
        request.setAttribute("isUpdate", true);
        return HOMEPAGE;
    }

    @RequestMapping(path = "/login")
    public String login(HttpServletRequest request) {
        request.setAttribute(MODE, "MODE_LOGIN");
        return HOMEPAGE;
    }
}
