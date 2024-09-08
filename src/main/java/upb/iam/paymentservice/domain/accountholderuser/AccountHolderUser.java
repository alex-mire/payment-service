package upb.iam.paymentservice.domain.accountholderuser;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "account_holder_user", schema = "payment_service")
public record AccountHolderUser(
        @Id
        UUID id,
        @NotNull
        UUID userId,
        @NotNull
        UUID accountHolderId,
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
