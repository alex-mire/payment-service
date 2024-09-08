package upb.iam.paymentservice.web.accountholder.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AccountHolderDto {
    String firstName;
    String lastName;
    LocalDate birthDate;
}
