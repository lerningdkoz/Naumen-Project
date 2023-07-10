package com.example.securingweb.Controllers;

import com.example.securingweb.Entity.Feedback;
import com.example.securingweb.Entity.Review;
import com.example.securingweb.Repository.FeedbackRepository;
import com.example.securingweb.Repository.ReviewRepository;
import com.example.securingweb.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class ReviewConfig {

    @Autowired
    public UserService userService;

    @Autowired
    public ReviewRepository reviewRepository;

    @Autowired
    public FeedbackRepository feedbackRepository;

    @GetMapping("/myReview")
    public String mainPage(@RequestParam(name = "id") Long id, Principal principal, Model model){
        Review review = reviewRepository.findById(id).orElse(null);
        List<Feedback> feedbackList = feedbackRepository.findByReview(review);
        model.addAttribute("principal",userService.getUserByPrincipal(principal));
        model.addAttribute("review", review);
        model.addAttribute("listFeedback", feedbackList);
        int sumRait = 0;
        if (feedbackList.size()!=0){
            for (var el : feedbackList){
                sumRait+=el.getRait();
            }
            model.addAttribute("aveRait", (int)Math.floor(sumRait/feedbackList.size()));
        }
        else {
            model.addAttribute("aveRait", 0);
        }
        return "review";
    }

    @GetMapping("/newFeedback")
    public String aboutMe(@RequestParam("id") Long id, Principal principal, Model model){
        model.addAttribute("principal",userService.getUserByPrincipal(principal));
        model.addAttribute("review", reviewRepository.findById(id).orElse(null));

        return "new_review";
    }
    @GetMapping("/reviews")
    public String getReviews(Principal principal, Model model){
        List<Review> list = (List<Review>) reviewRepository.findAll();
        model.addAttribute("principal",userService.getUserByPrincipal(principal));
        if (list.size()!=0)
            model.addAttribute("listReview", list);
        return "test_review";
    }

    @PostMapping("/addFeedback")
    public String addReview(@RequestParam("review_type") String type,
                            @RequestParam("organization_city") String city,
                            @RequestParam("organization_address") String address,
                            @RequestParam("review_rank") String rank,
                            @RequestParam("myText") String text,
                            @RequestParam("review_id") String id,
                            Principal principal, Model model){
        Feedback feedback = new Feedback();
        feedback.setReview(reviewRepository.findById(Long.valueOf(id)).orElse(null));
        feedback.setCity(city);
        feedback.setRait(Integer.parseInt(rank));
        feedback.setUser(userService.getUserByPrincipal(principal));
        feedback.setAddress(address);
        feedback.setText(text);
        feedback.setType(type);
        feedback.inti();
        feedbackRepository.save(feedback);
        model.addAttribute("success", "Отзыв добавлен!");
        return "mainPage";
    }

    @GetMapping("/newService")
    public String newService(Principal principal, Model model){
        model.addAttribute("principal",userService.getUserByPrincipal(principal));
        return "new_service";
    }

    @PostMapping("/newService")
    public String addNewService(@RequestParam("service_name") String name,
                                @RequestParam("service_org") String nameOrg,
                                @RequestParam("service_address") String address,
                                @RequestParam("description") String description,
                                Principal principal, Model model){
        model.addAttribute("principal",userService.getUserByPrincipal(principal));
        System.out.println(name+nameOrg+address+description);
        Review review = new Review();
        review.setAddress(address);
        review.setAuthor(userService.getUserByPrincipal(principal));
        review.setDescription(description);
        review.setName(name);
        review.setNameOrg(nameOrg);
        reviewRepository.save(review);
        model.addAttribute("success", "Услуга добавлена!");
        return "mainPage";
    }

    @PostMapping("/myReview")
    public String mainpPage(@RequestParam(name = "id") Long id, Principal principal, Model model){
        Review review = reviewRepository.findById(id).orElse(null);
        List<Feedback> feedbackList = feedbackRepository.findByReview(review);
        model.addAttribute("principal",userService.getUserByPrincipal(principal));
        model.addAttribute("review", review);
        model.addAttribute("listFeedback", feedbackList);
        int sumRait = 0;
        if (feedbackList.size()!=0){
            for (var el : feedbackList){
                sumRait+=el.getRait();
            }
            model.addAttribute("aveRait", (int)Math.floor(sumRait/feedbackList.size()));
        }
        else {
            model.addAttribute("aveRait", 0);
        }
        return "review";
    }

}
