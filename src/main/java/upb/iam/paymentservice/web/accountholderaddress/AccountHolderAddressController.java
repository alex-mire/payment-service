package upb.iam.paymentservice.web.accountholderaddress;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upb.iam.paymentservice.domain.accountholderaddress.AccountHolderAddressRepository;
import upb.iam.paymentservice.domain.shared.BadRequestException;
import upb.iam.paymentservice.web.accountholder.dto.AccountHolderDto;
import upb.iam.paymentservice.web.accountholderaddress.dto.AccountHolderAddressDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account-holder-address")
@Transactional
public class AccountHolderAddressController {
    private final AccountHolderAddressRepository accountHolderAddressRepository;

    @GetMapping("/{accountHolderId}")
    public List<AccountHolderAddressDto> getAccountHolderAddress(@PathVariable UUID accountHolderId, Authentication authentication) {
        return accountHolderAddressRepository.findAllByAccountHolderId(accountHolderId);
    }
}
