package pl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dto.salesmodel.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

}

