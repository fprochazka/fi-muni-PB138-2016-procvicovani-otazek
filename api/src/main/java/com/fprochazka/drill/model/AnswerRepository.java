package com.fprochazka.drill.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Michaela Bamburová on 15.05.2016.
 */
@Repository
public interface AnswerRepository extends MongoRepository<Answer, String>
{

	/*
	---QuestionRepository---

	//@Query("{'name' : ?0}")

	public List<Question> getAllQuestionsInDrill()
	public void createQuestion(Question question) {
	public Question getQuestion()
	public void updateQuestion(Question question)
	 */

//	Answer findByText(String text);
	//List<Answer> findByTexts(String text);


}
