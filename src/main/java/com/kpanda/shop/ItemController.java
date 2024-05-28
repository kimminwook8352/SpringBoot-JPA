package com.kpanda.shop;

import lombok.RequiredArgsConstructor;
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
    private final ItemService itemService;

    @GetMapping("/list")
    String list(Model model){
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items", result);
        return "list";
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

    @PostMapping("/edit")
    String editItem(Long id ,String title, Integer price){

        Item item = new Item();
        item.setId(id);
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
        return "redirect:/list";
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable Long id, Model model){
        Optional<Item> result = itemRepository.findById(id);
        if(result.isPresent()){
            model.addAttribute("data",result.get());
            return "edit";
        } else {
            return "redirect:/list";
        }
    }

    @GetMapping("/write")
    String write(){
        return "write";
    }

    @PostMapping("/add")
    String addPost(String title, Integer price){
        itemService.saveItem(title, price);
        return "redirect:/list";
    }
    //간단하게 사용할수 있음
//    String writePost(@ModelAttribute Item item){
//        itemRepository.save(item);
//        return "redirect:/list";
//    }

}
