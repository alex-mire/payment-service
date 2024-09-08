package upb.iam.paymentservice.web.accountholderaddress.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountHolderAddressDto {
    String country;
    String county;
    String city;
    String postalCode;
    String street;
    String number;
    String building;
    String floor;
    String apartmentNumber;
}
