package com.gruporyc.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * OrderItemDTO: Data transformation object for json transformation of Order ItemDTO object
 * @author jmunoz
 * @since 31/07/2019
 * @version 1.0.0
 */
public class OrderItemDTO extends ItemDTO{
    @NotNull(message = "is required")
    @Min(value = 1, message = "must be greater than or equal to 1")
    private Long quantity;
    private BigDecimal subTotal;
//    @NotNull(message = "is required")
//    private ItemDTO item;

    /**
     * @return the Order Item's quantity
     */
    @JsonProperty("quantity")
    public Long getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the order item's quantity
     */
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the Order Item's subTotal
     */
    @JsonProperty("total")
    public BigDecimal getSubTotal() {
        return subTotal;
    }

    /**
     * @param subTotal the order item's subTotal
     */
    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

//    /**
//     * @return the Order Item's single item
//     */
//    @JsonProperty("item")
//    public ItemDTO getItem() {
//        return item;
//    }
//
//    /**
//     * @param item the order item's single item
//     */
//    public void setItem(ItemDTO item) {
//        this.item = item;
//    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                ", quantity=" + quantity +
                ", subTotal=" + subTotal +
//                ", item=" + item +
                '}';
    }
}
