package com.fprochazka.drill.api.exam;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
public class ExamController {

    /**
     * Function creates new exam for given user and drill. If exam already exists, does nothing.
     *
     * @param drillId - ID of drill we want to create exam for
     */
    @RequestMapping(value = "/user/{userId}/exam", method = RequestMethod.POST)
    public void createExam(@RequestBody UUID drillId) {

    }

    /**
     * Function finds in DB all exams of the user and returns them. If there are no exams, returns empty list.
     *
     * @return exams - ArrayList of all exams of given user.
     */
    @RequestMapping(value = "/user/{userId}/exam", method = RequestMethod.GET)
    public @ResponseBody Collection<Object> getAllExams() {
        return null;
    }

    /**
     * Function finds in DB exam object corresponding to given ID. If doesn't exist, return null.
     *
     * @return exam - of type Exam, identified by examId
     */
    @RequestMapping(value = "/user/{userId}/exam/{examId}", method = RequestMethod.GET)
    public @ResponseBody Object getExam() {
        return null;
    }

    /**
     * Function find exam by given ID and updates its statistics.
     *
     * @param answers - list of JSON objects, saying wheter user's answer was correct
     */
    @RequestMapping(value = "/user/{userId}/exam/{examId}", method = RequestMethod.PUT)
    public void updateExam(@RequestBody Collection<Object> answers) {
        // Find exam in database, then for each answer in list add counter whether the answer was correct or not
    }
}
