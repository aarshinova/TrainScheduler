package org.annainc.trainscheduler;

import org.annainc.trainscheduler.domain.Station;
import org.annainc.trainscheduler.domain.TrainDetail;
import org.annainc.trainscheduler.domain.TrainResponse;
import org.annainc.trainscheduler.domain.TrainSchedule;
import org.annainc.trainscheduler.utilities.InternalApplicationException;
import org.annainc.trainscheduler.store.TrainDetailStore;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
/**
 * Controller class that creates a link between front layer (front-end) and back-end layer
 */
public class TrainScheduler {

    // Repository
    final TrainDetailStore trainDetailStore = new TrainDetailStore();

    /**
     * Method to create a new Train
     * @param description
     * @param stations
     * @param schedule
     */
    void addTrain(String description, List<Station> stations, TrainSchedule schedule) {
        TrainDetail train = new TrainDetail(description, stations, schedule);
        trainDetailStore.addNewTrain(train);
    }

    /** Consult the list of trains given the input of :
     * @param date
     * @param departure
     * @param from
     * @param to
     * @return list of trains and its schedules formatted in the way convenient to process by front end*/
    List<TrainResponse> consultTrains(LocalDate date, LocalTime departure, String from, String to) {
        return trainDetailStore.getListTrainDetails(date, departure, from, to);
    }
    /** Update train schedule
     * @param trainId
     * @param schedule
     * @throws InternalApplicationException if train doesn't exist*/
    public void updateTrainSchedule(int trainId, TrainSchedule schedule) throws InternalApplicationException {
        trainDetailStore.updateTrainSchedule(trainId, schedule);
    }

    // TODO : full update of train description, stations and schedule
    void updateTrainSchedule(int trainId, String description, List<Station> stations, TrainSchedule schedule){

    }

    // TODO : update of train stations
    void updateTrainStations(int trainId, List<Station> stations){

    }

}
