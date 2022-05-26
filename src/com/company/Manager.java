package com.company;

import java.util.*;
import java.util.Scanner;


public class Manager {

    List<Flight> flights;
    List<Ticket> tickets;
    public static final String PATH="flight.txt";

    public Manager() {
        flights = new ArrayList<Flight>();
        tickets = new ArrayList<Ticket>();

    }


    public void displayAvailableFlights(String origin, String destination) {
        int size, seats;
        boolean flag = true;
        String o, d;
        size = flights.size();
        for (int i = 0; i < size; i++) {
            o = flights.get(i).getOrigin().toLowerCase(Locale.ROOT);
            d = flights.get(i).getDestination().toLowerCase(Locale.ROOT);
            seats = flights.get(i).getNumberOfSeatsleft();
            if ((o.equals(origin)) && (d.equals(destination)) && seats > 0) {
                if (flag) {
                    System.out.println("Список доступних рейсів:");
                    flag = false;
                }
                System.out.println(flights.get(i));
            }
        }
        if (flag) {
            System.out.println("Немає доступних рейсів");
        }



    }

    public Flight getFlight(int flightNumber) {
        int size = flights.size();
        for (Flight flight : flights) {
            if (flight.getFlightNumber() == flightNumber) {
                return flight;
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
        if (flag && (seats > 0)) {
            flights.get(index).bookASeat();
            price = p.applyDiscount(flights.get(index).getPrice());
            Ticket a = new Ticket(flights.get(index), p, price);
            tickets.add(a);
            System.out.println("Ви успішно забронювали місце на рейс " + flightNumber);
            System.out.println("Квиток: " + a);



        } else {
            if (flag && seats == 0) {
                System.out.println("Політ " + flightNumber + " Ви не можете забронювати квиток на цей рейс");

            } else if (!flag) {
                System.out.println("Політ " + flightNumber + " не існує");
            }
        }

    }

    private static Flight stringToClass(String str) {
        String[] a = str.split(", ");
        return new Flight(Integer.parseInt(a[0]), a[1], a[2], a[3], Integer.parseInt(a[4]), Integer.parseInt(a[5]));
    }

    private static List<Flight> convertArray(String[] str) {
        List<Flight> flights = new ArrayList<>();
        for (String word: str) {
            flights.add(stringToClass(word));
        }
        return flights;
    }

    public static void main(String[] args) {
        Manager M = new Manager();
        M.flights = convertArray(FileWork.readFromFile(PATH));  //Вибирає з файла
        Flight F;
        Passenger P;
        Scanner scan1 = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        String input, destination, origin, name;
        int FN, age, years;
        boolean flag = false;

        while (flag != true) {
            System.out.println("Введіть 1, якщо ви хочете відобразити всі рейси ");
            System.out.println("Введіть 2, якщо ви хочете отримати інформацію про рейс ");
            System.out.println("Введіть 3, якщо ви хочете забронювати місце ");
            System.out.println("Введіть 4, якщо ви хочете завершити програму ");
            input = scan1.nextLine();
            switch (input) {
                case "1":
                    for (Flight flight: M.flights){ //Проходить по всім обєктам
                        System.out.println(flight); //Виводить все на екран
                    }
                    break;
                case "2":
                    System.out.println("Введіть номер рейсу");
                    FN = scan2.nextInt();
                    F = M.getFlight(FN);
                    if (F == null) {
                        System.out.println("Рейсу " + FN + " не існує");
                    } else {
                        System.out.println("Інформація про рейс " + FN + ":");
                        System.out.println(F);
                    }
                    break;
                case "3":
                    System.out.println("Якщо пасажир не є учасником, введіть 1, якщо пасажир є учасником, введіть 2");
                    input = scan1.nextLine();
                    System.out.println("Введіть вік пасажира");
                    age = scan2.nextInt();
                    System.out.println("Введіть ім'я пасажира");
                    name = scan1.nextLine();
                    System.out.println("Введіть номер рейсу");
                    FN = scan2.nextInt();

                    if (input.equals("1")) {
                        P = new NonMember(age, name);
                        M.bookSeat(FN, P);
                    } else if (input.equals("2")) {
                        System.out.println("Скільки років пасажир є учасником");
                        years = scan2.nextInt();
                        P = new Member(years, age, name);
                        M.bookSeat(FN, P);

                    }
                    break;
                case "4":
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
