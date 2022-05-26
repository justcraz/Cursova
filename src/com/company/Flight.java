package com.company;

public class Flight {

    private final int flightNumber;
    private final String origin;
    private final String destination;
    private final String departureTime;
    private int numberOfSeatsleft;
    private final double originalPrice;

    public Flight(int fn, String o, String ds, String dp, int c, double op) {
        if (ds.equals(o)) {
            throw new IllegalArgumentException("Пункт призначення та походження однакові");
        }
        flightNumber = fn;

        origin = o;
        destination = ds;
        departureTime = dp;
        numberOfSeatsleft = c;
        originalPrice = op;
    }

    public void bookASeat() {
        if (numberOfSeatsleft > 0) {
            numberOfSeatsleft = numberOfSeatsleft - 1;

        }
    }

    public double getPrice() {
        return originalPrice;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public int getNumberOfSeatsleft() {
        return numberOfSeatsleft;
    }

//    public int getCapacity() {
//        return capacity;
//    }
//
//    public void setPrice(double p) {
//        originalPrice = p;
//    }
//
//    public void setOrigin(String o) {
//        origin = o;
//    }
//
//    public void setDestination(String d) {
//        destination = d;
//    }
//
//    public void setDepartureTime(String d) {
//        departureTime = d;
//    }
//
//    public void setFlightNumber(int fn) {
//        flightNumber = fn;
//    }
//
//    public void setNumberOfSeatsleft(int s) {
//        numberOfSeatsleft = s;
//    }
//
//    public void setCapacity(int c) {
//        capacity = c;
//    }

    @Override
    public String toString() {
        String one = "Квиток " + "№" + flightNumber;
        String two = ", Початок рейсу: " + origin + " Прямує до: " + destination;
        String three = ", Час та дата відправлення: " + departureTime;
        String four = ", Ціна квитка: " + originalPrice + "$";
        return one + two + three + four;
    }

}
