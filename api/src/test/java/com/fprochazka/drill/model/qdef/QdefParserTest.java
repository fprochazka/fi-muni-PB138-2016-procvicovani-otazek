package com.fprochazka.drill.model.qdef;

import com.fprochazka.drill.model.drill.question.Answer;
import com.fprochazka.drill.model.drill.question.Question;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class QdefParserTest
{

	private QdefParser parser = new QdefParser();
	private File threeQuestions = new File(this.getClass().getResource("threeQuestions.qdef").getFile());
	private File moreLineTitles = new File(this.getClass().getResource("moreLineTitles.qdef").getFile());
	private File answerComments = new File(this.getClass().getResource("answerComments.qdef").getFile());
	private File multipleCorrect = new File(this.getClass().getResource("multipleCorrect.qdef").getFile());

	@Test
	public void testNumberOfQuestions1() throws Exception
	{
		ArrayList<Question> result = parser.parse(threeQuestions);
		assertEquals(3, result.size());
	}

	@Test
	public void testNumberOfQuestions2() throws Exception
	{
		ArrayList<Question> result = parser.parse(moreLineTitles);
		assertEquals(2, result.size());
	}

	@Test
	public void testNumberOfQuestions3() throws Exception
	{
		ArrayList<Question> result = parser.parse(answerComments);
		assertEquals(2, result.size());
	}

	@Test
	public void testNumberOfQuestions4() throws Exception
	{
		ArrayList<Question> result = parser.parse(multipleCorrect);
		assertEquals(2, result.size());
	}

	@Test
	public void testNumberOfAnswers1() throws Exception
	{
		ArrayList<Question> result = parser.parse(threeQuestions);
		assertEquals(5, result.get(0).getAnswers().size());
		assertEquals(3, result.get(1).getAnswers().size());
		assertEquals(4, result.get(2).getAnswers().size());
	}

	@Test
	public void testNumberOfAnswers2() throws Exception
	{
		ArrayList<Question> result = parser.parse(moreLineTitles);
		assertEquals(5, result.get(0).getAnswers().size());
		assertEquals(5, result.get(1).getAnswers().size());
	}

	@Test
	public void testNumberOfAnswers3() throws Exception
	{
		ArrayList<Question> result = parser.parse(answerComments);
		assertEquals(3, result.get(0).getAnswers().size());
		assertEquals(4, result.get(1).getAnswers().size());
	}

	@Test
	public void testNumberOfAnswers4() throws Exception
	{
		ArrayList<Question> result = parser.parse(multipleCorrect);
		assertEquals(5, result.get(0).getAnswers().size());
		assertEquals(6, result.get(1).getAnswers().size());
	}

	@Test
	public void testNumberOfCorrectAnswers1() throws Exception
	{
		countCorrectAnswers(threeQuestions);
	}

	@Test
	public void testNumberOfCorrectAnswers2() throws Exception
	{
		countCorrectAnswers(moreLineTitles);
	}

	@Test
	public void testNumberOfCorrectAnswers3() throws Exception
	{
		countCorrectAnswers(answerComments);
	}

	private void countCorrectAnswers(File file) throws IOException
	{
		ArrayList<Question> result = parser.parse(file);
		int count = 0;
		int index = 0;
		for (Question q : result) {
			for (Answer a : result.get(index).getAnswers()) {
				if (a.isCorrect()) {
					count++;
				}
			}
			assertEquals(1, count);
			index++;
			count = 0;
		}
	}

	@Test
	public void testNumberOfCorrectAnswers4() throws Exception
	{
		ArrayList<Question> result = parser.parse(multipleCorrect);
		int count = 0;
		for (Answer a : result.get(0).getAnswers()) {
			if (a.isCorrect()) {
				count++;
			}
		}
		assertEquals(3, count);
		count = 0;

		for (Answer a : result.get(1).getAnswers()) {
			if (a.isCorrect()) {
				count++;
			}
		}
		assertEquals(2, count);
	}

	@Test
	public void testTitles1() throws Exception
	{
		ArrayList<Question> result = parser.parse(threeQuestions);
		assertEquals(result.get(0).getTitle(), "První verze UNIXu byla vytvořena v");
		assertEquals(result.get(1).getTitle(), "Co vzniklo dříve? MS-DOS (předchůdce MS-Windows) nebo UNIX?");
		assertEquals(result.get(2).getTitle(), "Kdo jsou autoři prvních verzí UNIXu?");
	}

	@Test
	public void testTitles2() throws Exception
	{
		ArrayList<Question> result = parser.parse(moreLineTitles);
		assertEquals(result.get(0).getTitle(), "Po provedení příkazů\n" +
			"<PRE> $ echo 'mravenec' > a; ls -l a\n" +
			" -rw-r--r--  1 brandejs staff 9 dub  8 23:23 a\n" +
			" $ echo ferda >> a</PRE>\n" +
			"bude mít soubor 'a' velikost");
		assertEquals(result.get(1).getTitle(), "Posloupnost příkazů\n" +
			"<PRE>cat &lt;&lt;ukazka\n" +
			"ls -l ukazka\n" +
			"ukazka</PRE>\n" +
			"předá na standardní výstup");
	}

	@Test
	public void testTitles3() throws Exception
	{
		ArrayList<Question> result = parser.parse(answerComments);
		assertEquals(result.get(0).getTitle(), "V semestru podzim 2004 byl ustaven rekord v rychlosti rozebrání\n" +
			"(zaplnění) některých zkušebních termínů. Víte nebo tipnete jaký?");
		assertEquals(result.get(1).getTitle(), "Hrajete kamenožrouta?");
	}

	@Test
	public void testTitles4() throws Exception
	{
		ArrayList<Question> result = parser.parse(multipleCorrect);
		assertEquals(result.get(0).getTitle(), "Optimální algoritmus, který pro livovolnou (neorientovanou) " +
			"kružnici na <I>n</I> uzlech s kladně ohodnocenými hranami a její uzel <I>r</I> vypočte vzdálenost " +
			"z uzlu <I>r</I> do všech ostatních uzlů, má složitost ležící v:");
		assertEquals(result.get(1).getTitle(), "Máme libovolnou neorientovanou kružnici s kladně ohodnocenými " +
			"hranami a <I>n</I> uzly. Optimální algoritmus řešící výpočet matice vzdáleností mezi jejími uzly, " +
			"má složitost v:");
	}

	@Test
	public void testTextOfOptions1() throws Exception
	{
		ArrayList<Question> result = parser.parse(threeQuestions);
		assertEquals("Bell Laboratories", result.get(0).getAnswers().get(0).getText());
		assertEquals("University of Berkeley", result.get(0).getAnswers().get(1).getText());
		assertEquals("Microsoftu", result.get(0).getAnswers().get(2).getText());
		assertEquals("Novellu", result.get(0).getAnswers().get(3).getText());
		assertEquals("USL (UNIX System Laboratories)", result.get(0).getAnswers().get(4).getText());

		assertEquals("MS-DOS", result.get(1).getAnswers().get(0).getText());
		assertEquals("UNIX", result.get(1).getAnswers().get(1).getText());
		assertEquals("Obojí je zhruba stejně staré.", result.get(1).getAnswers().get(2).getText());

		assertEquals("Steeve Jobs, Tim O'Reilly, Andrew Tanenbaum", result.get(2).getAnswers().get(0).getText());
		assertEquals("Larry Ellison, Scott McNealy, Michael Tiemann", result.get(2).getAnswers().get(1).getText());
		assertEquals("Brian Kernighan, Dennis Ritchie, Ken Thompson", result.get(2).getAnswers().get(2).getText());
		assertEquals("Eric Raymond, Richard Stallman, Linus Torvalds", result.get(2).getAnswers().get(3).getText());
	}

	@Test
	public void testTextOfOptions2() throws Exception
	{
		ArrayList<Question> result = parser.parse(moreLineTitles);
		assertEquals("5", result.get(0).getAnswers().get(0).getText());
		assertEquals("6", result.get(0).getAnswers().get(1).getText());
		assertEquals("13", result.get(0).getAnswers().get(2).getText());
		assertEquals("14", result.get(0).getAnswers().get(3).getText());
		assertEquals("15", result.get(0).getAnswers().get(4).getText());

		assertEquals("obsah souboru 'ukazka', výstup příkazu 'ls -l ukazka', spustí se program 'ukazka' z běžného adresáře", result.get(1).getAnswers().get(0).getText());
		assertEquals("text 'ls -l ukazka'", result.get(1).getAnswers().get(1).getText());
		assertEquals("text 'ukazka'", result.get(1).getAnswers().get(2).getText());
		assertEquals("nepředá se nic", result.get(1).getAnswers().get(3).getText());
		assertEquals("předá se výstup programu 'ukazka'", result.get(1).getAnswers().get(4).getText());
	}

	@Test
	public void testTextOfOptions3() throws Exception
	{
		ArrayList<Question> result = parser.parse(answerComments);
		assertEquals("  2 sekundy", result.get(0).getAnswers().get(0).getText());
		assertEquals("  121 sekund", result.get(0).getAnswers().get(1).getText());
		assertEquals("  cca 4 minuty", result.get(0).getAnswers().get(2).getText());

		assertEquals("  Ano", result.get(1).getAnswers().get(0).getText());
		assertEquals("  Ne", result.get(1).getAnswers().get(1).getText());
		assertEquals("  Když jsem čekával na odezvu IS, tj. poslední půl rok ne", result.get(1).getAnswers().get(2).getText());
		assertEquals("  co to je?", result.get(1).getAnswers().get(3).getText());
	}

	@Test
	public void testTextOfOptions4() throws Exception
	{
		ArrayList<Question> result = parser.parse(multipleCorrect);
		assertEquals("<M>o (n)</M>", result.get(0).getAnswers().get(0).getText());
		assertEquals("<M>\\Omega (n\\cdot \\log\\ n)</M>", result.get(0).getAnswers().get(1).getText());
		assertEquals("<M>\\Omega (n)</M>", result.get(0).getAnswers().get(2).getText());
		assertEquals("<M>\\omega (\\log\\ n)</M>", result.get(0).getAnswers().get(3).getText());
		assertEquals("<M>o (n^{2})</M>", result.get(0).getAnswers().get(4).getText());

		assertEquals("<M>O (n)</M>", result.get(1).getAnswers().get(0).getText());
		assertEquals("<M>\\omega (n^{2})</M>", result.get(1).getAnswers().get(1).getText());
		assertEquals("<M>O (n^{2}\\cdot \\log\\ n)</M>", result.get(1).getAnswers().get(2).getText());
		assertEquals("<M>o (n^{2})</M>", result.get(1).getAnswers().get(3).getText());
		assertEquals("<M>\\Theta (n^{3})</M>", result.get(1).getAnswers().get(4).getText());
		assertEquals("<M>\\omega (n\\cdot \\log\\ n)</M>", result.get(1).getAnswers().get(5).getText());
	}

	@Test
	public void testCorrectAnswer1() throws Exception
	{
		ArrayList<Question> result = parser.parse(threeQuestions);
		assertEquals(true, result.get(0).getAnswers().get(0).isCorrect());
		assertEquals(false, result.get(0).getAnswers().get(1).isCorrect());
		assertEquals(false, result.get(0).getAnswers().get(2).isCorrect());
		assertEquals(false, result.get(0).getAnswers().get(3).isCorrect());
		assertEquals(false, result.get(0).getAnswers().get(4).isCorrect());

		assertEquals(false, result.get(1).getAnswers().get(0).isCorrect());
		assertEquals(true, result.get(1).getAnswers().get(1).isCorrect());
		assertEquals(false, result.get(1).getAnswers().get(2).isCorrect());

		assertEquals(false, result.get(2).getAnswers().get(0).isCorrect());
		assertEquals(false, result.get(2).getAnswers().get(1).isCorrect());
		assertEquals(true, result.get(2).getAnswers().get(2).isCorrect());
		assertEquals(false, result.get(2).getAnswers().get(3).isCorrect());
	}

	@Test
	public void testCorrectAnswer2() throws Exception
	{
		ArrayList<Question> result = parser.parse(moreLineTitles);
		assertEquals(false, result.get(0).getAnswers().get(0).isCorrect());
		assertEquals(false, result.get(0).getAnswers().get(1).isCorrect());
		assertEquals(false, result.get(0).getAnswers().get(2).isCorrect());
		assertEquals(false, result.get(0).getAnswers().get(3).isCorrect());
		assertEquals(true, result.get(0).getAnswers().get(4).isCorrect());

		assertEquals(false, result.get(1).getAnswers().get(0).isCorrect());
		assertEquals(true, result.get(1).getAnswers().get(1).isCorrect());
		assertEquals(false, result.get(1).getAnswers().get(2).isCorrect());
		assertEquals(false, result.get(1).getAnswers().get(3).isCorrect());
		assertEquals(false, result.get(1).getAnswers().get(4).isCorrect());
	}

	@Test
	public void testCorrectAnswer3() throws Exception
	{
		ArrayList<Question> result = parser.parse(answerComments);
		assertEquals(true, result.get(0).getAnswers().get(0).isCorrect());
		assertEquals(false, result.get(0).getAnswers().get(1).isCorrect());
		assertEquals(false, result.get(0).getAnswers().get(2).isCorrect());

		assertEquals(true, result.get(1).getAnswers().get(0).isCorrect());
		assertEquals(false, result.get(1).getAnswers().get(1).isCorrect());
		assertEquals(false, result.get(1).getAnswers().get(2).isCorrect());
		assertEquals(false, result.get(1).getAnswers().get(3).isCorrect());
	}

	@Test
	public void testCorrectAnswer4() throws Exception
	{
		ArrayList<Question> result = parser.parse(multipleCorrect);
		assertEquals(false, result.get(0).getAnswers().get(0).isCorrect());
		assertEquals(false, result.get(0).getAnswers().get(1).isCorrect());
		assertEquals(true, result.get(0).getAnswers().get(2).isCorrect());
		assertEquals(true, result.get(0).getAnswers().get(3).isCorrect());
		assertEquals(true, result.get(0).getAnswers().get(4).isCorrect());

		assertEquals(false, result.get(1).getAnswers().get(0).isCorrect());
		assertEquals(false, result.get(1).getAnswers().get(1).isCorrect());
		assertEquals(true, result.get(1).getAnswers().get(2).isCorrect());
		assertEquals(false, result.get(1).getAnswers().get(3).isCorrect());
		assertEquals(false, result.get(1).getAnswers().get(4).isCorrect());
		assertEquals(true, result.get(1).getAnswers().get(5).isCorrect());
	}
}
