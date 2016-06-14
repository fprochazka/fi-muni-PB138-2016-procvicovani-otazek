package com.fprochazka.drill.model.json;

import com.fprochazka.drill.model.drill.Drill;
import com.fprochazka.drill.model.drill.question.Answer;
import com.fprochazka.drill.model.drill.question.Question;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class JSONQuestionParser
{
	public List<Question> parse(Drill drill, File file) throws FileNotFoundException, ParseException
	{
		JSONParser parser = new JSONParser(JSONParser.MODE_PERMISSIVE);
		JSONArray a = (JSONArray) parser.parse(new FileReader(file));
		List<Question> questions = new ArrayList<>();

		for (Object o : a) {
			JSONObject question = (JSONObject) o;
			String name = (String) question.get("name");
			ArrayList<HashMap<String, Object>> answersMap = (ArrayList) question.get("answers");
			ArrayList<Answer> answers = new ArrayList<>();
			for (HashMap answerMap : answersMap) {
				String body = (String) answerMap.get("body");
				Boolean right = (Boolean) answerMap.get("right");
				answers.add(new Answer(right, body));
			}
			questions.add(new Question(name, answers, drill));
		}

		return questions;
	}
}
