package com.gruporyc.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * ItemResponseTO: Data transformation object for json transformation of Item object
 * @author jmunoz
 * @since 31/07/2019
 * @version 1.0.0
 */
public class ItemResponseDTO {
    private String id;

    @NotNull(message = "is required")
    @Min(value = 3, message = "must be greater than or equal to 3")
    @Max(value = 512, message = "must be less than or equal to 512")
    private String name;
    private String description;
    @NotNull(message = "is required")
    private BigDecimal price;
    @NotNull(message = "is required")
    @Min(value = 1, message = "must be greater than or equal to 1")
    private Long quantity;
    private BigDecimal total;
    private String status;
    private String createDate;
    private String updateDate;

    /**
     * @return the Order ItemDTO's universal identifier
     */
    @JsonProperty("product_id")
    public String getId() {
        return id;
    }

    /**
     * @param id the item's universal identifier
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the Order ItemDTO's commercial name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * @param name the item's universal name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the Order ItemDTO's commercial description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * @param description the item's commercial description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the Order ItemDTO's price
     */
    @JsonProperty("price")
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the item's price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

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
     * @return the Order Item's total
     */
    @JsonProperty("total")
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the order item's total
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return the Order status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     * @param status the order status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the created date
     */
    @JsonProperty("created_date")
    public String getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the created date
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the updated date
     */
    @JsonProperty("updated_date")
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate the updated date
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "ItemResponseDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", total=" + total +
                ", status='" + status + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }
}
