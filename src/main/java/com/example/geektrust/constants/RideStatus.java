package com.example.geektrust.constants;

public enum RideStatus {
    RIDE_STARTED("RIDE_STARTED"),
    RIDE_STOPPED("RIDE_STOPPED"),
    RIDE_NOT_COMPLETED("RIDE_NOT_COMPLETED"),
    INVALID_RIDE("INVALID_RID");

    private final String status;

    RideStatus(String status) {
        this.status=status;
    }
}
