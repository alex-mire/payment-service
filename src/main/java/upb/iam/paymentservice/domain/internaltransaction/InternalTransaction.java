package upb.iam.paymentservice.domain.internaltransaction;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.UUID;

@Table(name = "internal_transaction", schema = "payment_service")
public record InternalTransaction(
        @Id
        UUID id,
        @NotNull
        UUID accountHolderId,
        @NotNull
        UUID sourceId,
        @NotNull
        UUID destinationId,
        @NotNull
        BigDecimal amount,
        @NotNull
        InternalTransactionCurrency currency,
        @NotNull
        BigDecimal fee,
        String description,
        @NotNull
        LocalDateTime transactionDate,
        @NotNull
        TransactionType type,
        @CreatedDate
        LocalDateTime createdDate,
        @CreatedBy
        String createdBy,
        @LastModifiedDate
        LocalDateTime lastModifiedDate,
        @LastModifiedBy
        String lastModifiedBy,
        @Version
        int version
) {
}
