package com.golfpadgps;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class AuthenticatedWebPage  extends WebPage
{

    public AuthenticatedWebPage(final PageParameters parameters) {
        super(parameters);
        add(new BookmarkablePageLink<Void>("singOut", SignOutPage.class));
    }

    /**
     * Get downcast session object
     *
     * @return The session
     */
    public GolferSession getLibrarySession()
    {
        return (GolferSession)getSession();
}

}
