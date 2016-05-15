package com.fprochazka.drill.model;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by Michaela Bamburová on 15.05.2016.
 */
public interface ExamRepository extends CrudRepository<StudentExam, UUID> {

	/*
	public void createExam(UUID drillId)
	public List<StudentExam> getAllExams()
	public StudentExam getExam()
	public void updateExam(List<StudentExam> answers)
	 */
}
