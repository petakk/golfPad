package com.golfpadgps;

import org.apache.wicket.Session;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authorization.strategies.page.SimplePageAuthorizationStrategy;
import org.apache.wicket.authroles.authentication.pages.SignOutPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.settings.RequestCycleSettings;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see com.golfpadgps.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	@Override
	public Session newSession(Request request, Response response)
	{
		return new GolferSession(request);
	}


	@Override
	protected void init()
	{
		super.init();

		getResourceSettings().setThrowExceptionOnMissingResource(false);
		getRequestCycleSettings().setRenderStrategy(RequestCycleSettings.RenderStrategy.REDIRECT_TO_RENDER);

		// Install a simple page authorization strategy, that checks all pages
		// of type AuthenticatedWebPage.
		IAuthorizationStrategy authorizationStrategy = new SimplePageAuthorizationStrategy(
				AuthenticatedWebPage.class, SignInPage.class)
		{
			@Override
			protected boolean isAuthorized()
			{
				// check whether the user is logged on
				return (((GolferSession)Session.get()).isSignedIn());
			}
		};
		getSecuritySettings().setAuthorizationStrategy(authorizationStrategy);


		mountPage("home", HomePage.class);
		mountPage("singOut", SignOutPage.class);
		mountPage("golfer", GolferPage.class);
		mountPage("golfer-add", GolferAddPage.class);
	}
}
