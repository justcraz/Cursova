package com.company;

public class NonMember extends Passenger {

    public NonMember(int a, String n) {
        super(a, n);
    }

    @Override
    public double applyDiscount(double p) {
        if (age > 65) {
            p = (p *9)/10;
            return p;
        }
        return p;
    }

    @Override
    public String toString() {
        return String.format("%s, %d, %s, %d", "false", 0, getName(), getAge());
    }

    @Override
    public String viewFormat(){
        return String.format("%s, вік: %d, не є учасником", getName(), getAge());
    }
}
