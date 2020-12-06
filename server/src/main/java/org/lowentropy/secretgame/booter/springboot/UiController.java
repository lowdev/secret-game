package org.lowentropy.secretgame.booter.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UiController {

    @RequestMapping(value = { "/", "/create", "/join" })
    public String index() {
        return "index.html";
    }

    @RequestMapping(value = { "/room/**" })
    public String room() {
        return "/";
    }
}
