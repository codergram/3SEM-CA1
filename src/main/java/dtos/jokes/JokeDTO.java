package dtos.jokes;

import entities.jokes.Joke;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 */
public class JokeDTO {
  private int id;
  private String thejoke;
  private String type;

  public JokeDTO(String thejoke, String type) {
    this.thejoke = thejoke;
    this.type = type;
  }

  public static List<JokeDTO> getDtos(List<Joke> rms){
    List<JokeDTO> jokes = new ArrayList<>();
    rms.forEach(rm->jokes.add(new JokeDTO(rm)));
    return jokes;
  }


  public JokeDTO(Joke rm) {
    this.id = rm.getId();
    this.thejoke = rm.getThejoke();
    this.type = rm.getType();
  }

  public String getThejoke() {
    return thejoke;
  }

  public void setThejoke(String thejoke) {
    this.thejoke = thejoke;
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
    sb.append(", thejoke='").append(thejoke).append('\'');
    sb.append(", type='").append(type).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
