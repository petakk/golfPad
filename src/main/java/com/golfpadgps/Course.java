package com.golfpadgps;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course implements Serializable {

    private static final Map<Long, Golfer> idToCourse = new HashMap<>();

    private String title;
    private int courseRating;
    private int slopeRating;

    // The course's golfer list
    private List<Golfer> golfers = new ArrayList<Golfer>();

    public Course(String title, int courseRating, int slopeRating) {
        this.title = title;
        this.courseRating = courseRating;
        this.slopeRating = slopeRating;


        golfers.add(new Golfer("John Smith", 56, 12.1));
        golfers.add(new Golfer("Sandra Bullock", 55, 15.5));
        golfers.add(new Golfer("Cliff Booth", 44, 9));

    }

    public Golfer newGolfer() {
        Golfer golfer = new Golfer("", 0, 0);
        return golfer;
    }

    public void addGolfer(Golfer golfer){
        golfers.add(golfer);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCourseRating() {
        return courseRating;
    }

    public void setCourseRating(int courseRating) {
        this.courseRating = courseRating;
    }

    public int getSlopeRating() {
        return slopeRating;
    }

    public void setSlopeRating(int slopeRating) {
        this.slopeRating = slopeRating;
    }

    /**
     * @return Course's golfers list
     */
    public final List<Golfer> getGolfers()
    {
        return golfers;
    }

    /**
     * @param golfers
     *            New golfer list
     */
    public void setGolfers(final List<Golfer> golfers)
    {
        this.golfers = golfers;
    }

}
