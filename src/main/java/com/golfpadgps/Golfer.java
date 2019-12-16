package com.golfpadgps;


import org.apache.wicket.util.lang.EnumeratedType;

import java.io.Serializable;
import java.util.*;

import static org.apache.wicket.ThreadContext.getSession;

public class Golfer  implements Serializable {

    private static int nextId = 0;
    private static final Map<Long, Golfer> idToGolfer = new HashMap<>();

    private long id;
    private String name;
    private int age;
    private int handicap;
    private double handicapIndex;



    /**
     * Constructor
     *
     * @param name
     *            Golfer name
     * @param age
     *            Golfer age
     * @param handicapIndex
     *            Golfer handicap index
     */
    public Golfer(final String name, final int age, final double handicapIndex)
    {
        id = nextId++;
        this.name = name;
        this.age = age;
        this.handicapIndex = handicapIndex;
        this.handicap = 0;

        add(this);
    }

    public double getHandicapIndex() {
        return handicapIndex;
    }

    public void setHandicapIndex(double handicapIndex) {
        this.handicapIndex = handicapIndex;
    }

    /**
     *
     * @param golfer
     */
    private void add(final Golfer golfer)
    {
        boolean hit = false;
        for (Golfer value : idToGolfer.values())
        {
            if (value.toString().equals(golfer.toString()))
            {
                golfer.id = value.id;
                hit = true;
                break;
            }
        }

        if (hit == false)
        {
            idToGolfer.put(golfer.id, golfer);
        }
    }

    /**
     * @param id
     *            Book id
     * @return Book for id
     */
    public static Golfer get(final long id)
    {
        return idToGolfer.get(id);
    }

    /**
     * @return All books
     */
    public static Collection<Golfer> getGolfers()
    {
        return idToGolfer.values();
    }

    /**
     * @return Book id
     */
    public final long getId()
    {
        return id;
    }

    /**
     * @param id
     *            New id
     */
    public final void setId(final long id)
    {
        this.id = id;
    }

    /**
     * @return The author
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHandicap() {
        User user =  ((GolferSession)getSession()).getUser();
        return (int)Math.round(getHandicapIndex() * ((double)user.getCourse().getSlopeRating() / ((double)user.getCourse().getCourseRating())));
    }

    public void setHandicap(int handicap) {
        this.handicap = handicap;
    }
}
