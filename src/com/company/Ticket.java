package com.company;

public class Ticket {

    private Flight flight;
    private Passenger passenger;
    private double price;
    private int number;
    public static int count = 0;

    public Ticket(Flight f, Passenger pa, double pr) {
        count++;
        flight = f;
        passenger = pa;
        price = pr;
        number = count;
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %d, %d", getFlight().getFlightNumber(), getPassenger(), (int)getPrice(), getNumber());
    }

    public String viewFormat(){
        return "Номер польоту: " + getFlight().getFlightNumber() + ", " +
                "Пасажир: " + getPassenger().viewFormat() + ", " +
                "ціна квитка: " + getPrice() + ", " +
                "номер квитка: " + getCount();
    }

    public Ticket(Flight flight, Passenger passenger, double price, int number) {
        this.flight = flight;
        this.passenger = passenger;
        this.price = price;
        this.number = number;
    }

    public Flight getFlight() {
        return flight;
    }
    public void setFlight(Flight F) {
        flight=F;
    }
    public Passenger getPassenger() {
        return passenger;
    }
    public void setPassenger(Passenger P) {
        passenger=P;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double P) {
        price=P;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int n) {
        number=n;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int n) {
        count=n;
    }
}
