package upb.iam.paymentservice.web.externaltransaction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import upb.iam.paymentservice.domain.externaltransaction.ExternalTransactionCurrency;
import upb.iam.paymentservice.domain.internaltransaction.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ExternalTransactionDto {
    private UUID id;
    private UUID sourceId;
    private String iban;
    private String receiverFullName;
    private BigDecimal amount;
    private ExternalTransactionCurrency currency;
    private BigDecimal fee;
    private String description;
    private LocalDateTime transactionDate;
    private TransactionType type;
}
