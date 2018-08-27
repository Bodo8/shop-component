package pl.database.impl;

import pl.dto.salesmodel.Receipt;

import java.io.File;
import java.util.List;

public class FileProcessor {

  /**
   * Method provides file path for an receipt to be saved in.
   *
   * @param directoryName - the name of main directory where subdirectories should be created.
   * @param receipt - the receipt the method should provide the file path for.
   * @return - the method returns File object as a file Path.
   */
  public static File getReceiptFilePath(String directoryName, Receipt receipt) {
    int year = receipt.getDate().getYear();
    String month = receipt.getDate().getMonth().toString();
    String stringDirectory = directoryName + year + "/" + month + "/";
    File directory = new File(stringDirectory);
    directory.mkdirs();
    return new File(stringDirectory + "/Receipt.json");
  }

  public static File getProductsFilePath(String directoryName) {
    File directory = new File(directoryName);
    directory.mkdirs();
    return new File(directoryName + "/products.json");
  }

  /**
   * Method makes a list of directories, subdirectories and files.
   *
   * @param directoryName - from which the list of subdirectories is to be made.
   * @param files - a collection object which the list of subdirectories is to be referred to.
   */
  public static void getFilesIncludingSubdirectories(String directoryName, List<File> files) {
    File directory = new File(directoryName);
    for (File file : directory.listFiles()) {
      if (file.isFile()) {
        files.add(file);
      } else if (file.isDirectory()) {
        getFilesIncludingSubdirectories(file.getAbsolutePath(), files);
      }
    }
  }

}
