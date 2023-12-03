package Desertniy.Lab5.controllers;

import Desertniy.Lab5.model.PropertyBody;
import Desertniy.Lab5.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainPropertyControllers {

    @Autowired
    PropertyService data;

    public Model Models(Model model){
        Iterable<PropertyBody> objects = data.getObjects();
        model.addAttribute("properties", objects);
        model.addAttribute("property", new PropertyBody());
        model.addAttribute("street_act", "");
        return model;
    }
    @GetMapping("/property")
    public String propertyGet(Model model){
        Model models = Models(model);
        return "properties";
    }

    @PostMapping("/property")
    public String propertyPost(@Valid @ModelAttribute("property") PropertyBody body, Model model){
        if (data.getObj(body.getStreet()) == null)
            data.addProperty(body);
        else
            model.addAttribute("error", true);
        return "redirect:/property";
    }

    @PostMapping("/delete/{street_act:.+}")
    public String propertyDelete(@RequestParam("street_act") String street_act){
        data.remove(street_act);
        return "redirect:/property";
    }

    @PostMapping("/put")
    public String propertyPut(@Valid @ModelAttribute("property") PropertyBody body){
        data.update_obj(body);
        return "redirect:/property";
    }

    @GetMapping("/property/{street_act:.+}")
    public String propertyGetStreet(@RequestParam("street_act") String street_act, Model model){
        PropertyBody rp = data.getObj(street_act);
        model.addAttribute("property_get", rp);
        Model models = Models(model);
        return "properties";
    }

}
