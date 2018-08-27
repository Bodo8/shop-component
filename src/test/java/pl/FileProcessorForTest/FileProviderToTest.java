package pl.FileProcessorForTest;

import java.io.File;

public class FileProviderToTest {

  /**
   * Method  deletes all subdirectories and files from given one.
   *
   * @param directory - the directory from which other subdirectories and files are to be removed.
   */
  public static void purgeDirectory(File directory) {
    if (directory.exists() && directory.isDirectory()) {
      for (File file : directory.listFiles()) {
        if (file.isDirectory()) {
          purgeDirectory(file);
        }
        file.delete();
      }
    }
  }
}
