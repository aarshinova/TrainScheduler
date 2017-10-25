package org.annainc.trainscheduler.domain;

import java.time.LocalTime;

/**
 * Station object.
 */
public class Station {
    /**
     * Train arrival time
     */
    LocalTime timeArrival;
    /**
     * Train departure time
     */
    LocalTime timeDeparture;
    /**
     * Station name where train stops
     */
    String stationName;
    /**
     * Station type : as departure, origin or intermediate station
     */
    StationType stationType;

    public Station(LocalTime timeArrival, LocalTime timeDeparture, String stationName, StationType stationType) {
        this.timeArrival = timeArrival;
        this.timeDeparture = timeDeparture;
        this.stationName = stationName;
        this.stationType = stationType;
    }

    public LocalTime getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(LocalTime timeArrival) {
        this.timeArrival = timeArrival;
    }

    public LocalTime getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(LocalTime timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public StationType getStationType() {
        return stationType;
    }

    public void setStationType(StationType stationType) {
        this.stationType = stationType;
    }
}
