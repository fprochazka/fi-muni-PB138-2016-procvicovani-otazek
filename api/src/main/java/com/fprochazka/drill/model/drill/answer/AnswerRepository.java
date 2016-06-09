package com.fprochazka.drill.model.drill.answer;

import com.fprochazka.drill.model.drill.question.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, UUID>
{

	Answer getAnswerByQuestion(Question question);
}
