package facades.jokes;

import dtos.jokes.JokeDTO;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 */
public class JokePopulator {
  public static boolean populate(){
    System.out.println("Running " + JokePopulator.class.getSimpleName());
    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    JokeFacade fe = JokeFacade.getJokeFacade(emf);
    try{
      fe.create(
          new JokeDTO(
              "How do you keep a blonde busy for hours?\n"
                  + "Put her in a round room and tell her to sit in the corner.",
              "Blonde"
          ));

      fe.create(
          new JokeDTO(
              "The three most well known languages in India are English, Hindi, and...\n"
              + "JavaScript",
              "Programmer"
          ));
      fe.create(
          new JokeDTO(
              "Hey girl, are you a Java compiler?\n"
                  + "Because when I proposed a Date, you said I wasn't your type.",
              "Programmer"
          ));
      fe.create(
          new JokeDTO(
              "0 is false and 1 is true, right?\n"
                  + "1",
              "Programmer"
          ));
      fe.create(
          new JokeDTO(
              "What do you call a group of baby soldiers?\n"
                  + "An infantry.",
              "Baby"
          ));
      fe.create(
          new JokeDTO(
              "What do you do with a fussy baby?\n"
                  + "You pacify it.",
              "Baby"
          ));
      fe.create(
          new JokeDTO(
              "IT paradox?\n"
                  + "The warmer a computer becomes, the more it freezes.",
              "Geek"));
      fe.create(
          new JokeDTO(
              "No means no,\n"
                  + "Unless she's dyslexic.",
              "Mean"));
      fe.create(
          new JokeDTO(
              "No means no,\n"
                  + "Unless she's dyslexic.",
              "Mean"));
      fe.create(
          new JokeDTO(
              "Son: \"Mommy why doesn't Gandhi have hair?\"\n"
                  + "Mom: \"Because he never lies.\"\n"
                  + "Son: \"Ohh now I see why ladies have long hair.\"",
              "Mean"));
      fe.create(
          new JokeDTO(
              "If we weren't meant to eat meat\n"
                  + "why are cows made out of food?",
              "Mean"));
      return true;
    } catch (Exception e){
      return false;
    }
  }
  public static void main(String[] args) {
    populate();
  }
}
