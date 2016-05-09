package converter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Michaela Bamburová on 07.05.2016.
 */
public class Main {
	public static void main(String[] args) {

		if (args.length != 1) {
			System.err.println("Wrong number of arguments.");
			System.exit(1);
		}

		String xmlFile = "";
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				xmlFile += sCurrentLine;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		XStreamConverter xStreamConverter = new XStreamConverter();
		xStreamConverter.xmlToDTO(xmlFile);

		System.out.println("---");
		xStreamConverter.DTOToXml();
	}
}
