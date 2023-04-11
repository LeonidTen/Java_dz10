/*Добавить абстрактоное целочисленное поле инициатива. Создать новый список, добавить в него два ваших списка комманд. Отсортировать получившийся список по инициативе. 
Добавить абстрактное строковое поле "состояние" и сделать его значение равным "stand". Доработать степ лучника. При поиске крестьянина учитывать что его поле "состояние" ревно "stand". 
Если крстьянин найден стрел прибавить одну и у найденного крестьянина поле "состояние" заменить на "busy". 
В основноклассе (main) реализовать вывод в консоль здоровья бойцов обоих комманд, потом вызов метода степ у всех персонажей и вывод здоровья снова. 
Чтобы увидеть что степ сработал и лучники в когото стрельнули!) */

import java.util.*;

abstract class Personage implements Comparable<Personage> {
    protected int health;
    protected int initiative;
    protected String state = "stand";

    public Personage(int health, int initiative) {
        this.health = health;
        this.initiative = initiative;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public abstract void step(List<Personage> allPersons);

    @Override
    public int compareTo(Personage o) {
        return Integer.compare(this.initiative, o.initiative);
    }
}

class Knight extends Personage {
    public Knight(int health, int initiative) {
        super(health, initiative);
    }

    @Override
    public void step(List<Personage> allPersons) {
        // Действия рыцаря
    }
}

class Archer extends Personage {
    private int arrows;

    public Archer(int health, int initiative, int arrows) {
        super(health, initiative);
        this.arrows = arrows;
    }

    public int getArrows() {
        return arrows;
    }

    public void setArrows(int arrows) {
        this.arrows = arrows;
    }

    @Override
    public void step(List<Personage> allPersons) {
        for (Personage person : allPersons) {
            if (person instanceof Peasant && person.getState().equals("stand")) {
                this.setArrows(this.getArrows() - 1);
                person.setState("busy");
                break;
            }
        }
    }
}

class Peasant extends Personage {
    public Peasant(int health, int initiative) {
        super(health, initiative);
    }

    @Override
    public void step(List<Personage> allPersons) {
        // Действия крестьянина
    }
}

public class Main {
    public static void main(String[] args) {
        List<Personage> team1 = new ArrayList<>();
        team1.add(new Knight(100, 5));
        team1.add(new Archer(70, 8, 10));
        team1.add(new Peasant(50, 3));

        List<Personage> team2 = new ArrayList<>();
        team2.add(new Knight(120, 4));
        team2.add(new Archer(60, 9, 8));
        team2.add(new Peasant(40, 2));

        List<Personage> allPersons = new ArrayList<>();
        allPersons.addAll(team1);
        allPersons.addAll(team2);
        Collections.sort(allPersons);

        for (Personage person : allPersons) {
            System.out.println(person.getHealth());
        }

        for (Personage person : allPersons) {
            person.step(allPersons);
        }

        for (Personage person : allPersons) {
            System.out.println(person.getHealth());
        }
    }
}