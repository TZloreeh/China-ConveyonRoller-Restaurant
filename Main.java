// Lorenzo Eccher - Ristorante Cinese, codice importato da/in collaborazione con  @GabrieleConcli e interpretato nel funzionamento
import java.util.Random;

class Main {
  //rnd initializing
  static Random rand = new Random();

  //main
  public static void main(String[] args) throws InterruptedException {
    //clearing the console for the ux purpose 
    QueueClass.CLS();
    System.out.println("Starting Conveyor Roller...");


    QueueClass queue = new QueueClass(10);

    final Runnable producer = () -> {
      while (true) {
        try {
          int x = createItem();
          queue.put(x);

          Thread.sleep(300);

        } catch (InterruptedException e) { }
      }
    };

    //starting 2 threads producer
    new Thread(producer).start();
    new Thread(producer).start();

    final Runnable consumer = () -> {
      while (true) {
        try {
          queue.take();

          Thread.sleep(500);

        } catch (InterruptedException e) { }
      }
    };

    //starting 2 threads comsuner 
    new Thread(consumer).start();
    new Thread(consumer).start();
  }

  public static int createItem() {
    int x = rand.nextInt(9) + 1;
    return x;
  }
}