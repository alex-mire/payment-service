package upb.iam.paymentservice.domain.accountholderaddress;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import upb.iam.paymentservice.domain.accountholder.AccountHolder;
import upb.iam.paymentservice.web.accountholderaddress.dto.AccountHolderAddressDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountHolderAddressRepository extends CrudRepository<AccountHolderAddress, UUID> {
    @Query("""
            select
            aha.country,
            aha.county,
            aha.city,
            aha.postal_code,
            aha.street,
            aha.number,
            aha.building,
            aha.floor,
            aha.apartment_number
            from payment_service.account_holder_address aha
            where aha.account_holder_id = :accountHolderId
            """)
    List<AccountHolderAddressDto> findAllByAccountHolderId(UUID accountHolderId);

}
