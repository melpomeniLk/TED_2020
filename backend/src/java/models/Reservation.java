/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Timestamp;

/**
 *
 * @author alicemts
 */
public class Reservation {
    private long idReservation;
    private long userId, roomId;
    private Timestamp fromDate, toDate;
    
    
    public Reservation() {
    }

    public Reservation(long idReservation, long userId, long roomId, Timestamp fromDate, Timestamp toDate) {
        this.idReservation = idReservation;
        this.userId = userId;
        this.roomId = roomId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(long idReservation) {
        this.idReservation = idReservation;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public Timestamp getFromDate() {
        return fromDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    public Timestamp getToDate() {
        return toDate;
    }

    public void setToDate(Timestamp toDate) {
        this.toDate = toDate;
    }

    
}
