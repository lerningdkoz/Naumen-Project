package com.example.securingweb.Controllers;


import com.example.securingweb.Entity.User;
import com.example.securingweb.Repository.FeedbackRepository;
import com.example.securingweb.Repository.ReviewRepository;
import com.example.securingweb.Repository.UserRepository;
import com.example.securingweb.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ReviewRepository reviewRepository;

    @Autowired
    private final FeedbackRepository feedbackRepository;

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", userService.list());
        return "admin1";
    }

    @PostMapping("/admin/user/ban")
    public String userBan(@RequestParam("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/user/accept")
    public String userAccept(@RequestParam("id") Long id) {
        userService.acceptUser(id);
        return "redirect:/admin";
    }
    @PostMapping("admin/user/info")
    public String userInfo(@RequestParam("id") Long id, Model model){
        User user = userRepository.findById(id).orElse(null);
        if (user!=null){
            model.addAttribute("user", user);
            model.addAttribute("role", user.getRoles().stream().toList().get(0).toString());
        var reviews = reviewRepository.findByAuthor_Id(id);
        var feedbacksUsers = feedbackRepository.findByUser_Id(id);
        if (reviews!=null){
            model.addAttribute("reviews", reviews);
        }
        if (feedbacksUsers!=null){
            model.addAttribute("feedbacks", feedbacksUsers);
        }
        }
        return "info";
    }

    @PostMapping("/admin/user/verif")
    public String userVerif(@RequestParam("id") Long id) {
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        user.setVerification(true);
        userRepository.save(user);
        return "redirect:/admin";
    }
}
