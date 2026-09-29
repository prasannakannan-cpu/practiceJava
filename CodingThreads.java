import java.util.concurrent.*;

public class CodingThreads{
    public static void main(String args[]){
      Thread t1 = new Thread(() -> {System.out.println("Thread is running");});

      t1.start();

      try 
      {
        Thread.sleep(1000);
      }
      catch(InterruptedException ie)
      {
        System.err.println(ie.getMessage());
      }
    }
}