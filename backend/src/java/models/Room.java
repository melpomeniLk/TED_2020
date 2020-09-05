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
public class Room {

    private long id;
    private long hostId;
    private long idLocation;
    private String description;
    private String transportationDescription;
    private String roomType;
    private long maxNumberOfRenters;
    private long costPerDay;
    private long numberOfBeds;
    private long numberOfBedrooms;
    private long numberOfBathrooms;
    private long squareFootage;
    private long minDaysToRent;
    private boolean hasLivingRoom;
    private boolean wifi;
    private boolean cooling;
    private boolean heat;
    private boolean kitchen;
    private boolean tv;
    private boolean parking;
    private boolean lift;
    private boolean smoking;
    private boolean pets;
    private boolean happenings;


    public Room() {

    }

    public Room(long id, long hostId, long idLocation, String description, String transportationDescription, String roomType, long maxNumberOfRenters, long costPerDay, long numberOfBeds, long numberOfBedrooms, long numberOfBathrooms, long squareFootage, long minDaysToRent, boolean hasLivingRoom, boolean wifi, boolean cooling, boolean heat, boolean kitchen, boolean tv, boolean parking, boolean lift, boolean smoking, boolean pets, boolean happenings) {
        this.id = id;
        this.hostId = hostId;
        this.idLocation = idLocation;
        this.description = description;
        this.transportationDescription = transportationDescription;
        this.roomType = roomType;
        this.maxNumberOfRenters = maxNumberOfRenters;
        this.costPerDay = costPerDay;
        this.numberOfBeds = numberOfBeds;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.squareFootage = squareFootage;
        this.minDaysToRent = minDaysToRent;
        this.hasLivingRoom = hasLivingRoom;
        this.wifi = wifi;
        this.cooling = cooling;
        this.heat = heat;
        this.kitchen = kitchen;
        this.tv = tv;
        this.parking = parking;
        this.lift = lift;
        this.smoking = smoking;
        this.pets = pets;
        this.happenings = happenings;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getHostId() {
        return hostId;
    }

    public void setHostId(long hostId) {
        this.hostId = hostId;
    }

    public long getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(long idLocation) {
        this.idLocation = idLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransportationDescription() {
        return transportationDescription;
    }

    public void setTransportationDescription(String transportationDescription) {
        this.transportationDescription = transportationDescription;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public long getMaxNumberOfRenters() {
        return maxNumberOfRenters;
    }

    public void setMaxNumberOfRenters(long maxNumberOfRenters) {
        this.maxNumberOfRenters = maxNumberOfRenters;
    }

    public long getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(long costPerDay) {
        this.costPerDay = costPerDay;
    }

    public long getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(long numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public long getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(long numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public long getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(long numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public long getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(long squareFootage) {
        this.squareFootage = squareFootage;
    }

    public long getMinDaysToRent() {
        return minDaysToRent;
    }

    public void setMinDaysToRent(long minDaysToRent) {
        this.minDaysToRent = minDaysToRent;
    }

    public boolean isHasLivingRoom() {
        return hasLivingRoom;
    }

    public void setHasLivingRoom(boolean hasLivingRoom) {
        this.hasLivingRoom = hasLivingRoom;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isCooling() {
        return cooling;
    }

    public void setCooling(boolean cooling) {
        this.cooling = cooling;
    }

    public boolean isHeat() {
        return heat;
    }

    public void setHeat(boolean heat) {
        this.heat = heat;
    }

    public boolean isKitchen() {
        return kitchen;
    }

    public void setKitchen(boolean kitchen) {
        this.kitchen = kitchen;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean isLift() {
        return lift;
    }

    public void setLift(boolean lift) {
        this.lift = lift;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public boolean isPets() {
        return pets;
    }

    public void setPets(boolean pets) {
        this.pets = pets;
    }

    public boolean isHappenings() {
        return happenings;
    }

    public void setHappenings(boolean happenings) {
        this.happenings = happenings;
    }

    
    
    
}
