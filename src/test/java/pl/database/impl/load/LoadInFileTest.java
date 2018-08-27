package pl.database.impl.load;

import pl.FileProcessorForTest.FileConfigurationToTest;
import pl.database.DataLoader;
import pl.database.DataLoaderTest;

public class LoadInFileTest extends DataLoaderTest {

  @Override
  protected DataLoader loader() {
    return new LoadInFile(FileConfigurationToTest.DATALOADER_TEST_PATH);
  }
}