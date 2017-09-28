package dev.roysez.financemanager.controller;

import dev.roysez.financemanager.model.Category;
import dev.roysez.financemanager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postCategory(Category category, Model model) {
        try {
            if(category.getTax()< 0 || category.getTax()>100)
                throw new IllegalArgumentException("Tax should be [0:100]");

            category.setId(categoryService.findAll().stream()
                    .reduce((first, second) -> second)
                    .orElseThrow(ArrayIndexOutOfBoundsException::new)
                    .getId() + 1);

            categoryService.save(category);
        } catch (Exception e){
            model.addAttribute("error","Something wrong with adding a new category, check inputs\n" + e.getMessage());
        }
        return "redirect:/transactions";
    }
}
