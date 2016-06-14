package com.fprochazka.drill.cli;

import com.fprochazka.drill.model.drill.*;
import com.fprochazka.drill.model.drill.question.Question;
import com.fprochazka.drill.model.drill.question.QuestionFacade;
import com.fprochazka.drill.model.json.JSONQuestionParser;
import com.fprochazka.drill.model.qdef.QdefParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@ComponentScan(basePackages = "com.fprochazka.drill")
@ImportResource(locations = "application-context.xml")
@EnableMongoRepositories(basePackages = "com.fprochazka.drill")
public class QuestionLoader implements CommandLineRunner
{
	@Autowired
	private QdefParser qdefParser;
	@Autowired
	private JSONQuestionParser jsonParser;
	@Autowired
	private QuestionFacade questionFacade;
	@Autowired
	private DrillFacade drillFacade;
	@Autowired
	private DrillRepository drillRepository;

	public static String getExtension(String filename) {
		if (filename == null) {
			return null;
		}
		int extensionPos = filename.lastIndexOf('.');
		int lastUnixPos = filename.lastIndexOf('/');
		int lastWindowsPos = filename.lastIndexOf('\\');
		int lastSeparator = Math.max(lastUnixPos, lastWindowsPos);
		int index = lastSeparator > extensionPos ? -1 : extensionPos;
		if (index == -1) {
			return "";
		} else {
			return filename.substring(index + 1);
		}
	}


	public void load( String code, String filename )
	{
		Drill drill = drillRepository.getDrillByCode( code );
		if ( drill == null ) {
			System.out.println("Drill with given code '" + code + "' does not exist.\nCreating new drill.");
			try
			{
				drill = drillFacade.createDrill(code, code);
			} catch (DrillCodeNotUniqueException e) {
				System.err.println("Failed to create new drill. Drill with given code '" + code + "' already exists.");
				return;
			}
		}
		String ext = getExtension( filename );

		File file = new File( filename );
		List< Question > questions = new ArrayList<>();

		if ( ext.equals( "qdef" ) || ext.equals("QDEF") )
		{
			try {
				questions = qdefParser.parse( file );
				for ( Question question: questions )
				{
					questionFacade.createQuestion( drill.getId(), question.getText(), question.getAnswers() );
				}
				System.out.println( "Total " + questions.size() + " succesfully created questions and added to drill " + drill.getCode() + "." );
			} catch (IOException e) {
				System.err.println( "Problem while reading file " + filename + ".\n" + e.getMessage()  );
				return;
			} catch (DrillNotFoundException e) {
				System.err.println( "Failed to create question in drill " + code + ". Apparently, it does not exist." );
				return;
			}
		}

		if ( ext.equals("json") || ext.equals("JSON") )
		{
			try {
				questions = jsonParser.parse( drill, file );
				for ( Question question: questions )
				{
					questionFacade.createQuestion( drill.getId(), question.getText(), question.getAnswers() );
				}
				System.out.println( "Total " + questions.size() + " succesfully created questions and added to drill " + drill.getCode() + "." );
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (DrillNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run(String... args) throws Exception
	{
		if (args.length != 2) {
			System.err.println("Invalid number of arguments.");
			return;
		}
		String code = args[0];
		String filename = args[1];

		load( code, filename );


	}
}
