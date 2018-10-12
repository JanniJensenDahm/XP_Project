package com.adventure;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SessionAttributes({"accessLevel", "Booking"})
@Controller
public class HomeController {


    @GetMapping({"/", "index"})
    public String login(Model model) {
        //Clear access level
        model.addAttribute("accessLevel", "");
        return "index";
    }

    @PostMapping({"/", "/login"})
    public String index(Model model, @RequestParam String username, @RequestParam String password) {
        //Create login
        Login login = new Login(username, password);
        //Verify user
        login.verifyUser();
        model.addAttribute("accessLevel", login.getAccessLevel());
        //Redirect based on accessLevel
        return "redirect:/" + login.redirect();
    }

    @GetMapping("booking")
    public String booking(Model model) {
        return "booking";
    }

    @PostMapping(value = "booking", params = "create_booking_btn")
    public String bookingPost(Model model) {
        return "redirect:/create_booking";
    }

    @PostMapping(value = "booking", params = "edit_booking_btn")
    public String editbookingPost(Model model) {
        return "redirect:/edit_booking";
    }


    @PostMapping(value = "booking", params = "check_booking_btn")
    public String checkbookingPost(Model model) {
        return "redirect:/check_booking";
    }

    @GetMapping("/create_booking")
    public String createBooking(Model model) {
        Booking booking = new Booking();
        //TODO Add bookingEmployee based on logged in user
        model.addAttribute("Booking", booking);
        return "create_booking";
    }

    //Booking activities
    @PostMapping(value = "/create_booking", params = "minigolf")
    public String createBookingPost(@ModelAttribute Booking booking, Model model) {
        model.addAttribute("Booking", booking);
        return "redirect:/add_activity";
    }

    @PostMapping(value = "/create_booking", params = "gokart")
    public String gokartBookingPost(@ModelAttribute Booking booking, Model model) {
        model.addAttribute("Booking", booking);
        return "redirect:/add_activity";
    }

    @PostMapping(value = "/create_booking", params = "paintball")
    public String paintballBookingPost(@ModelAttribute Booking booking, Model model) {
        model.addAttribute("Booking", booking);
        return "redirect:/add_activity";
    }

    @PostMapping(value = "/create_booking", params = "sumo")
    public String sumoBookingPost(@ModelAttribute Booking booking, Model model) {
        model.addAttribute("Booking", booking);
        return "redirect:/add_activity";
    }


    @GetMapping("/add_activity")
    public String addActivities(Model model) {
//        public String addActivities(Model model, @SessionAttribute("Booking") Booking booking) {
//        model.addAttribute("Booking",booking);
        model.addAttribute("Activity", new Activity());

        //Add activities should have booking as session.Booking

        model.addAttribute("activity", new Activity());


        return "add_activity";
    }


    @PostMapping("/add_activities")
    public String asdddActivities(@ModelAttribute Booking booking, @ModelAttribute Activity activity) {
        //    booking.addActivity(activity);

        return "add_activities";

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
