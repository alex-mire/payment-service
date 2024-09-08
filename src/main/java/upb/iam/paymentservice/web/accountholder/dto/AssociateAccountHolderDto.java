package upb.iam.paymentservice.web.accountholder.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class AssociateAccountHolderDto {
    private String userId;
    private String accountHolderId;
}
