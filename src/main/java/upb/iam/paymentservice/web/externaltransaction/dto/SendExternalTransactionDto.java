package upb.iam.paymentservice.web.externaltransaction.dto;

import lombok.Getter;
import lombok.Setter;
import upb.iam.paymentservice.domain.externaltransaction.ExternalTransactionCurrency;
import upb.iam.paymentservice.domain.internaltransaction.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class SendExternalTransactionDto {
    private UUID accountHolderId;
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
