package pl.database.impl.load;

import pl.database.DataLoader;
import pl.database.DataLoaderTest;

public class LoadInMemoryTest extends DataLoaderTest {

  @Override
  protected DataLoader loader() {
    return new LoadInMemory();
  }


}