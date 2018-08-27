package pl.database.impl.sale;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.database.impl.DateProcessorHelper;
import pl.dto.salesmodel.Receipt;

import java.io.File;
import java.io.IOException;
import java.util.List;

class ReceiptProcessor {

  DateProcessorHelper processorHelper = new DateProcessorHelper();
  ObjectMapper mapper = processorHelper.objectMapper();

  List<Receipt> readFileAndConvertContentIntoListOfReceipts(String receiptPath)
      throws IOException {
    return mapper.readValue(new File(receiptPath),
        mapper.getTypeFactory().constructCollectionType(List.class, Receipt.class));
  }

  void saveToFile(List<Receipt> receipts, File filename) throws IOException {
    mapper.writeValue(filename, receipts);
  }
}
