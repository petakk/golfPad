package com.golfpadgps;

import org.apache.wicket.util.io.IClusterable;

import java.util.ArrayList;

public final class User implements IClusterable
{
    // The user's name
    private String name;
    private Course course;

    /**
     * @return User name
     */
    public final String getName()
    {
        return name;
    }

    /**
     * @param string
     *            User name
     */
    public final void setName(final String string)
    {
        name = string;
    }


    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}