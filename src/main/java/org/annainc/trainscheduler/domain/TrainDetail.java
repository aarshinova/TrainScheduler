package org.annainc.trainscheduler.domain;

import java.util.List;

/**
 * Class that describes a train object
 */
public class TrainDetail {
    /**
     * The stations where train stops
     */
    List<Station> stations;
    /**
     * Description of a train
     */
    String trainDescription;
    /**
     * Train's schedule
     */
    TrainSchedule schedule;

    public TrainDetail(String trainDescription, List<Station> stations, TrainSchedule schedule) {
        this.stations = stations;
        this.trainDescription = trainDescription;
        this.schedule = schedule;
    }

    public List<Station> getStations() {
        return stations;

    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public String getTrainDescription() {
        return trainDescription;
    }

    public void setTrainDescription(String trainDescription) {
        this.trainDescription = trainDescription;
    }

    public TrainSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(TrainSchedule schedule) {
        this.schedule = schedule;
    }
}
