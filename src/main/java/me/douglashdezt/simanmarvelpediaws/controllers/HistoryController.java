package me.douglashdezt.simanmarvelpediaws.controllers;

import me.douglashdezt.simanmarvelpediaws.dtos.GeneralResponse;
import me.douglashdezt.simanmarvelpediaws.models.User;
import me.douglashdezt.simanmarvelpediaws.services.HistoryService;
import me.douglashdezt.simanmarvelpediaws.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/search-history")
@CrossOrigin("*")
public class HistoryController {
    private final HistoryService historyService;
    private final UserService userService;

    public HistoryController(HistoryService historyService, UserService userService) {
        this.historyService = historyService;
        this.userService = userService;
    }

    @GetMapping("/by-user")
    public ResponseEntity<GeneralResponse> findAllByUser(@RequestParam(name = "user", defaultValue = "") String info) {
        User user = userService.findUserByEmail(info);

        if (user == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "User not found");
        }

        return GeneralResponse.getResponse("History found", historyService.findAllByUser(user));
    }

    @GetMapping("/own")
    public ResponseEntity<GeneralResponse> findAllOwn() {
        User user = userService.findAuthenticated();

        if (user == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "User not found");
        }

        return GeneralResponse.getResponse("History found", historyService.findAllByUser(user));
    }

    @GetMapping("/by-model/{model}")
    public ResponseEntity<GeneralResponse> findAllByModel(@PathVariable String model) {
        return GeneralResponse.getResponse("History found", historyService.findAllByModel(model));
    }
}
