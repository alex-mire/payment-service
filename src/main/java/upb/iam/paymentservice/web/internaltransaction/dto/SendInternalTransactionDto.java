package upb.iam.paymentservice.web.internaltransaction.dto;

import lombok.Getter;
import lombok.Setter;
import upb.iam.paymentservice.domain.externaltransaction.ExternalTransactionCurrency;
import upb.iam.paymentservice.domain.internaltransaction.InternalTransactionCurrency;
import upb.iam.paymentservice.domain.internaltransaction.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class SendInternalTransactionDto {
    private UUID accountHolderId;
    private UUID sourceId;
    private UUID destinationId;
    private BigDecimal amount;
    private InternalTransactionCurrency currency;
    private BigDecimal fee;
    private String description;
    private LocalDateTime transactionDate;
    private TransactionType type;
}
