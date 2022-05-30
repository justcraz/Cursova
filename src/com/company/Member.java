package com.company;

public class Member extends Passenger {

    private final int yearsOfMembership;

    public Member(int yom, int a, String n) {
        super(a, n);
        yearsOfMembership = yom;

    }

    @Override
    public double applyDiscount(double p) {
        if (yearsOfMembership > 5) {
            p = p / 2;
            return p;
        } else if (yearsOfMembership > 1) {
            p = (p *9)/10;
            return p;
        }
        return p;
    }

    @Override
    public String toString() {
        return String.format("%s, %d, %s, %d", "true", yearsOfMembership, getName(), getAge());
    }

    @Override
    public String viewFormat(){
        return String.format("%s, вік: %d, скільки років учасник: %d", getName(), getAge(), yearsOfMembership);
    }

}
