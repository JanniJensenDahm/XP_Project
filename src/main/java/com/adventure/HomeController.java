package com.adventure;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SessionAttributes({"accessLevel", "Booking"})
@Controller
public class HomeController {
    private Login login = null;


    @GetMapping({"/", "index"})
    public String login(Model model) {
        //Clear access level
        model.addAttribute("accessLevel", "");
        Booking b = new Booking();
//        b.addBooking();


        return "index";
    }

    @GetMapping("/owner_page")
    public String owner_page(Model model) {
        return "owner_page";
    }

    @PostMapping(value = "/owner_page", params = "shop_btn")
    public String shopPage() {
        return "redirect:/shop";
    }

    @PostMapping(value = "/owner_page", params = "create_booking_btn")
    public String createBoooking() {
        return "redirect:/create_booking";
    }

    @PostMapping(value = "/owner_page", params = "check_booking_btn")
    public String bookinglist() {
        return "redirect:/booking_list";
    }

    @PostMapping(value = "/owner_page", params = "check_activity_btn")
    public String newActivity() {
        return "redirect:/newActivity";
    }

@SuppressWarnings("Duplicates")
    @GetMapping("/shop")
    public String shop_page(Model model) {

        ArrayList<Product> productlist = Product.getProducts();

        Set<String> categories = new HashSet<>();
        for (int i = 0; i < productlist.size(); i++) {
            categories.add(productlist.get(i).getCategory());
        }

        model.addAttribute("categories", categories);
        model.addAttribute("productlist", productlist);


        return "shop";
    }

    @SuppressWarnings("Duplicates")
    @PostMapping("/shop")
    public String shop_page1(Model model) {
        ArrayList<Product> productlist = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            productlist.add(new Product("Sodavand","Cola" +i, 150.4,85,i+1));
        }


        for (int i = 10; i <  15; i++) {
            productlist.add(new Product("Chips","Bugles"+i, 25,85,i));

        }


        for (int i = 16; i < 21 ; i++) {
            productlist.add(new Product("T-shirt","Jesper Fan T-Shirt" + i, 300,85,i+1));
            productlist.add(new Product("T-shirt","Mikkel Fan T-Shirt" +i, 300,85,i+1));
        }
        //Count categories

        Set<String> categories = new HashSet<>();
        for (int i = 0; i < productlist.size(); i++) {
            categories.add(productlist.get(i).getCategory());
        }

        //Add cat
        // categories.add("T-shirt");

        //Add products
//        productlist.add();

        model.addAttribute("categories", categories);
        model.addAttribute("productlist", productlist);

        return "redirect:/shop";
    }


    @PostMapping({"/", "/login"})
    public String index(Model model, @RequestParam String username, @RequestParam String password) {
        //Create login
        login = new Login(username, password);
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
        return "redirect:/booking_list";
    }

    @PostMapping(value = "booking", params = "check_activity_btn")
    public String checkactivityPost(Model model) {
        return "redirect:/newActivity";
    }

    @PostMapping(value = "booking", params = "create_activity_btn")
    public String activityPost(Model model) {
        return "redirect:/owner_page";
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


    @PostMapping(value = "/add_activity", params = "create_booking_btn")
    public String activitiesPost(@ModelAttribute Booking booking, @ModelAttribute Activity activity) {
        if(login.getAccessLevel() == 1) {
            return "owner_page";
        }
        //    booking.addActivity(activity);
        return "booking";

    }

    @PostMapping(value = "/add_activity", params = "back_btn")
    public String activitiesPost() {
        //    booking.addActivity(activity);
        return "redirect:/booking";

    }

//
//    @PostMapping(value = "/booking_list", params = "create_booking_btn")
//    public String activitiesPost(@ModelAttribute Booking booking, @ModelAttribute Activity activity) {
//        //    booking.addActivity(activity);
//        return "booking";
//
//    }

    @GetMapping("/booking_list")
    public String bookingList(Model model) {
        Booking booking = new Booking();
        model.addAttribute("Bookings", booking.getBookings());
        return "booking_list";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    /**
     * Redirects the user to login, and resets accessLevel
     *
     * @return String Returns a String that redirects the user to login
     */
    @GetMapping("/logout")
    public String logout() {

        return "redirect:/login";
    }

    @GetMapping("/newActivity")
    public String newActivity(Model model) {
        model.addAttribute("activity", new Activity());
        return "newActivity";
    }

    @PostMapping(value = "/newActivity")
    public String newActivity(@ModelAttribute Activity activity, Model model) {
        Activity.addNewActivity(activity);
        return "redirect:/owner_page";
    }

}
