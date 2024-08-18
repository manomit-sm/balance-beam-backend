package com.quickbook.playground.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String fullyQualifiedName;

    private PrimaryEmailAddr primaryEmailAddr;

    private String displayName;

    private String Suffix;

    private String Title;

    private String middleName;

    private String notes;

    private String familyName;

    private PrimaryPhone primaryPhone;

    private String companyName;

    private BillAddr billAddr;

    private String givenName;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PrimaryEmailAddr {

        private String address;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PrimaryPhone {

        private String freeFormNumber;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BillAddr {

        private String countrySubDivisionCode;

        private String city;

        private String postalCode;

        private String Line1;
        
        private String Country;
    }
}
