package com.circleescape.server.scoreboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Model model) {
        model.addAttribute("name", name);

        Set<Score> scores = Set.of(
                new Score("alice", 0.6f),
                new Score("bob", 0.9f));

        model.addAttribute("scoreTests", scores);

        return "greeting";
    }

}