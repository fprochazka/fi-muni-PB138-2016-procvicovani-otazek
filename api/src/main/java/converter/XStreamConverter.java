package converter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

import java.io.Writer;

/**
 * Created by Michaela Bamburová on 07.05.2016.
 */
public class XStreamConverter {

	private Question question = new Question();

	void xmlToDTO(String file) {
		XStream xstream = new XStream();
		xstream.alias("question", Question.class);

		question = (Question) xstream.fromXML(file);
		System.out.println("New question (DTO):");
		System.out.println(question.toString());
	}

	void DTOToXml() {
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {

			public HierarchicalStreamWriter createWriter(Writer writer) {
				return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
			}
		});

		xstream.setMode(XStream.NO_REFERENCES);

		System.out.println("Converted DTO to JSON:");
		System.out.println(xstream.toXML(question));
	}









}
