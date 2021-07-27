package com.example.crud.controller;
import com.example.crud.model.Product;
import com.example.crud.model.User;
import com.example.crud.repository.ProductRepository;
import com.example.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    ProductRepository productRepository;
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(Model model){
        List<Product> listProduct = productRepository.findAll();
        model.addAttribute("listProduct",listProduct);
        model.addAttribute("product",new Product()); // Used for storing new product's info
        return "home";
    }

}
