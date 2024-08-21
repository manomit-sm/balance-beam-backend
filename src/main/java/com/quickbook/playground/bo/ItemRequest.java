package com.quickbook.playground.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequest {

    @JsonProperty("TrackQtyOnHand")
    @JsonPropertyDescription("Only for type Inventory")
    private boolean TrackQtyOnHand;

    @JsonProperty("Name")
    private String Name;

    @JsonProperty("QtyOnHand")
    private Integer QtyOnHand;

    @JsonProperty("IncomeAccountRef")
    private ItemAccountRef IncomeAccountRef;


    @JsonProperty("AssetAccountRef")
    @JsonPropertyDescription("Only for type Inventory")
    private ItemAccountRef AssetAccountRef;

    @JsonProperty("InvStartDate")
    @JsonPropertyDescription("Only for type Inventory")
    private String InvStartDate;

    @JsonProperty("Type")
    private String Type;

    @JsonProperty("ExpenseAccountRef")
    private ItemAccountRef ExpenseAccountRef;

    @JsonProperty("UnitPrice")
    @JsonPropertyDescription("Only for type Inventory")
    private Double UnitPrice;

    @JsonProperty("PurchaseCost")
    @JsonPropertyDescription("Only for type Inventory")
    private Double PurchaseCost;

    @JsonProperty("PurchaseDesc")
    private String PurchaseDesc;

    @JsonProperty("Id")
    @JsonPropertyDescription("Only for update item")
    private String Id;

    @JsonProperty("SyncToken")
    @JsonPropertyDescription("Only for update item")
    private String SyncToken;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemAccountRef {
        private String name;
        private String value;
    }
}
