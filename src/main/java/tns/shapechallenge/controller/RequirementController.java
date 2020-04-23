package tns.shapechallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tns.shapechallenge.model.Requirement;
import tns.shapechallenge.services.RequirementService;
import tns.shapechallenge.services.ShapeService;
import tns.shapechallenge.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static tns.shapechallenge.utils.ShapeUtils.*;

@Controller
public class RequirementController {
    @Autowired private UserService userService;
    @Autowired private ShapeService shapeService;
    @Autowired private RequirementService requirementService;

    @RequestMapping("/manage-requirements")
    public String getAllRequirements(HttpServletRequest request) {
        setUserAttributeForView(request, userService);
        List<Requirement> requirements = getRequirementsWithShapeName();
        request.setAttribute("requirements", requirements);
        request.setAttribute(MODE, "ALL_REQUIREMENTS");
        return HOMEPAGE;
    }

    private List<Requirement> getRequirementsWithShapeName() {
        List<Requirement> requirements = new ArrayList<>();
        for (Requirement requirement : requirementService.getAllRequirements()) {
            requirement.setName(shapeService.getShapeById(requirement.getId()).getName());
            requirements.add(requirement);
        }
        return requirements;
    }

    @PostMapping("/update-requirements")
    public String updateRequirements(@RequestParam Integer id, @RequestParam String width, @RequestParam String height, @RequestParam String high, HttpServletRequest request) throws Exception {
        setUserAttributeForView(request, userService);

        Requirement requirementById = requirementService.getRequirementById(id);
        if (requirementById != null) {
            requirementById.setWidth(Boolean.parseBoolean(width));
            requirementById.setHeight(Boolean.parseBoolean(height));
            requirementById.setHigh(Boolean.parseBoolean(high));
            requirementService.saveRequirement(requirementById);
        }

        List<Requirement> requirements = getRequirementsWithShapeName();
        request.setAttribute("requirements", requirements);
        request.setAttribute(MODE, "ALL_REQUIREMENTS");
        return HOMEPAGE;
    }

    @GetMapping("/getAllRequirements")
    public @ResponseBody
    List<Requirement> getAllRequirements() {
        List<Requirement> requirements = getRequirementsWithShapeName();
        return requirements;
    }
}
