package converter;

import java.util.List;

/**
 * Created by Michaela Bamburová on 07.05.2016.
 */
public interface QuestionManager {

	void createQuestion(Question question);

	void updateQuestion(Question question);

	void deleteQuestion(Question question);

	Question findQuestionById(Long id);

	List<Question> findAllQuestions();

	//...etc
}
