package com.fprochazka.drill.model.exam;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentExamRepository extends CrudRepository<StudentExam, UUID>
{

	StudentExam getStudentExamById(UUID examId);

}
