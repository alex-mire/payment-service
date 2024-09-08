package upb.iam.paymentservice.web.bankaccount;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upb.iam.paymentservice.domain.bankaccount.BankAccountRepository;
import upb.iam.paymentservice.domain.shared.BadRequestException;
import upb.iam.paymentservice.web.bankaccount.dto.BankAccountDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank-account")
@Transactional
public class BankAccountController {
    private final BankAccountRepository bankAccountRepository;

    @GetMapping("/{accountHolderId}")
    public List<BankAccountDto> getAllBankAccounts(@PathVariable UUID accountHolderId, Authentication authentication) {
        return bankAccountRepository.findAllBankAccountByAccountHolderId(accountHolderId);
    }
}
