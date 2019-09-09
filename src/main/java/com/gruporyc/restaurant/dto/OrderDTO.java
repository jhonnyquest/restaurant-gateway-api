package com.gruporyc.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * OrderDto: Data transformation object for json transformation of Order object
 * @author jmunoz
 * @since 31/07/2019
 * @version 1.0.0
 */
public class OrderDTO {
    private String id;
    private BigDecimal total;
    private String status;
    @NotNull(message = "is required")
    private String customerId;
    private String table;
    @NotNull(message = "is required")
    private List<OrderItemDTO> items;
    private CustomerDTO customer;

    /**
     * @return the Order's universal identifier
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * @param id the order universal identifier
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the Order's total amount
     */
    @JsonProperty("total")
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the order total amount
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
     * @return the Customer's universal identifier
     */
    @JsonProperty("customer_id")
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customer universal identifier
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return Table that generate the order if customer is in site
     */
    @JsonProperty("table")
    public String getTable() {
        return table;
    }

    /**
     * @param table Table that generate the order if customer is in site
     */
    public void setTable(String table) {
        this.table = table;
    }

    /**
     * @return the list of order items
     */
    @JsonProperty("line_items")
    public List<OrderItemDTO> getItems() {
        return items;
    }

    /**
     * @param items the list of order items
     */
    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    /**
     * @return the Customer data object
     */
    @JsonProperty("billing")
    public CustomerDTO getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer object
     */
    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
        this.customer.setId(this.customerId);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id='" + id + '\'' +
                ", total=" + total +
                ", status='" + status + '\'' +
                ", customerId='" + customerId + '\'' +
                ", table='" + table + '\'' +
                ", items=" + items +
                ", customer=" + customer +
                '}';
    }
}
