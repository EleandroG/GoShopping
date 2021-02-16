package com.goshopping.site.admin.customer;

import com.goshopping.common.entity.Customer;
import com.goshopping.site.admin.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/customers") //url para listar os customers com a pag HTML5
    public String listAll(Model model) {
        List<Customer> listCustomers = service.listAll();
        model.addAttribute("listCustomers", listCustomers);
        return "customers"; //ficheiro que devolve
    }

    @GetMapping("/customers/new") //url que devolve a pag HTML5
    public String newCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        model.addAttribute("pageTitle", "Create New Customer");

        return "customer_form"; //ficheiro que devolve
    }

    @PostMapping("/customers/save")
    public String saveCustomer(Customer customer, RedirectAttributes redirectAttributes) {
        System.out.print(customer);
        service.save(customer);

        redirectAttributes.addFlashAttribute("message", "The customer has been saved succesfully!");
        return "redirect:/customers";
    }

    @GetMapping("/customers/edit/{id}")
    public String editCustomer(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Customer customer = service.get(id);
            model.addAttribute("customer", customer);

            model.addAttribute("pageTitle", "Edit Customer (ID: " + id + ")");
            return "customer_form";
        } catch (UserNotFoundException exception) {
            redirectAttributes.addFlashAttribute("message", exception.getMessage());
            return "redirect:/customers";
        }
    }

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("message", "The customer ID " + id + " has been deleted succesfully!");
        } catch (UserNotFoundException exception) {
            redirectAttributes.addFlashAttribute("message", exception.getMessage());
        }
        return "redirect:/customers";
    }
}
