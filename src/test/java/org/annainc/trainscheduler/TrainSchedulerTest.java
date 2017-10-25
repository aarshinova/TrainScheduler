package org.annainc.trainscheduler;

import org.annainc.trainscheduler.domain.Station;
import org.annainc.trainscheduler.domain.StationType;
import org.annainc.trainscheduler.domain.TrainResponse;
import org.annainc.trainscheduler.domain.TrainSchedule;
import org.annainc.trainscheduler.utilities.InternalApplicationException;
import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
/**
 * Test cases for train scheduler
 */
public class TrainSchedulerTest {

    /**
     * The test case to check if it adds a new train and its schedule
     */
    @Test
    public void it_should_add_a_train() {
        // GIVEN
        TrainScheduler scheduler = new TrainScheduler();

        // WHEN
        List< Station > stations = new ArrayList<>();
        stations.add(new Station(null,  LocalTime.parse("11:30"), "Lyon", StationType.ORIGIN));
        stations.add(new Station(LocalTime.parse("14:00"), LocalTime.parse("14:05"), "Paris", StationType.INTERMEDIATE));
        stations.add(new Station(LocalTime.parse("15:00"), null, "London", StationType.DESTINATION ));

        List<DayOfWeek> days = new ArrayList<>();
        days.add(DayOfWeek.MONDAY);
        days.add(DayOfWeek.TUESDAY);
        days.add(DayOfWeek.WEDNESDAY);
        days.add(DayOfWeek.THURSDAY);
        days.add(DayOfWeek.FRIDAY);
        TrainSchedule schedule = new TrainSchedule(days);

        scheduler.addTrain("Eurostar", stations, schedule);

        // THEN
        List<TrainResponse> responses = scheduler.consultTrains(LocalDate.now(), LocalTime.parse("11:00"), "Paris", "London");
        Assert.assertEquals(responses.size(), 1);
        TrainResponse response = responses.get(0);
        Assert.assertEquals(response.getFrom(), "Paris");
        Assert.assertEquals(response.getTo(), "London");
    }

    /**
     * The test case to check if an exception in schedule work for current train schedule
     */
    @Test
    public void it_should_add_an_exception(){
        // GIVEN
        TrainScheduler scheduler = new TrainScheduler();

        // WHEN
        List< Station > stations = new ArrayList<>();
        stations.add(new Station(null,  LocalTime.parse("11:30"), "Lyon", StationType.ORIGIN));
        stations.add(new Station(LocalTime.parse("15:00"), null, "London", StationType.DESTINATION ));

        List<DayOfWeek> days = new ArrayList<>();
        days.add(DayOfWeek.MONDAY);
        days.add(DayOfWeek.TUESDAY);
        List<LocalDate> exceptions = new ArrayList<>();
        exceptions.add(LocalDate.of(2017, Month.DECEMBER, 25));
        TrainSchedule schedule = new TrainSchedule(days, exceptions);
        scheduler.addTrain("Eurostar", stations, schedule);

        // THEN
        List<TrainResponse> responses = scheduler.consultTrains(LocalDate.of(2017, Month.DECEMBER, 25), LocalTime.parse("11:00"), "Lyon", "London");
        Assert.assertEquals(responses.size(), 0);
    }

    /**
     *  Test case to check if it updates train's schedule
     */
    @Test
    public void it_should_update_schedule_for_train(){
        // GIVEN
        TrainScheduler scheduler = new TrainScheduler();

        // WHEN
        List< Station > stations = new ArrayList<>();
        stations.add(new Station(null,  LocalTime.parse("11:30"), "Lyon", StationType.ORIGIN));
        stations.add(new Station(LocalTime.parse("15:00"), null, "London", StationType.DESTINATION ));

        List<DayOfWeek> days = new ArrayList<>();
        days.add(DayOfWeek.MONDAY);
        TrainSchedule schedule = new TrainSchedule(days);
        scheduler.addTrain("Eurostar", stations, schedule);

        List<DayOfWeek> updateDaysList = new ArrayList<>();
        updateDaysList.add(DayOfWeek.TUESDAY);
        updateDaysList.add(DayOfWeek.WEDNESDAY);
        try {
            scheduler.updateTrainSchedule(0, new TrainSchedule(updateDaysList));
        } catch (InternalApplicationException e) {
            e.printStackTrace();
        }

        // THEN
        List<TrainResponse> responses = scheduler.consultTrains(LocalDate.of(2017, Month.OCTOBER, 24), LocalTime.parse("11:00"), "Lyon", "London");
        Assert.assertEquals(responses.size(), 1);

    }

}