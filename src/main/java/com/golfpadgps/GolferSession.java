package com.golfpadgps;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.request.Request;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import java.util.List;

public class GolferSession extends AuthenticatedWebSession
{
    // Logged in user
    private User user;

    /**
     * Constructor
     *
     * @param request
     *            The current request object
     */
    protected GolferSession(Request request)
    {
        super(request);
    }

    /**
     * Checks the given username and password, returning a User object if if the username and
     * password identify a valid user.
     *
     * @param username
     *            The username
     * @param password
     *            The password
     * @return The signed in user
     */
    @Override
    public final boolean authenticate(final String username, final String password)
    {
        final String WICKET = "golfer";

        if (WICKET.equalsIgnoreCase(username) && WICKET.equalsIgnoreCase(password))
        {
            // Create User object
            final User user = new User();

            user.setName(username);

            Course course = new Course("Open American Course", 113, 120);;


            user.setCourse(course);

            setUser(user);

            return true;
        }

        return false;
    }

    /**
     * @return User
     */
    public User getUser()
    {
        return user;
    }

    /**
     * @param user
     *            New user
     */
    public void setUser(final User user)
    {
        this.user = user;
    }

    @Override
    public void invalidate()
    {
        super.invalidate();
        user = null;
    }

    @Override
    public Roles getRoles()
    {
        return null;
    }
}
