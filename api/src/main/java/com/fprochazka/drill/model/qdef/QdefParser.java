package com.fprochazka.drill.model.qdef;

import com.fprochazka.drill.model.drill.question.Answer;
import com.fprochazka.drill.model.drill.question.Question;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;

@Service
public class QdefParser
{

	public ArrayList<Question> parse(File file) throws IOException
	{
		ArrayList<ArrayList<String>> blocks = readFile(file);
		ArrayList<Question> parsedFile = new ArrayList<>();
		StringBuilder title = new StringBuilder();
		Question question;
		Answer answer;
		ArrayList<Answer> answersList;

		for (ArrayList<String> block : blocks) {
			int countOfCorrect;
			title.setLength(0);
			answersList = new ArrayList<>();
			for (String s : block) {
				if (!s.startsWith(" :") && !s.startsWith(":")) {
					title.append(s);
					title.append("\n");
				}
				if (s.startsWith(" :")) {
					answer = new Answer(false, s.substring(5));
					answersList.add(answer);
				}
				if (s.startsWith(":") && s.contains("ok")) {
					if (s.contains(":r")) {
						answersList.get(Integer.parseInt(s.substring(2, 3)) - 1).setCorrect(true);
					} else {
						countOfCorrect = StringUtils.countOccurrencesOf(s, ":");
						for (int i = 2; i < countOfCorrect * 3; i += 3) {
							answersList.get(Integer.parseInt(s.substring(i, i + 1)) - 1).setCorrect(true);
						}
					}
				}
			}
			question = new Question(title.toString().trim(), answersList, null);
			parsedFile.add(question);
		}
		return parsedFile;
	}

	private ArrayList<ArrayList<String>> readFile(File file) throws IOException
	{
		ArrayList<ArrayList<String>> result = new ArrayList<>();

		String inputLine;
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			ArrayList<String> block = new ArrayList<>();

			while ((inputLine = in.readLine()) != null) {
				if (inputLine.equals("--")) {
					result.add(new ArrayList<>(block));
					block.clear();
					continue;
				}
				block.add(inputLine);
			}
			if (block.size() > 0) {
				result.add(new ArrayList<>(block));
				block.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}
