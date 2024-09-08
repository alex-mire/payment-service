package upb.iam.paymentservice.domain.accountholder;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import upb.iam.paymentservice.web.accountholder.dto.AccountHolderDto;
import upb.iam.paymentservice.web.accountholder.dto.AccountHolderMinimalDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountHolderRepository extends CrudRepository<AccountHolder, UUID> {
    @Query("""
           select
           ah.first_name,
           ah.last_name,
           ah.birth_date
           from payment_service.account_holder ah
           join payment_service.account_holder_user ahu on ahu.account_holder_id = ah.id
           where ahu.user_id = :userId
           """)
    Optional<AccountHolderDto> findAccountHolderDtoByUser(String userId);

    @Query("""
           select
           ah.id,
           ah.first_name,
           ah.last_name
           from payment_service.account_holder ah
           """)
    List<AccountHolderMinimalDto> findAllMinimal();

}
