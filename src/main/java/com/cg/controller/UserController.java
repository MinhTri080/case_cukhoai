package com.cg.controller;

import com.cg.model.Customer;
import com.cg.model.User;
import com.cg.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
//    @GetMapping
//    public ModelAndView showListUser() {
//        return new ModelAndView("/customer/list");
//    }

    @Autowired
    private IUserService userService;

    @GetMapping
    public ModelAndView showListPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/list");
        return modelAndView;
    }

//    @GetMapping("/search")
//    public ModelAndView showSearchResultPage(@RequestParam String search) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("customer/list");
//
//        search = "%" + search + "%";
//
////        List<Customer> customerList = customerService.findByFullNameLike(search);
//
////        List<Customer> customerList = customerService.findByFullNameLikeOrEmailLikeOrPhoneLikeOrAddressLike(search, search, search, search);
//
//        List<Customer> customerList = customerService.findAllBySearchKey(search);
//
//        modelAndView.addObject("customers", customerList);
//
//
//        return modelAndView;
//    }


    @GetMapping("/create")
    public ModelAndView showCreatePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/modal_createCus");

        modelAndView.addObject("user", new User());

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditPage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/modal_updateCus");

        Optional<User> user = userService.findById(id);

        if (user.isPresent()) {
            modelAndView.addObject("user", user);
        }
        else {
            modelAndView.addObject("user", new User());
        }

        return modelAndView;
    }


    @PostMapping("/create")
    public ModelAndView doCreate(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/modal_createCus");

        try {
            userService.save(user);

            modelAndView.addObject("success", "New user add success");
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.addObject("error", "Bad data");
        }

        modelAndView.addObject("customer", new Customer());

        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView doUpdate(@PathVariable Long id, @ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/modal_updateCus");

        try {
            user.setId(id);
            userService.save(user);

            modelAndView.addObject("user", user);
            modelAndView.addObject("success", "Update user success");
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.addObject("error", "Bad data");
            modelAndView.addObject("customer", new Customer());
        }

        return modelAndView;
    }
}

