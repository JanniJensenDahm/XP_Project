package com.adventure;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SessionAttributes("accessLevel")
@Controller
public class HomeController {


    @GetMapping({"/", "index"})
    public String login(Model model) {
        //Clear access level
        model.addAttribute("accessLevel", "");
        return "index";
    }

    @PostMapping({"/","/login"})
    public String index(Model model, @RequestParam String username, @RequestParam String password) {
        //Create login
        Login login = new Login(username, password);
        //Verify user
        login.verifyUser();
        model.addAttribute("accessLevel", login.getAccessLevel());
        //Redirect based on accessLevel
        return "redirect:/" + login.redirect();
    }


    @GetMapping("/home")
    public String home() {
        return "home";
    }

    /**
     * Redirects the user to login, and resets accessLevel
     * @return String Returns a String that redirects the user to login
     */
    @GetMapping ("/logout")
    public String logout(){

        return "redirect:/login";
    }
}
