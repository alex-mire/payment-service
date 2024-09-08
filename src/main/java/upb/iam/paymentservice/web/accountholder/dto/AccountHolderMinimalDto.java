package upb.iam.paymentservice.web.accountholder.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AccountHolderMinimalDto {
    private UUID id;
    private String firstName;
    private String lastName;
}
