package com.fprochazka.drill.model.qdef;

import com.fprochazka.drill.model.drill.question.Answer;
import com.fprochazka.drill.model.drill.question.Question;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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
		ArrayList<String> block = new ArrayList<>();
		BufferedReader in = null;

		try {
			in = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Scanner scanner = new Scanner(file, "UTF-8");
		String lastLine = null;
		while (scanner.hasNextLine()) {
			lastLine = scanner.nextLine();
		}

		String inputLine;
		try {
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.equals("--") || inputLine.equals(lastLine)) {
					result.add(new ArrayList<>(block));
					block.clear();
					continue;
				}
				block.add(inputLine);
			}
			if (in.readLine() == null) {
				result.add(new ArrayList<>(block));
				block.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		in.close();
		return result;
	}
}
