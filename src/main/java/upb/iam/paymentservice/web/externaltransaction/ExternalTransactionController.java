package upb.iam.paymentservice.web.externaltransaction;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upb.iam.paymentservice.domain.bankaccount.BankAccount;
import upb.iam.paymentservice.domain.bankaccount.BankAccountRepository;
import upb.iam.paymentservice.domain.externaltransaction.ExternalTransaction;
import upb.iam.paymentservice.domain.externaltransaction.ExternalTransactionRepository;
import upb.iam.paymentservice.domain.shared.BadRequestException;
import upb.iam.paymentservice.service.BankAccountService;
import upb.iam.paymentservice.web.externaltransaction.dto.ExternalTransactionDto;
import upb.iam.paymentservice.web.externaltransaction.dto.SendExternalTransactionDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/external-transaction")
@Transactional
public class ExternalTransactionController {
    private final ExternalTransactionRepository externalTransactionRepository;
    private final BankAccountRepository bankAccountRepository;
    private final BankAccountService bankAccountService;

    @GetMapping("/{transactionId}")
    public ExternalTransactionDto getExternalTransactionById(@PathVariable UUID transactionId) {
        return externalTransactionRepository.findDtoById(transactionId)
                .orElseThrow(() -> new BadRequestException("Transaction not found"));
    }

    @GetMapping("/{accountHolderId}/all")
    public List<ExternalTransactionDto> getAllExternalTransactionsByAccountHolderId(@PathVariable UUID accountHolderId) {
        return externalTransactionRepository.findAllDtoById(accountHolderId);
    }

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody SendExternalTransactionDto sendExternalTransactionDto) {

        var bankAccount = bankAccountRepository.findById(sendExternalTransactionDto.getSourceId())
                .orElseThrow(() -> new BadRequestException("Bank account not found"));
        validateBankAccount(sendExternalTransactionDto, bankAccount);
        var newTotalAmount = bankAccount.balance().subtract(sendExternalTransactionDto.getAmount().add(sendExternalTransactionDto.getFee()));
        bankAccountService.updateBankAccount(bankAccount, newTotalAmount);
        sendTransaction(sendExternalTransactionDto);
        return ResponseEntity.ok("Transaction sent");
    }

    private static void validateBankAccount(SendExternalTransactionDto sendExternalTransactionDto, BankAccount bankAccount) {
        if (bankAccount.balance().compareTo(sendExternalTransactionDto.getAmount().add(sendExternalTransactionDto.getFee())) < 0) {
            throw new BadRequestException("Insufficient funds");
        }

        if (!bankAccount.currency().name().equals(sendExternalTransactionDto.getCurrency().name())) {
            throw new BadRequestException("Currency not supported");
        }
    }

    private void sendTransaction(SendExternalTransactionDto sendExternalTransactionDto) {
        var externalTransaction = new ExternalTransaction(UUID.randomUUID(),
                sendExternalTransactionDto.getAccountHolderId(),
                sendExternalTransactionDto.getSourceId(),
                sendExternalTransactionDto.getIban(),
                sendExternalTransactionDto.getReceiverFullName(),
                sendExternalTransactionDto.getAmount(),
                sendExternalTransactionDto.getCurrency(),
                sendExternalTransactionDto.getFee(),
                sendExternalTransactionDto.getDescription(),
                sendExternalTransactionDto.getTransactionDate(),
                sendExternalTransactionDto.getType(),
                null,
                null,
                null,
                null,
                0
        );
        externalTransactionRepository.save(externalTransaction);
    }
}
