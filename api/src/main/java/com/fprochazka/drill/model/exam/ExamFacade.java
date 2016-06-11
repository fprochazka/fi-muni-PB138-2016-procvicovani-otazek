package com.fprochazka.drill.model.exam;

import com.fprochazka.drill.model.drill.Drill;
import com.fprochazka.drill.model.drill.DrillNotFoundException;
import com.fprochazka.drill.model.drill.DrillRepository;
import com.fprochazka.drill.model.user.User;
import com.fprochazka.drill.model.authentication.password.UserNotFoundException;
import com.fprochazka.drill.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ExamFacade
{

	private ExamRepository examRepository;
	private DrillRepository drillRepository;
	private UserRepository userRepository;

	@Autowired
	public ExamFacade(ExamRepository examRepository, DrillRepository drillRepository, UserRepository userRepository)
	{
		this.examRepository = examRepository;
		this.drillRepository = drillRepository;
		this.userRepository = userRepository;
	}

	public Exam createExam(UUID drillId, UUID userId) throws ExamNotUniqueException, DrillNotFoundException, UserNotFoundException
	{
		Drill drill = drillRepository.findOne(drillId);
		User user = userRepository.findOne(userId);

		if (drill == null) {
			throw new DrillNotFoundException();
		}
		if (user == null) {
			throw new UserNotFoundException(  );
		}
		Exam userExam = new Exam(drill, user);

		if (examRepository.getExamByDrillAndUser(userExam.getDrill().getId(), userExam.getUser().getId()) != null) {
			throw new ExamNotUniqueException();
		}
		examRepository.save(userExam);

		return userExam;
	}

}
