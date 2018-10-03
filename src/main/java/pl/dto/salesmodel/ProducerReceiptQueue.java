package pl.dto.salesmodel;

import java.util.concurrent.BlockingQueue;

public class ProducerReceiptQueue implements Runnable {

  protected BlockingQueue<Receipt> queue = null;
  private Receipt receipt;

  public ProducerReceiptQueue(BlockingQueue<Receipt> queue,
      Receipt receipt) {
    this.queue = queue;
    this.receipt = receipt;
  }

  public void run() {
    try {
      if (queue.remainingCapacity() == 0) {
        throw new InterruptedException("Queue is full");
      } else {
        queue.put(receipt);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
