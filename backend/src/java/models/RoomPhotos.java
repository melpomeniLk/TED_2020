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
public class RoomPhotos {
    private long photoId;
    private long roomId;
    private boolean isMainPhoto;
    
    public RoomPhotos() {
    }

    public RoomPhotos(long photoId, long roomId, boolean isMainPhoto) {
        this.photoId = photoId;
        this.roomId = roomId;
        this.isMainPhoto = isMainPhoto;
    }

    public long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public boolean isIsMainPhoto() {
        return isMainPhoto;
    }

    public void setIsMainPhoto(boolean isMainPhoto) {
        this.isMainPhoto = isMainPhoto;
    }

    
    
}
