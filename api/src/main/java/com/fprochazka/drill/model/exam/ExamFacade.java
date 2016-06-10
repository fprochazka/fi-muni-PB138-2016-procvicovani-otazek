package com.fprochazka.drill.model.exam;

import com.fprochazka.drill.model.drill.Drill;
import com.fprochazka.drill.model.drill.DrillRepository;
import com.fprochazka.drill.model.exceptions.NotFoundException;
import com.fprochazka.drill.model.exceptions.NotUniqueException;
import com.fprochazka.drill.model.student.Student;
import com.fprochazka.drill.model.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Exam createExam(UUID drillId, UUID studentId) throws NotUniqueException, NotFoundException
	{
		Drill drill = drillRepository.findOne(drillId);
		Student student = studentRepository.findOne(studentId);

		if (drill == null) {
			throw new NotFoundException();
		}
		if (student == null) {
			throw new NotFoundException();
		}
		Exam studentExam = new Exam(drill, student);

		if (examRepository.getExamByDrillAndStudent(studentExam.getDrill().getId(), studentExam.getStudent().getId()) != null) {
			throw new NotUniqueException();
		}
		examRepository.save(studentExam);

		return studentExam;
	}

}
