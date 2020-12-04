package org.lowentropy.secretgame.booter.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UiController {

    @RequestMapping(value = { "/", "/create", "/join", "/room/*" })
    public String index() {
        return "index.html";
    }
}
