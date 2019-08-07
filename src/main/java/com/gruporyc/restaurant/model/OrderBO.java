/*
package com.gruporyc.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gruporyc.restaurant.enums.Status;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

*/
/**
 * OrderDto: Business Order object
 * @author jmunoz
 * @since 31/07/2019
 * @version 1.0.0
 *//*

public class OrderBO {
    private Long id;
    private BigDecimal total;
    private Status status;
    @NotNull(message = "is required")
    private Long customerId;
    @NotNull(message = "is required")
    private List<OrderItemDTO> items;
    private CustomerDTO customer;

    */
/**
     * @return the Order's universal identifier
     *//*

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    */
/**
     * @param id the order universal identifier
     *//*

    public void setId(Long id) {
        this.id = id;
    }

    */
/**
     * @return the Order's total amount
     *//*

    @JsonProperty("total")
    public BigDecimal getTotal() {
        return total;
    }

    */
/**
     * @param total the order total amount
     *//*

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    */
/**
     * @return the Order status
     *//*

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    */
/**
     * @param status the order status
     *//*

    public void setStatus(String status) {
        this.status = status;
    }

    */
/**
     * @return the Customer's universal identifier
     *//*

    @JsonProperty("customer_id")
    public Long getCustomerId() {
        return customerId;
    }

    */
/**
     * @param customerId the customer universal identifier
     *//*

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    */
/**
     * @return the list of order items
     *//*

    @JsonProperty("line_items")
    public List<OrderItemDTO> getItems() {
        return items;
    }

    */
/**
     * @param items the list of order items
     *//*

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    */
/**
     * @return the Customer data object
     *//*

    @JsonProperty("billing")
    public CustomerDTO getCustomer() {
        return customer;
    }

    */
/**
     * @param customer the customer object
     *//*

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
        this.customer.setId(this.customerId);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", total=" + total +
                ", status='" + status + '\'' +
                ", items='" + items + '\'' +
                ", customerId=" + customerId +
                '}';
    }
}

*/
