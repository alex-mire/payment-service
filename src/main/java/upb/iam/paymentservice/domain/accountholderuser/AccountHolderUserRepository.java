package upb.iam.paymentservice.domain.accountholderuser;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import upb.iam.paymentservice.web.accountholder.dto.UserAccountHolderDto;

import java.util.Optional;
import java.util.UUID;

public interface AccountHolderUserRepository extends CrudRepository<AccountHolderUser, UUID> {
    boolean existsByUserId(UUID userId);
    boolean existsByUserIdAndAccountHolderId(UUID userId, UUID accountHolderId);

    @Modifying
    @Query("""
           delete from payment_service.account_holder_user
           where user_id = :userId and account_holder_id = :accountHolderId
           """)
    void deleteAccountHolder(UUID userId, UUID accountHolderId);

    @Query("""
           select ac.first_name || ' ' || ac.last_name as account_holder,
                  acu.account_holder_id
           from payment_service.account_holder_user acu
           join payment_service.account_holder ac on ac.id = acu.account_holder_id
           where user_id = :userId
           """)
    Optional<UserAccountHolderDto> findByUserId(UUID userId);
}
