package pl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.dto.loadermodel.ProductsStore;

import java.util.List;

@Transactional
public interface StoreRepository extends JpaRepository<ProductsStore, Integer> {

  @Query("SELECT p FROM ProductsStore p WHERE p.basisProduct.productName=:productName")
  List<ProductsStore> findByNameProduct(@Param("productName") String productName);
}
