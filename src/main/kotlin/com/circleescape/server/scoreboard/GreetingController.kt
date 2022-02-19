package com.circleescape.server.scoreboard

import com.circleescape.server.scoreboard.data.ScoreRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class GreetingController {
    @Autowired
    var scoreRepository: ScoreRepository? = null
    @GetMapping("/greeting")
    fun greeting(
        @RequestParam(name = "name", required = false, defaultValue = "World") name: String?,
        model: Model
    ): String {
        model.addAttribute("name", name)
        return "greeting"
    }
}