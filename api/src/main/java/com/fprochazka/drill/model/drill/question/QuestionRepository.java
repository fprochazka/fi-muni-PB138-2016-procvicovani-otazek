package com.fprochazka.drill.model.drill.question;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionRepository extends CrudRepository<Question, UUID>
{

	Question getQuestionById(UUID questionId);

}
