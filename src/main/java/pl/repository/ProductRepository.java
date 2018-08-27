package pl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dto.salesmodel.productmodel.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

  @Query("SELECT COUNT(p)>0 FROM Product p")
  boolean existsProducts();

  @Query("SELECT p FROM Product p WHERE p.productStore.basisProduct.productName=:productName")
  List<Product> findByNameProduct(@Param("productName") String productName);
}
