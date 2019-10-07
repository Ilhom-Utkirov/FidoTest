package com.fido2.demo.controller;

import com.fido2.demo.entity.Goods;
import com.fido2.demo.entity.User;
import com.fido2.demo.repository.GoodsRepository;
import com.fido2.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping
public class ApiController {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final GoodsRepository goodsRepository;

    public ApiController(UserRepository userRepository, GoodsRepository goodsRepository) {
        this.userRepository = userRepository;
        this.goodsRepository = goodsRepository;
    }


    @GetMapping("/")
    public String showFirstPage(){
        return "index";
    }

    @GetMapping("/userlist")
    public String showUserList(Model model){
        model.addAttribute("users",userRepository.findAll());
        return "user-list";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {


        model.addAttribute(new User());
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        //user.setCredit(Integer.parseInt(String.valueOf(user.getCredit())));
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "user-list";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
       // user.setCredit((double)user.getCredit());
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }

        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/userlist";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/userlist";
    }

    /*
    =========================
    *  //Maps for goods//
    =========================
    */
    //For paging purposes i used page request as it is deprecated
    //then i use method PageRequest.of from youtube comments
    @GetMapping("/index2")
    public String showGoodsPage(Model model, @RequestParam(defaultValue = "0") int page){
        model.addAttribute("data",goodsRepository.findAll( PageRequest.of(page,4)));
        model.addAttribute("currentPage", page);

        return "index2";
    }

    //needed get
    @PostMapping("/saveGoods")
    public String addGoods(Goods goods){
        goodsRepository.save(goods);
        return "redirect:/index2";
    }

    //needed get
    @GetMapping("/deleteGoods/{id}")
    public String deleteGoods(@PathVariable Long id, Model model){
        goodsRepository.deleteById(id);
        model.addAttribute("data",goodsRepository.findAll());

        return "redirect:/index2";
    }

    //needed get
    @GetMapping("/findOne/{id}")
    @ResponseBody
    public  Goods findOne(@PathVariable Long id){
        //Goods goods = goodsRepository.findById(id)
        return goodsRepository.findById(id).get();
    }





}
