package pl.dto.salesmodel;


import pl.database.Database;

import java.util.concurrent.BlockingQueue;

public class ConsumerReceiptQueue extends Thread {

  protected BlockingQueue<Receipt> queue = null;
  private final Database database;

  public ConsumerReceiptQueue(BlockingQueue<Receipt> queue,
      Database database) {
    this.queue = queue;
    this.database = database;
  }

  public void run() {

    try {
      while (true) {
        if (!queue.isEmpty()) {
          Receipt receipt = queue.take();
          System.out.println(receipt);
          database.saveReceipt(receipt);
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

