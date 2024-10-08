package com.cho.system.infra.web.presentaion;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(Model model,  @AuthenticationPrincipal OAuth2User oauth2User) {
        if (oauth2User != null) {
            String userName = oauth2User.getAttribute("name");
            Collection<? extends GrantedAuthority> authorities = oauth2User.getAuthorities();
            model.addAttribute("userName", userName);
            model.addAttribute("userRole",authorities);
        }
        return "index";
    }
}