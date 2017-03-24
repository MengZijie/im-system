package com.mzj.im.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ob on 17-3-16.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String getHomePage() {
        return "home";
    }
}
