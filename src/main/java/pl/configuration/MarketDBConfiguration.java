package pl.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import pl.calculators.Calculate;
import pl.calculators.impl.DiscountCalculator;
import pl.database.DataLoader;
import pl.database.Database;
import pl.database.impl.load.LoadInSql;
import pl.database.impl.sale.SqlDatabase;

@Configuration
public class MarketDBConfiguration {

  @Bean
  @Primary
  public DataLoader loader() {
    return new LoadInSql();
  }

  @Bean
  public Database database() {
    return new SqlDatabase();
  }

  @Bean
  public Calculate calculate() {
    return new DiscountCalculator();
  }
}
