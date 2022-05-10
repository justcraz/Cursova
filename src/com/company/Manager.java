package com.company;

import java.util.*;
import java.util.Scanner;


public class Manager {

    List<Flight> flights;
    List<Ticket> tickets;

    public Manager() {
        flights = new ArrayList<Flight>();
        tickets = new ArrayList<Ticket>();

    }

    public void createFlights() {
        int FN, c;
        double op;
        String origin, destination, dt;
        Scanner scan1 = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        Scanner scan3 = new Scanner(System.in);
        System.out.println("Введіть номер рейсу");
        FN = scan1.nextInt();
        System.out.println("Введіть початок рейсу");
        origin = scan2.nextLine();
        System.out.println("Введіть пункт призначення рейсу");
        destination = scan2.nextLine();
        System.out.println("Введіть час і дату відправлення рейсу");
        dt = scan2.nextLine();
        System.out.println("Введіть пропускну здатність");
        c = scan1.nextInt();
        System.out.println("Введіть оригінальну ціну квитка");
        op = scan3.nextDouble();
        Flight F = new Flight(FN, origin, destination, dt, c, op);
        flights.add(F);
        System.out.println("Створено наступний рейс:");
        System.out.println(F);

    }

    public void displayAvailableFlights(String origin, String destination) {
        int size, seats;
        boolean flag = true;
        String o, d;
        size = flights.size();
        for (int i = 0; i < size; i++) {
            o = flights.get(i).getOrigin();
            d = flights.get(i).getDestination();
            seats = flights.get(i).getNumberOfSeatsleft();
            if ((o.equals(origin) == true) && (d.equals(destination) == true) && seats > 0) {
                if (flag == true) {
                    System.out.println("Список доступних рейсів:");
                    flag = false;
                }
                System.out.println(flights.get(i));
            }
        }
        if (flag == true) {
            System.out.println("Немає доступних рейсів");
        }



    }

    public Flight getFlight(int flightNumber) {
        int size = flights.size();
        for (int i = 0; i < size; i++) {
            if (flights.get(i).getFlightNumber() == flightNumber) {
                return flights.get(i);
            }

        }
        return null;
    }

    public void bookSeat(int flightNumber, Passenger p) {
        int size = flights.size();
        boolean flag = false;
        int index = 0, seats;
        for (int i = 0; i < size; i++) {
            if (flights.get(i).getFlightNumber() == flightNumber) {
                flag = true;
                index = i;
                break;
            }

        }
        double price;
        seats = flights.get(index).getNumberOfSeatsleft();
        if (flag == true && (seats > 0)) {
            flights.get(index).bookASeat();
            price = p.applyDiscount(flights.get(index).getPrice());
            Ticket a = new Ticket(flights.get(index), p, price);
            tickets.add(a);
            System.out.println("Ви успішно забронювали місце на рейс " + flightNumber);
            System.out.println("Квиток: " + a);



        } else {
            if (flag == true && seats == 0) {
                System.out.println("Політ " + flightNumber + " Ви не можете забронювати квиток на цей рейс");

            } else if (flag == false) {
                System.out.println("Політ " + flightNumber + " не існує");
            }
        }

    }

    public static void main(String[] args) {
        Manager M = new Manager();
        Flight F;
        Passenger P;
        Scanner scan1 = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        String input, destination, origin, name;
        int FN, age, years;
        boolean flag = false;

        while (flag != true) {
            System.out.println("Введіть c, якщо ви хочете створити рейс ");
            System.out.println("Введіть d, якщо ви хочете відобразити всі рейси ");
            System.out.println("Введіть g, якщо ви хочете отримати інформацію про рейс ");
            System.out.println("Введіть b, якщо ви хочете забронювати місце ");
            System.out.println("Введіть t, якщо ви хочете завершити програму ");
            input = scan1.nextLine();
            switch (input) {
                case "c":
                    M.createFlights();
                    break;
                case "d":
                    System.out.println("Введіть початок рейсу");
                    origin = scan1.nextLine();
                    System.out.println("Введіть пункт призначення рейсу");
                    destination = scan1.nextLine();
                    M.displayAvailableFlights(origin, destination);
                    break;
                case "g":
                    System.out.println("Введіть номер рейсу");
                    FN = scan2.nextInt();
                    F = M.getFlight(FN);
                    if (F == null) {
                        System.out.println("Політ " + FN + " не існує");
                    } else {
                        System.out.println("Інформація про рейс " + FN + ":");
                        System.out.println(F);
                    }
                    break;
                case "b":
                    System.out.println("Якщо пасажир не є учасником, введіть n, якщо пасажир є учасником, введіть m");
                    input = scan1.nextLine();
                    System.out.println("Введіть вік пасажирів");
                    age = scan2.nextInt();
                    System.out.println("Введіть ім'я пасажира");
                    name = scan1.nextLine();
                    System.out.println("Введіть номер рейсу");
                    FN = scan2.nextInt();

                    if (input.equals("n") == true) {
                        P = new NonMember(age, name);
                        M.bookSeat(FN, P);
                    } else if (input.equals("m") == true) {
                        System.out.println("Скільки років пасажир є учасником");
                        years = scan2.nextInt();
                        P = new Member(years, age, name);
                        M.bookSeat(FN, P);

                    }
                    break;
                case "t":
                    flag = true;
                    System.out.println("Програма припинена");
                    break;
                default:
                    System.out.println("Ви ввели недійсний параметр");
                    break;

            }

        }

    }
}