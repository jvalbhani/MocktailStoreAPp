package com.mocktails.mocktailstore.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PartyOrder implements Serializable {

    private String description;
    private String venue;
    private Date placedDate;
    private Date deliveryDate;
    private List<OrderMocktail> orderMocktails;
    private User placedBy;
    private short status;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PartyOrder [description=").append(description)
                .append(", venue=").append(venue).append(", placedDate=").append(placedDate).append(", deliveryDate=")
                .append(deliveryDate).append(", orderMocktails=").append(orderMocktails).append(", placedBy=")
                .append(placedBy).append("]");
        return builder.toString();
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public List<OrderMocktail> getOrderMocktails() {
        return orderMocktails;
    }

    public void setOrderMocktails(List<OrderMocktail> orderMocktails) {
        this.orderMocktails = orderMocktails;
    }

    public User getPlacedBy() {
        return placedBy;
    }

    public void setPlacedBy(User placedBy) {
        this.placedBy = placedBy;
    }

    public List<OrderMocktail> getMocktails() {
        return orderMocktails;
    }

    public void setMocktails(List<OrderMocktail> orderMocktails) {
        this.orderMocktails = orderMocktails;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Date getPlacedDate() {
        return placedDate;
    }

    public void setPlacedDate(Date placedDate) {
        this.placedDate = placedDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
