package com.quickbook.playground.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ItemResponse(
        @JsonProperty("Id") String Id,
        @JsonProperty("Name") String Name,
        @JsonProperty("TrackQtyOnHand") boolean TrackQtyOnHand,
        @JsonProperty("UnitPrice") Double UnitPrice,
        @JsonProperty("PurchaseCost") Double PurchaseCost,
        @JsonProperty("QtyOnHand") Double QtyOnHand,
        @JsonProperty("IncomeAccountRef") Object IncomeAccountRef,
        @JsonProperty("AssetAccountRef") Object AssetAccountRef,
        @JsonProperty("Active") boolean Active,
        @JsonProperty("SyncToken") String SyncToken,
        @JsonProperty("InvStartDate") String InvStartDate,
        @JsonProperty("Type") String Type,
        @JsonProperty("ExpenseAccountRef") Object ExpenseAccountRef
) {
}
