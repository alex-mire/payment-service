package upb.iam.paymentservice.domain.accountholderaddress;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "account_holder_address", schema = "payment-service")
public record AccountHolderAddress(
        @Id
        UUID id,
        @NotNull
        UUID accountHolderId,
        @NotBlank
        String country,
        @NotBlank
        String county,
        @NotBlank
        String city,
        @NotBlank
        String postalCode,
        @NotBlank
        String street,
        @NotBlank
        String number,
        String building,
        String floor,
        String apartmentNumber,
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
