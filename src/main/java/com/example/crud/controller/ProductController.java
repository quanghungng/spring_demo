package com.example.crud.controller;

import com.example.crud.model.Product;
import com.example.crud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = "/addProduct" ,method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("product") Product product){
        product.setPhoto("default");
        productRepository.save(product);
        return "redirect:/home";
    }
    @RequestMapping(value = "/deleteProduct/{id}",  method = RequestMethod.GET)
    public String deleteProduct(@PathVariable("id") int id){
        productRepository.deleteById(id);
        return "redirect:/home";
    }

    //Navigate to Update product page
    @RequestMapping(value = "/updateProductPage/{id}", method = RequestMethod.GET)
    public String updateProduct(Model model,@PathVariable("id") int id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            model.addAttribute("product",product);
            model.addAttribute("newProduct", new Product()); //Used for storing update info
        }
        return "updateProductPage";
    }

    @RequestMapping(value = "/updateProduct/{id}", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("newProduct") Product newProduct,@PathVariable("id")int id){
        Optional<Product> product = productRepository.findById(id); //Get old product
        if(product.isPresent()){// Update product
            Product oldProduct = product.get();
            oldProduct.setName(newProduct.getName());
            oldProduct.setPrice(newProduct.getPrice());
            oldProduct.setDescription(newProduct.getDescription());
            productRepository.save(oldProduct);
        }
        return "redirect:/home";
    }

}

