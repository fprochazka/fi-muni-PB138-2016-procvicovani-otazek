package com.fprochazka.drill.api.question;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
public class QuestionController {

    /**
     * Function returns all questions in database belonging to given drill.
     *
	 * @param drillId - ID of the drill we want to return all questions for
     * @return ArrayList of type Question, all questions of the drill in database
     */
    @RequestMapping(value = "/drill/{drillId}/question", method = RequestMethod.GET, headers = "content-type=applicaiont/json")
    public @ResponseBody List<QuestionResponse> getAllQuestionsInDrill(@PathVariable UUID drillId) {
        List<QuestionResponse> questions = new ArrayList<>();
        // get all questions of the drill with given ID
        return questions;
    }

    /**
     * Function gets model of question (required parameters: text, answers, for each answer sign, whether it's correct). Then new ID is generated (on which level?) and new model is saved to DB.
     *
	 * @param drillId - ID of the drill we want to add question to
     * @param question - JSON object parsed to suitable Java object, preferably Question
     */
    @RequestMapping(value = "/drill/{drillId}/question", method = RequestMethod.POST)
    public void createQuestion(@PathVariable UUID drillId, @RequestBody QuestionRequest question) {
        // parse Model to Question, then save it to databasereturn question;
    }

    /**
     * Function finds question in database with given ID and returns it
     *
	 * @param drillId  - ID of the drill we want to find question in
	 * @param questionId - ID of the question we want find
     * @return question with given ID
     */
    @RequestMapping(value = "/drill/{drillId}/question/{questionId}", method = RequestMethod.GET)
    public @ResponseBody QuestionResponse getQuestion(@PathVariable UUID drillId, @PathVariable UUID questionId) {
        // find question in database by given ID and return it
        return null;
    }

    /**
     * Function updates identified question with new given question
     *
	 * @param drillId - ID of the drill we want to update question in
	 * @param questionId - ID of the question we want to update
     * @param question - updated question
     */
    @RequestMapping(value = "/drill/{drillId}/question/{questionId}", method = RequestMethod.PUT)
    public @ResponseBody QuestionRequest updateQuestion(@PathVariable UUID drillId, @PathVariable UUID questionId, @RequestBody QuestionRequest question) {
        // find question with given ID and update it
        return question;
    }
}
