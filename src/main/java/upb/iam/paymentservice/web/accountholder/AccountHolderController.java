package upb.iam.paymentservice.web.accountholder;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upb.iam.paymentservice.domain.accountholder.AccountHolderRepository;
import upb.iam.paymentservice.domain.accountholderuser.AccountHolderUser;
import upb.iam.paymentservice.domain.accountholderuser.AccountHolderUserRepository;
import upb.iam.paymentservice.domain.shared.BadRequestException;
import upb.iam.paymentservice.web.accountholder.dto.AccountHolderDto;
import upb.iam.paymentservice.web.accountholder.dto.AccountHolderMinimalDto;
import upb.iam.paymentservice.web.accountholder.dto.AssociateAccountHolderDto;
import upb.iam.paymentservice.web.accountholder.dto.UserAccountHolderDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account-holder")
@Transactional
public class AccountHolderController {
    private final AccountHolderRepository accountHolderRepository;
    private final AccountHolderUserRepository accountHolderUserRepository;

    @GetMapping
    public AccountHolderDto getAccountHolder(Authentication authentication) {
        return accountHolderRepository.findAccountHolderDtoByUser(authentication.getPrincipal().toString())
                .orElseThrow(() -> new BadRequestException("Account Holder not found"));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('CLIENT')")
    public List<AccountHolderMinimalDto> getAllAccountHolders() {
        SecurityContextHolder.getContext();
        return accountHolderRepository.findAllMinimal();
    }

    @PostMapping("/associate")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<String> associateAccountHolder(@RequestBody AssociateAccountHolderDto dto) {
        if (accountHolderUserRepository.existsByUserId(UUID.fromString(dto.getUserId()))) {
            return ResponseEntity.badRequest().body("User already associated with an account");
        }
        if (!accountHolderRepository.existsById(UUID.fromString(dto.getAccountHolderId()))) {
            return ResponseEntity.badRequest().body("Account holder not found");
        }
        accountHolderUserRepository.save(new AccountHolderUser(UUID.randomUUID(),
                UUID.fromString(dto.getUserId()),
                UUID.fromString(dto.getAccountHolderId()),
                null,
                null,
                null,
                null,
                0));
        return ResponseEntity.ok("Account holder associated successfully");
    }

    @PostMapping("/deassociate")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<String> deassociateAccountHolder(@RequestBody AssociateAccountHolderDto dto) {
        if (!accountHolderUserRepository.existsByUserIdAndAccountHolderId(UUID.fromString(dto.getUserId()), UUID.fromString(dto.getAccountHolderId()))) {
            return ResponseEntity.badRequest().body("Association does not exist");
        }
        accountHolderUserRepository.deleteAccountHolder(UUID.fromString(dto.getUserId()), UUID.fromString(dto.getAccountHolderId()));
        return ResponseEntity.ok("Account holder association deleted successfully");
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<UserAccountHolderDto> getByUserId(@PathVariable String userId) {
        if (!accountHolderUserRepository.existsByUserId(UUID.fromString(userId))) {
            throw new BadRequestException("User does not exist");
        }

        var accHolder = accountHolderUserRepository.findByUserId(UUID.fromString(userId));
        if (accHolder.isEmpty()) {
            throw new BadRequestException("User does not exist");
        }
        return ResponseEntity.ok(accHolder.get());
    }
}
