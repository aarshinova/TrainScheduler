package org.annainc.trainscheduler.domain;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Class to show train information in simple format
 */
public class TrainResponse {
    /**
     * From : departure
     */
    String from;
    /**
     * To : destination
     */
    String to;
    /**
     * Date of departure
     */
    LocalDate dateDeparture;
    /**
     * Time of departure
     */
    LocalTime timeDeparture;

    public TrainResponse(LocalDate date, LocalTime departure, String origin, String destination) {
        this.from = origin;
        this.to = destination;
        this.dateDeparture = date;
        this.timeDeparture = departure;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDate getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(LocalDate dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public LocalTime getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(LocalTime timeDeparture) {
        this.timeDeparture = timeDeparture;
    }
}
