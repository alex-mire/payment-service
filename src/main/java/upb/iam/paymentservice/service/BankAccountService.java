package upb.iam.paymentservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import upb.iam.paymentservice.domain.bankaccount.BankAccount;
import upb.iam.paymentservice.domain.bankaccount.BankAccountRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public void updateBankAccount(BankAccount bankAccount, BigDecimal amount) {
        bankAccountRepository.update(bankAccount.id(), amount);
    }
}
