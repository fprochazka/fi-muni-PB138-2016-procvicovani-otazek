package com.fprochazka.drill.model.exam;

import com.fprochazka.drill.model.drill.Drill;
import com.fprochazka.drill.model.drill.DrillRepository;
import com.fprochazka.drill.model.student.Student;
import com.fprochazka.drill.model.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExamFacade
{

	private ExamRepository examRepository;
	private DrillRepository drillRepository;
	private StudentRepository studentRepository;

	@Autowired
	public ExamFacade(ExamRepository examRepository, DrillRepository drillRepository, StudentRepository studentRepository)
	{
		this.examRepository = examRepository;
		this.drillRepository = drillRepository;
		this.studentRepository = studentRepository;
	}

	public Exam createExam(UUID drillId, UUID studentId)
	{
		Drill drill = drillRepository.findOne(drillId);
		Student student = studentRepository.findOne(studentId);
		Exam studentExam = new Exam(drill, student);
		examRepository.save(studentExam);
		return studentExam;
	}

	public List<Exam> getExamsByStudent(UUID studentId) {
		List<Exam> exams = examRepository.getExamsByStudent(studentId);
		return exams;
	}

}
