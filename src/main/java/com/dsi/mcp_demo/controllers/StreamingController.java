package com.dsi.mcp_demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class StreamingController {

    @GetMapping("/streaming")
    public String streaming() {
        return "streaming"; // Will load src/main/resources/templates/streaming.html
    }

}