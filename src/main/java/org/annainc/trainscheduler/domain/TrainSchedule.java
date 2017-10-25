package org.annainc.trainscheduler.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
/**
 * Train schedule class
 */
public class TrainSchedule {
    /**
     * The days of week when a train runs
     * */
    List<DayOfWeek> daysOfWeek;

    /**
     * The exception dates like "25/12/2017"
     */
    List<LocalDate> exceptions;

    public TrainSchedule(List<DayOfWeek> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public TrainSchedule(List<DayOfWeek> daysOfWeek, List<LocalDate> exceptions) {

        this.daysOfWeek = daysOfWeek;
        this.exceptions = exceptions;
    }

    public List<DayOfWeek> getDaysOfWeek() {
        return daysOfWeek;

    }

    public void setDaysOfWeek(List<DayOfWeek> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public List<LocalDate> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<LocalDate> exceptions) {
        this.exceptions = exceptions;
    }

    /**
     * Checks if train runs the given date
     * @param date
     * @return true if train runs, false if not
     */
    public boolean doesMatch(LocalDate date) {
        if (exceptions != null && exceptions.contains(date))
            return false;

        for (DayOfWeek day : daysOfWeek) {
            if (day == date.getDayOfWeek()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new exception for train's schedule
     * @param newDate
     */
    public void addNewException(LocalDate newDate) {
        this.getExceptions().add(newDate);
    }

    /**
     * Adds a new week day to train's schedule
     * @param day
     */
    public void addNewDayOfWeek(DayOfWeek day) {
        this.getDaysOfWeek().add(day);
    }

}
