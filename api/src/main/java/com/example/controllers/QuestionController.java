package com.example.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by viki on 05.05.16.
 */

@RestController
public class QuestionController {

    @RequestMapping(value="/drill/{drill_id}/question", method = RequestMethod.POST)
    public void createQuestion() {

    }
}
