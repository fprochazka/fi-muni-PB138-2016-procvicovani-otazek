package com.fprochazka.drill.api.exam;

import java.util.UUID;

/**
 * Created by viki on 09.06.16.
 */
public class ExamResponse {

	private UUID drillId;
	private UUID studentId;

	public ExamResponse(UUID drillId, UUID studentId) {
		this.drillId = drillId;
		this.studentId = studentId;
	}

	public ExamResponse() {
	}

	public UUID getDrillId() {
		return drillId;
	}

	public UUID getStudentId() {
		return studentId;
	}
}
