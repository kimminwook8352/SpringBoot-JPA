package com.kpanda.shop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BasicController {

    private final ItemRepository itemRepository;

    @GetMapping("/")
    String index(Model model){
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items", result);
        return "list";
    }

    @GetMapping("/about")
    @ResponseBody
    String about(){
        return "피싱사이트";
    }

    @GetMapping("/mypage")
    @ResponseBody
    String myPage(){
        return "내정보";
    }

    @GetMapping("/date")
    @ResponseBody
    String date(){
//        return LocalDateTime.now().toString();
//         return ZonedDateTime.now().toString();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatterDateTime = now.format(formatter);
        return (" 오늘날짜/시간 : " + formatterDateTime);
    }
}
