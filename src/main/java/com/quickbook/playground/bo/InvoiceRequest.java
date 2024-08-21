package com.quickbook.playground.bo;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRequest {

    @JsonProperty("CustomerRef")
    private CustomerRef CustomerRef;

    @JsonProperty("Line")
    private List<InvoiceLine> Line;

    @JsonProperty("EmailStatus")
    private String EmailStatus;

    @JsonProperty("BillEmail")
    @JsonPropertyDescription("When EmailStatus is set NeedToSend")
    private BillEmail BillEmail;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BillEmail {
        @JsonProperty("Address")
        private String Address;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CustomerRef {
        private String value;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InvoiceLine {
        @JsonProperty("DetailType")
        private String DetailType;

        @JsonProperty("Amount")
        private Double Amount;

        @JsonProperty("SalesItemLineDetail")
        private InvoiceSalesItemLineDetail SalesItemLineDetail;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class InvoiceSalesItemLineDetail {

            @JsonProperty("ItemRef")
            private InvoiceItemRef ItemRef;

            @JsonProperty("Qty")
            @JsonPropertyDescription("For Inventory Item")
            private Integer Qty;

            @Data
            @NoArgsConstructor
            @AllArgsConstructor
            public static class InvoiceItemRef {
                private String name;
                private String value;
            }
        }
    }
}
