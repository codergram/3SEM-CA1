package dtos.jokes;

import entities.jokes.Joke;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 */
public class JokeDTO {
  private int id;
  private String question;
  private String answer;
  private String type;

  public JokeDTO(String question, String answer, String type) {
    this.question = question;
    this.answer = answer;
    this.type = type;
  }

  public static List<JokeDTO> getDtos(List<Joke> rms){
    List<JokeDTO> jokes = new ArrayList<>();
    rms.forEach(rm->jokes.add(new JokeDTO(rm)));
    return jokes;
  }


  public JokeDTO(Joke rm) {
    this.id = rm.getId();
    this.question = rm.getQuestion();
    this.answer = rm.getAnswer();
    this.type = rm.getType();
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("JokeDTO{");
    sb.append("id=").append(id);
    sb.append(", question='").append(question).append('\'');
    sb.append(", answer='").append(answer).append('\'');
    sb.append(", type='").append(type).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
