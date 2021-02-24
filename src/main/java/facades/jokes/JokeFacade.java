package facades.jokes;

import dtos.jokes.JokeDTO;
import entities.jokes.Joke;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 * @author Emil
 */
public class JokeFacade {

  private static JokeFacade instance;
  private static EntityManagerFactory emf;

  //Private Constructor to ensure Singleton
  private JokeFacade() {}


  /**
   * @param _emf
   * @return an instance of this facade class.
   */
  public static JokeFacade getJokeFacade(EntityManagerFactory _emf) {
    if (instance == null) {
      emf = _emf;
      instance = new JokeFacade();
    }
    return instance;
  }

  private EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public JokeDTO create(JokeDTO jokeDTO){
    Joke joke = new Joke(jokeDTO.getThejoke(), jokeDTO.getType());
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      em.persist(joke);
      em.getTransaction().commit();
    } finally {
      em.close();
    }
    return new JokeDTO(joke);
  }

  public JokeDTO getById(int id){
    EntityManager em = emf.createEntityManager();
    return new JokeDTO(em.find(Joke.class, id));
  }

  public List<JokeDTO> getAllJokes(){
    EntityManager em = emf.createEntityManager();
    TypedQuery<Joke> query = em.createQuery("SELECT joke FROM Joke joke", Joke.class);
    List<Joke> rms = query.getResultList();
    return JokeDTO.getDtos(rms);
  }

  public JokeDTO getRandomJoke(){
    List<JokeDTO> jokes = getAllJokes();
    int jokeNo = new Random().nextInt(jokes.size() +1);
    return jokes.get(jokeNo);
  }

  public static void main(String[] args) {
    emf = EMF_Creator.createEntityManagerFactory();
    JokeFacade fe = getJokeFacade(emf);
    fe.getAllJokes().forEach(dto->System.out.println(dto));
  }

}
