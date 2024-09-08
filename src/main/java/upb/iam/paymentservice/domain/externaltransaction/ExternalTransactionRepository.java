package upb.iam.paymentservice.domain.externaltransaction;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import upb.iam.paymentservice.web.externaltransaction.dto.ExternalTransactionDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExternalTransactionRepository extends CrudRepository<ExternalTransaction, UUID> {

    @Query("""
            select
               id,
               source_id,
               iban,
               receiver_full_name,
               amount,
               currency,
               fee,
               description,
               transaction_date,
               type
               from payment_service.external_transaction
               where id = :transactionId
            """)
    Optional<ExternalTransactionDto> findDtoById(UUID transactionId);


    @Query("""
           select
           id,
           source_id,
           iban,
           receiver_full_name,
           amount,
           currency,
           fee,
           description,
           transaction_date,
           type
           from payment_service.external_transaction
           where account_holder_id = :accountHolderId
           """)
    List<ExternalTransactionDto> findAllDtoById(UUID accountHolderId);
}
