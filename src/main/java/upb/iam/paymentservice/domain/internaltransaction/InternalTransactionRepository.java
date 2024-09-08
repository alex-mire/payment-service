package upb.iam.paymentservice.domain.internaltransaction;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import upb.iam.paymentservice.web.internaltransaction.dto.InternalTransactionDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InternalTransactionRepository extends CrudRepository<InternalTransaction, UUID> {

    @Query("""
            select
            id,
            source_id,
            destination_id,
            amount,
            currency,
            fee,
            description,
            transaction_date,
            type
            from payment_service.internal_transaction
            where id = :transactionId
            """)
    Optional<InternalTransactionDto> findDtoById(String transactionId);

    @Query("""
            select
            id,
            source_id,
            destination_id,
            amount,
            currency,
            fee,
            description,
            transaction_date,
            type
            from payment_service.internal_transaction
            where account_holder_id = :accountHolderId
            """)
    List<InternalTransactionDto> findAllDtoById(UUID accountHolderId);

}
