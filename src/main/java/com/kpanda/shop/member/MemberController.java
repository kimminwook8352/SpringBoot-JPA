package com.kpanda.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    String register(){
        return "register";
    }

    @PostMapping("/member")
    String addMember(
            String username,
            String password,
            String displayName
    ){
        Member member = new Member();
        member.setUsername(username);
        //암호 해싱
//        var hash = new BCryptPasswordEncoder().encode(password);
        var hash = passwordEncoder.encode(password);
        member.setPassword(hash);
        member.setDisplayName(displayName);
        memberRepository.save(member);
        return "redirect:/list";
    }

    @GetMapping("/login")
    public String login(){
        var result = memberRepository.findByUsername("kim");
        System.out.println(result.get().getDisplayName());
        return "login.html";
    }


    @GetMapping("/logout")
    public String logout(){
        return "logout.html";
    }

    @GetMapping("/myPage")
    public String myPage(Authentication auth){
        CustomUser result = (CustomUser) auth.getPrincipal();
        return "mypage.html";
    }

    @GetMapping("/user/1")
    @ResponseBody
    public MemberDto user(){
        var a = memberRepository.findById(4L);
        var result = a.get();
        var data = new MemberDto(result.getUsername(), result.getDisplayName(), result.getId());
        return data ;
    }

}

class MemberDto{
    public String username;
    public String displayName;
    public Long id;
    MemberDto(String a, String b, Long c){
        this.username = a;
        this.displayName = b;
        this.id = c;
    }
}

