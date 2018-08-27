package pl.database.impl.sale;

import pl.database.Database;
import pl.database.DatabaseTest;

public class InMemoryDatabaseTest extends DatabaseTest {

  @Override
  protected Database provideDatabase() {
    return new InMemoryDatabase();
  }
}