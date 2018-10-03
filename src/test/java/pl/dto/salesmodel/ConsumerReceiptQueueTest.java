package pl.dto.salesmodel;

import org.junit.Test;
import pl.database.Database;
import pl.database.impl.sale.InMemoryDatabase;
import pl.generatorfortests.ProductGenerator;
import pl.generatorfortests.ReceiptGenerator;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConsumerReceiptQueueTest {

  BlockingQueue<Receipt> queue = new ArrayBlockingQueue(20);
  Database database = new InMemoryDatabase();

  @Test
  public void saveReceipt() throws Exception {
    Receipt receipt = ReceiptGenerator.generateOneReceipt(
        ProductGenerator.generateProductAB());

    ProducerReceiptQueue producerReceiptQueue =
        new ProducerReceiptQueue(queue, receipt);
    startThread(new Thread(producerReceiptQueue));
    ProducerReceiptQueue producerReceiptQueue2 =
        new ProducerReceiptQueue(queue, receipt);
    startThread(new Thread(producerReceiptQueue2));
    ConsumerReceiptQueue consumer = new ConsumerReceiptQueue(queue, database);
    startThread(new Thread(consumer));
  }

  static void startThread(Thread thread) throws InterruptedException {
    thread.start();
  }
}