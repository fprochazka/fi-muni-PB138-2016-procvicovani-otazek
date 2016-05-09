package com.fprochazka.drill.api.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by viki on 05.05.16.
 */

@RestController
public class QuestionController {

    /**
     * Function returns all questions in database belonging to given drill.
     *
     * @return ArrayList of type Question, all question of the drill in database
     */
    @RequestMapping(value = "/drill/{drillId}/question", method = RequestMethod.GET)
    public Collection<Object> getAllQuestionsInDrill() {
        List<Object> questions = new ArrayList<>();
        // get all questions of the drill with given ID
        return questions;
    }

    /**
     * Function gets model of question (required parameters: text, answers, for each answer sign, whether it's correct). Then new ID is generated (on which level?) and new model is saved to DB.
     *
     * @param question - JSON object parsed to suitable Java object, preferably Question
     */
    @RequestMapping(value = "/drill/{drillId}/question", method = RequestMethod.POST)
    public void createQuestion(Model question) {
        // parse Model to Question, then save it to database
    }

    /**
     * Function finds question in database with given ID and returns it
     *
     * @return model of question with given ID
     */
    @RequestMapping(value = "/drill/{drillId}/question/{questionId}", method = RequestMethod.GET)
    public Model getQuestion() {
        // find question in database by given ID and return it
        return null;
    }

    /**
     * Function updates identified question according to given model
     *
     * @param question - JSON object parsed to suitable Java object, preferably Question
     */
    @RequestMapping(value = "/drill/{drillId}/question/{questionId}", method = RequestMethod.PUT)
    public void updateQuestion(Model question) {
        // find question with given ID and update it
    }
}
