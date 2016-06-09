package com.fprochazka.drill.model.exam;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExamRepository extends CrudRepository<Exam, UUID>
{

	Exam getExamById(UUID examId);

}
