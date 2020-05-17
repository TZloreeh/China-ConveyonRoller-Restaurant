import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class QueueClass<Integer> {
    private int max;
    private Queue<Integer> queue = new LinkedList<Integer>();
    private ReentrantLock lock = new ReentrantLock(true);
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();
    
    public QueueClass
  (int size) {
    queue = new LinkedList<>();
    this.max = size;
  }

  public void put(Integer e) throws InterruptedException {
    lock.lock();
    try {
      while (queue.size() == max){
        notFull.await();
      }
      queue.add(e);
      System.out.println("|+| New dish added n " + e + "|");
      notEmpty.signalAll();
    } finally{
      lock.unlock();
    }
  }

  //exception control  
  public Integer take() throws InterruptedException {
    lock.lock();
    try {
      while (queue.size() == 0){
        notEmpty.await();
      }
      Integer item = queue.remove();
      System.out.println("|-| Dish removed   n " + item + "|");
      notFull.signalAll();
      return item;
    } finally{
      lock.unlock();
    }
  }

  //cls clear  
  public static void CLS() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
}  
}