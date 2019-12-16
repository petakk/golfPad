package com.golfpadgps;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class SignOutPage extends WebPage
{
    /**
     * Constructor
     *
     * @param parameters
     *            Page parameters (ignored since this is the home page)
     */
    public SignOutPage(final PageParameters parameters)
    {

        getSession().invalidate();

        add(new BookmarkablePageLink<Void>("home", HomePage.class));
    }
}
