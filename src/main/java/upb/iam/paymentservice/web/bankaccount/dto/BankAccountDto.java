package upb.iam.paymentservice.web.bankaccount.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import upb.iam.paymentservice.domain.bankaccount.BankAccountType;
import upb.iam.paymentservice.domain.bankaccount.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class BankAccountDto {
    UUID accountHolderId;
    String accountNumber;
    BankAccountType type;
    Currency currency;
    BigDecimal balance;
    BigDecimal interestRate;
    LocalDateTime openDate;
    LocalDateTime closeDate;
}
