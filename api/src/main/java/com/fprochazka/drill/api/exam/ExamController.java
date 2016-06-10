package com.fprochazka.drill.api.exam;

import com.fprochazka.drill.model.exam.Exam;
import com.fprochazka.drill.model.exam.ExamFacade;
import com.fprochazka.drill.model.exam.ExamRepository;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class ExamController {

	private ExamFactory examFactory;
	private ExamFacade examFacade;
	private ExamRepository examRepository;

	@Autowired
	public ExamController(
		ExamFactory examFactory,
		ExamFacade examFacade,
		ExamRepository examRepository) {
		this.examFactory = examFactory;
		this.examFacade = examFacade;
		this.examRepository = examRepository;
	}

	/**
     * Function creates new exam for given user and drill. If exam already exists, does nothing.
     *
     * @param request - ID of drill we want to create exam for
     */
    @RequestMapping(value = "/user/{userId}/exam", method = RequestMethod.POST)
    public void createExam(@PathVariable("userId") UUID userId, @RequestBody CreateExamRequest request ) {
		examFacade.createExam( request.getDrillId(), userId );
    }

    /**
     * Find all exams in database of given user.
     *
     * @return response objects for all found exams
     */
    @RequestMapping( value = "/user/{userId}/exam", method = RequestMethod.GET )
    public @ResponseBody Collection<ExamResponse> getAllExams( @PathVariable UUID userId ) {
        return null;
    }

    /**
     * Find exam with given ID in database, return it's response object.
     *
     * @return response object for found exam
     */
    @RequestMapping(value = "/user/{userId}/exam/{examId}", method = RequestMethod.GET)
    public @ResponseBody ExamResponse getExam(
		@PathVariable UUID examId) {
        Exam exam = examRepository.getExamById( examId );
		return examFactory.createExamResponse( exam );
    }

    /**
     * Update questions of given exam with given answers.
     *
     * @param answers - list of user's answers to questions of exam
     */
    @RequestMapping(value = "/user/{userId}/exam/{examId}", method = RequestMethod.PUT)
    public void updateExam(
		@PathVariable UUID userId,
		@PathVariable UUID examId,
		@RequestBody Collection<UpdateExamRequest> answers) {

		Map< UUID, Integer > correct = new HashMap<>();
		Map< UUID, Integer > wrong = new HashMap<>();

		for ( UpdateExamRequest request: answers ) {
			if ( request.getCorrect() ) {
				int count = correct.containsKey( request.getQuestionId() ) ?
					correct.get( request.getQuestionId() ) : 0;
				correct.put( request.getQuestionId(), count + 1 );
			}
			else {
				int count = wrong.containsKey( request.getQuestionId() ) ?
					wrong.get( request.getQuestionId() ) : 0;
				wrong.put( request.getQuestionId(), count + 1 );
			}
		}

		for ( Map.Entry< UUID, Integer > request: correct.entrySet() ) {
			// examQuestionFacade.updateExamIncreaseCorrect( examId, request.getKey(), request.getValue() );
		}

		for ( Map.Entry< UUID, Integer > request: wrong.entrySet() ) {
			// examQuestionFacade.updateExamIncreaseWrong( examId, request.getKey(), request.getValue() );
		}
    }
}
