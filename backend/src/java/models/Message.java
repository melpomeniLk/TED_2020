/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.sql.Timestamp;
/**
 *
 * @author melpomeni_lk
 */
public class Message {
    private long idMessage;
    private long fromUserId, toUserId;
    private Timestamp time;
    private String text;
    private boolean hasBeenRead;
    private long roomId;

    public Message() {
    }

    public Message(long idMessage, long fromUserId, long toUserId, Timestamp time, String text, boolean hasBeenRead, long roomId) {
        this.idMessage = idMessage;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.time = time;
        this.text = text;
        this.hasBeenRead = hasBeenRead;
        this.roomId = roomId;
    }

    public long getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(long idMessage) {
        this.idMessage = idMessage;
    }

    public long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public long getToUserId() {
        return toUserId;
    }

    public void setToUserId(long toUserId) {
        this.toUserId = toUserId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isHasBeenRead() {
        return hasBeenRead;
    }

    public void setHasBeenRead(boolean hasBeenRead) {
        this.hasBeenRead = hasBeenRead;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    
}
