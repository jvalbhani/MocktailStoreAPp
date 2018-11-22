package com.mocktails.mocktailstore.Entities;

import java.io.Serializable;
/**
 *
 * @author jayesh
 */
public class Rating implements Serializable {

    private int id;
    private int star;
    private String comment;
    private boolean visible;
    private User ratedBy;

    public int getStar() {
        return star;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public User getRatedBy() {
        return ratedBy;
    }

    public void setRatedBy(User ratedBy) {
        this.ratedBy = ratedBy;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Rating [star=").append(star).append(", comment=")
                .append(comment).append(", visible=").append(visible).append(", ratedBy=").append(ratedBy).append("]");
        return builder.toString();
    }

}
