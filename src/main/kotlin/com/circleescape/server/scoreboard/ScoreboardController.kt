package com.circleescape.server.scoreboard

import com.circleescape.server.scoreboard.data.ScoreRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ScoreboardController {
    @Autowired
    var scoreRepository: ScoreRepository? = null

    @GetMapping("/highscore")
    fun greeting(model: Model): String {
        model.addAttribute("scoreTests", scoreRepository!!.findAll())
        return "highscore"
    }
}