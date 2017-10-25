package org.annainc.trainscheduler.store;

import org.annainc.trainscheduler.domain.*;
import org.annainc.trainscheduler.utilities.InternalApplicationException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * Class "Repository" that is needed to talk to database layer
 * */
public class TrainDetailStore {
    final HashMap<Integer, TrainDetail> listTrainDetails = new HashMap<>();
    private Integer nextId = 0;

    /**
     * Method to add a new train
     * @param newTrain
     */
    public void addNewTrain(TrainDetail newTrain) {
        listTrainDetails.put(nextId++, newTrain);
    }

    /**
     *  Method to get train schedule details, given following parameters
     * @param date Date of departure
     * @param departure Time of departure
     * @param origin Place of departure
     * @param destination Travel destination
     * @return the list of trains corresponding the input parameters
     */
    public List<TrainResponse> getListTrainDetails(LocalDate date, LocalTime departure, String origin, String destination) {
        List<TrainResponse> result = new ArrayList<>();
        for (TrainDetail train : listTrainDetails.values()) {
            if (isThereIsTrainThisDay(train, date)) {
                LocalTime realDeparture = getRealDepTimeForDestAndOrigin(train, origin, destination, departure);
                if (realDeparture != null) {
                    result.add(new TrainResponse(date, departure, origin, destination));
                }
            }
        }
        return result;
    }

    /**
     *  Method to get real time of train departure for departure and destination requested by the user
     * @param train
     * @param origin train's departure place
     * @param destination train's destination
     * @param departure time of departure
     * @return real departure time of train
     */
    private LocalTime getRealDepTimeForDestAndOrigin(TrainDetail train, String origin, String destination, LocalTime departure) {
        List<Station> stations = train.getStations();
        LocalTime realDeparture = null;
        boolean originFound = false;
        boolean destinationFound = false;
        for (Station station : stations) {
            if (station.getStationName().equals(origin) && (station.getStationType().equals(StationType.ORIGIN) ||
                    station.getStationType().equals(StationType.INTERMEDIATE))) {
                if (station.getTimeDeparture().isAfter(departure)) {
                    originFound = true;
                    realDeparture = station.getTimeDeparture();
                }
            }
            if (station.getStationName().equals(destination) && (station.getStationType().equals(StationType.DESTINATION) ||
                    station.getStationType().equals(StationType.INTERMEDIATE))) {
                destinationFound = true;
            }
        }
        return (originFound && destinationFound) ? realDeparture : null;
    }

    /**
     * Method to check if there is a train for the requested date
     * @param train
     * @param date
     * @return true or false
     */
    private boolean isThereIsTrainThisDay(TrainDetail train, LocalDate date) {
        return train.getSchedule().doesMatch(date);
    }

    /**
     * Method to update train schedule
     * @param id
     * @param schedule
     * @throws InternalApplicationException if trainId doesn'e exist
     */
    public void updateTrainSchedule(int id, TrainSchedule schedule) throws InternalApplicationException {
        TrainDetail train = listTrainDetails.get(id);
        if (train == null) throw new InternalApplicationException("Internal application problem.");

        TrainSchedule sc = train.getSchedule();
        if (schedule.getDaysOfWeek() != null) {
            for (DayOfWeek day : schedule.getDaysOfWeek()) {
                sc.addNewDayOfWeek(day);
            }
        }

        if (schedule.getExceptions() != null) {
            for (LocalDate date : schedule.getExceptions()) {
                sc.addNewException(date);
            }
        }

    }
}
