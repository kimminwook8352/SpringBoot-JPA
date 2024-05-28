package com.kpanda.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController
{
    private final ItemRepository itemRepository;

    @GetMapping("/list")
    String list(Model model){
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items", result);
        return "list.html";
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id,Model model){
        Optional<Item> result = itemRepository.findById(id);
        if(result.isPresent()){
            model.addAttribute("item",result.get());
            System.out.println(result.get());
            return "detail.html";
        }else {
            return "redirect:/list";
        }
    }


    @GetMapping("/write")
    String write(){
        return "write.html";
    }

    @PostMapping("/add")
    String writePost(@ModelAttribute Item item){
        itemRepository.save(item);
        return "redirect:/list";
    }

}
