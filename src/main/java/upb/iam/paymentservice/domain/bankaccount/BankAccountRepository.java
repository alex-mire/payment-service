package upb.iam.paymentservice.domain.bankaccount;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import upb.iam.paymentservice.web.bankaccount.dto.BankAccountDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BankAccountRepository extends CrudRepository<BankAccount, UUID> {
    @Query("""
           select
           account_holder_id,
           account_number,
           type,
           currency,
           balance,
           interest_rate,
           open_date,
           close_date
           from payment_service.bank_account
           where account_holder_id = :accountHolderId
           """)
    List<BankAccountDto> findAllBankAccountByAccountHolderId(UUID accountHolderId);

    @Modifying
    @Query("""
           update payment_service.bank_account set balance = :amount where id = :id
           """)
    void update(UUID id, BigDecimal amount);
}
