package upb.iam.paymentservice.web.internaltransaction;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upb.iam.paymentservice.domain.bankaccount.BankAccount;
import upb.iam.paymentservice.domain.bankaccount.BankAccountRepository;
import upb.iam.paymentservice.domain.externaltransaction.ExternalTransaction;
import upb.iam.paymentservice.domain.internaltransaction.InternalTransaction;
import upb.iam.paymentservice.domain.internaltransaction.InternalTransactionRepository;
import upb.iam.paymentservice.domain.shared.BadRequestException;
import upb.iam.paymentservice.service.BankAccountService;
import upb.iam.paymentservice.web.externaltransaction.dto.SendExternalTransactionDto;
import upb.iam.paymentservice.web.internaltransaction.dto.InternalTransactionDto;
import upb.iam.paymentservice.web.internaltransaction.dto.SendInternalTransactionDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal-transaction")
@Transactional
public class InternalTransactionController {
    private final InternalTransactionRepository internalTransactionRepository;
    private final BankAccountRepository bankAccountRepository;
    private final BankAccountService bankAccountService;

    @GetMapping("/{transactionId}")
    public InternalTransactionDto getInternalTransactionById(@PathVariable String transactionId) {
        return internalTransactionRepository.findDtoById(transactionId)
                .orElseThrow(() -> new BadRequestException("Transaction not found"));
    }

    @GetMapping("/{accountHolderId}/all")
    public List<InternalTransactionDto> getAllInternalTransactionsByAccountHolderId(@PathVariable UUID accountHolderId) {
        return internalTransactionRepository.findAllDtoById(accountHolderId);
    }

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody SendInternalTransactionDto internalTransactionDto) {
        var sourceBankAccount = bankAccountRepository.findById(internalTransactionDto.getSourceId())
                .orElseThrow(() -> new BadRequestException("Source Bank account not found"));
        var destinationBankAccount = bankAccountRepository.findById(internalTransactionDto.getDestinationId())
                .orElseThrow(() -> new BadRequestException("Destination Bank account not found"));

        validateBankAccount(internalTransactionDto, sourceBankAccount);
        var sourceNewTotalAmount = sourceBankAccount.balance().subtract(internalTransactionDto.getAmount().add(internalTransactionDto.getFee()));
        var destinationNewTotalAmount = destinationBankAccount.balance().add(internalTransactionDto.getAmount());
        bankAccountService.updateBankAccount(sourceBankAccount, sourceNewTotalAmount);
        bankAccountService.updateBankAccount(destinationBankAccount, destinationNewTotalAmount);
        sendTransaction(internalTransactionDto);
        return ResponseEntity.ok("Transaction sent");
    }

    private static void validateBankAccount(SendInternalTransactionDto sendInternalTransactionDto, BankAccount bankAccount) {
        if (bankAccount.balance().compareTo(sendInternalTransactionDto.getAmount().add(sendInternalTransactionDto.getFee())) < 0) {
            throw new BadRequestException("Insufficient funds");
        }

        if (!bankAccount.currency().name().equals(sendInternalTransactionDto.getCurrency().name())) {
            throw new BadRequestException("Currency not supported");
        }
    }

    private void sendTransaction(SendInternalTransactionDto sendInternalTransactionDto) {
        var internalTransaction = new InternalTransaction(UUID.randomUUID(),
                sendInternalTransactionDto.getAccountHolderId(),
                sendInternalTransactionDto.getSourceId(),
                sendInternalTransactionDto.getDestinationId(),
                sendInternalTransactionDto.getAmount(),
                sendInternalTransactionDto.getCurrency(),
                sendInternalTransactionDto.getFee(),
                sendInternalTransactionDto.getDescription(),
                sendInternalTransactionDto.getTransactionDate(),
                sendInternalTransactionDto.getType(),
                null,
                null,
                null,
                null,
                0
        );
        internalTransactionRepository.save(internalTransaction);
    }
}
