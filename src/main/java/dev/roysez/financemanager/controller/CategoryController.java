package dev.roysez.financemanager.controller;

import dev.roysez.financemanager.model.Category;
import dev.roysez.financemanager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

@Controller
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postCategory(Category category, RedirectAttributes redir) {

        try {
            if(category.getTax()< 0 || category.getTax()>100)
                throw new IllegalArgumentException("Tax should be [0:100]");

            Set<Category> categories = categoryService.findAll();

            category.setId(categories.stream()
                    .reduce((first, second) -> second)
                    .orElseThrow(ArrayIndexOutOfBoundsException::new)
                    .getId() + 1);

            if(!categoryService.save(category))
                throw new IllegalArgumentException("Category already exist");

        } catch (Exception e){
            redir.addFlashAttribute("error","Something wrong while adding a new category: \n" + e.getMessage());
        }
        return "redirect:/transactions";
    }
}
