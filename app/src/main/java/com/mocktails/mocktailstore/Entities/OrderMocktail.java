package com.mocktails.mocktailstore.Entities;

import java.io.Serializable;

public class OrderMocktail implements Serializable {

    private Mocktail mocktail;
    private PartyOrder order;
    private int quantity;

    public PartyOrder getOrder() {
        return order;
    }

    public void setOrder(PartyOrder order) {
        this.order = order;
    }

    public OrderMocktail() {

    }

    public Mocktail getMocktail() {
        return mocktail;
    }

    public void setMocktail(Mocktail mocktail) {
        this.mocktail = mocktail;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("OrderMocktail [mocktail=").append(mocktail)
                .append(", order=").append(order).append(", quantity=").append(quantity).append("]");
        return builder.toString();
    }

}
