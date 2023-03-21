package ieco.internal.middleware.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DynamicNudgesController {

    @GetMapping(value = "/dynamicNudges")
    public String dynamicNudges(){
        return "dynamicNudges";
    }
}
