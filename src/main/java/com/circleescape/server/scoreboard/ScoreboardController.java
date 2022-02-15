package com.circleescape.server.scoreboard;

import com.circleescape.server.scoreboard.data.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class ScoreboardController {

    @Autowired
    ScoreRepository scoreRepository;

    @GetMapping("/highscore")
    public String greeting(Model model) {

        model.addAttribute("scoreTests", scoreRepository.findAll());

        return "highscore";
    }

}