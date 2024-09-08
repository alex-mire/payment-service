package upb.iam.paymentservice.domain.bankaccount;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "bank_account", schema = "payment_service")
public record BankAccount(
        @Id
        UUID id,
        @NotNull
        UUID accountHolderId,
        @NotBlank
        String accountNumber,
        @NotNull
        BankAccountType type,
        @NotNull
        Currency currency,
        @NotNull
        BigDecimal balance,
        @NotNull
        BigDecimal interestRate,
        @NotNull
        @PastOrPresent
        LocalDateTime openDate,
        LocalDateTime closeDate,
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
