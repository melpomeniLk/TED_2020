/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author melpomeni_lk
 */
public class Review {
    private long id;
    private String review;
    private long reservationId;
    private int numberOfStars;

    public Review() {
    }

    public Review(long id, String review, long reservationId, int numberOfStars) {
        this.id = id;
        this.review = review;
        this.reservationId = reservationId;
        this.numberOfStars = numberOfStars;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(int numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    
    
}
