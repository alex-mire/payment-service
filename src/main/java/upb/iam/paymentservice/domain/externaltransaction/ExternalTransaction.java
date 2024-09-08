package upb.iam.paymentservice.domain.externaltransaction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Table;
import upb.iam.paymentservice.domain.internaltransaction.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.UUID;

@Table(name = "external_transaction", schema = "payment_service")
public record ExternalTransaction(
        @Id
        UUID id,
        @NotNull
        UUID accountHolderId,
        @NotNull
        UUID sourceId,
        @NotBlank
        String iban,
        @NotBlank
        String receiverFullName,
        @NotNull
        BigDecimal amount,
        @NotNull
        ExternalTransactionCurrency currency,
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
