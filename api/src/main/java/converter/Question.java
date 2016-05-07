package converter;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Michaela Bamburová on 07.05.2016.
 */
@XStreamAlias("question")
public class Question {

	private Long id;
	private String text;
	private int points;
	// ...


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Question question = (Question) o;

		if (points != question.points) return false;
		if (id != null ? !id.equals(question.id) : question.id != null) return false;
		return text != null ? text.equals(question.text) : question.text == null;

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (text != null ? text.hashCode() : 0);
		result = 31 * result + points;
		return result;
	}

	@Override
	public String toString() {
		return "Text of question with id " + id + ": " + text + ", points: " + points;
	}
}
