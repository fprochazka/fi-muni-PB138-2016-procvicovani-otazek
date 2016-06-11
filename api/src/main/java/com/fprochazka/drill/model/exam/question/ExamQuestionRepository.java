package com.fprochazka.drill.model.exam.question;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExamQuestionRepository extends CrudRepository<ExamQuestion, UUID>
{

	List<ExamQuestion> getExamQuestionsByExam(UUID examId);

	ExamQuestion getExamQuestionByQuestionAndExam(UUID questionId, UUID examId);
}
