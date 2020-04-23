package tns.shapechallenge.utils;

import tns.shapechallenge.model.User;
import tns.shapechallenge.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

public class ShapeUtils {

    public static final String MODE = "mode";
    public static final String SHAPES = "shapes";
    public static final String CATEGORIES = "categories";
    public static final String HOMEPAGE = "homepage";
    public static final String IS_WIDTH_REQUIRED = "isWidthRequire";
    public static final String IS_HEIGHT_REQUIRED = "isHeightRequire";
    public static final String IS_HIGH_REQUIRED = "isHighRequire";
    public static final String USERS = "users";

    public static void setUserAttributeForView(HttpServletRequest request, UserService userService) {
        Principal userPrincipal = request.getUserPrincipal();
        if (userPrincipal != null) {
            User user = userService.findByUserName(userPrincipal.getName());
            request.setAttribute("fullName", user.getFirstname() + " " + user.getLastname());
            isAdmin(request, user);
        }
    }

    private static void isAdmin(HttpServletRequest request, User user) {
        if (user.getRoles().contains("ROLE_ADMIN")) {
            request.setAttribute("isAdmin", true);
        }
    }
}
