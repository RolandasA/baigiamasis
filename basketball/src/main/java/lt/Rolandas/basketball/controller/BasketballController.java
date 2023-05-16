package lt.Rolandas.basketball.controller;


import jakarta.validation.Valid;
import lt.Rolandas.basketball.model.User;
import lt.Rolandas.basketball.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;

import static lt.Rolandas.basketball.controller.BasketballGame.opponentScore;
import static lt.Rolandas.basketball.controller.BasketballGame.yourScore;

@Controller
public class BasketballController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/main")
    String getMain() {
        return "main";
    }

    @GetMapping(path = "/game")
    String getGame(Model model) {
        model.addAttribute("yourTotalScore", BasketballGame.yourTotalScore(yourScore));
        model.addAttribute("opponentsTotalScore", BasketballGame.opponentsTotalScore(opponentScore));
        model.addAttribute("yourMessage", BasketballGame.yourMessage);
        model.addAttribute("opponentMessage", BasketballGame.opponentMessage);
        return "game";
    }

    @PostMapping(path = "/shoot-two-points")
    String getTwo(Model model) {
        model.addAttribute("yourTotalScore", BasketballGame.yourTotalScore(yourScore));
        model.addAttribute("opponentsTotalScore", BasketballGame.opponentsTotalScore(opponentScore));
        model.addAttribute("yourTwoPointShot", BasketballGame.yourTwoPointShot(yourScore));
        if (BasketballGame.yourTotalScore(yourScore) >= 21 || BasketballGame.opponentsTotalScore(opponentScore) >= 21) {
            return "redirect:/gameOver";
        }
        return "redirect:/game";
    }

    @PostMapping(path = "/shoot-three-points")
    String getThree(Model model) {
        model.addAttribute("yourTotalScore", BasketballGame.yourTotalScore(yourScore));
        model.addAttribute("opponentsTotalScore", BasketballGame.opponentsTotalScore(opponentScore));
        model.addAttribute("yourThreePointShot", BasketballGame.yourThreePointShot(yourScore));
        if (BasketballGame.yourTotalScore(yourScore) >= 21 || BasketballGame.opponentsTotalScore(opponentScore) >= 21) {
            return "redirect:/gameOver";
        }
        return "redirect:/game";
    }

    @GetMapping(path = "/gameOver")
    String getGameOver(Model model) {
        model.addAttribute("yourTotalScore", BasketballGame.yourTotalScore(yourScore));
        model.addAttribute("opponentsTotalScore", BasketballGame.opponentsTotalScore(opponentScore));
        model.addAttribute("user", new User());
        return "gameOver";
    }

    @PostMapping(path = "/finalPage")
    String getFinal(@Valid User user, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            return "gameOver";
        }
        User newUser = userService.createOrUpdateUser(user);
        model.addAttribute("user", newUser);
        return "finalPage";
    }

    @PostMapping("/reset")
    public String resetGame() {
        BasketballGame.yourScore.clear();
        BasketballGame.opponentScore.clear();
        return "redirect:/main";
    }

    @GetMapping(path = "/rules")
    String getRules(Model model) {
        return "rules";
    }

    @GetMapping(path = "/results")
    String getResults(Model model, @RequestParam(name = "pageId", defaultValue = "0") int page) {
        if (page < 0) return "error";
        Page<User> users = userService.getAllUsers(page);
        int totalPages = users.getTotalPages();

        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "users";
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver clr = new CookieLocaleResolver();
        clr.setDefaultLocale(Locale.US);
        return clr;
    }
}
