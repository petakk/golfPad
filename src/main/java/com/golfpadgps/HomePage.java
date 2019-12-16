package com.golfpadgps;

import components.EditStringForm;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AuthenticatedWebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters)
	{
		// Add table of books
		super(parameters);
		final PageableListView<Golfer> listView;
		User user = getLibrarySession().getUser();

		IModel<Course> courseIModel = (IModel<Course>) () -> user.getCourse();

		setDefaultModel(new CompoundPropertyModel<>(courseIModel));

		add(new Label("title", new PropertyModel<>(courseIModel, "title")));
		add(new Label("courseRating", new PropertyModel<>(courseIModel, "courseRating")));
		add(new EditStringForm("slopeRating", new PropertyModel<>(courseIModel, "slopeRating")));

		add(listView = new PageableListView<Golfer>("golfers", new PropertyModel<>(this,
				"golfers"), 4)
		{
			@Override
			public void populateItem(final ListItem<Golfer> listItem)
			{
				final Golfer golfer = listItem.getModelObject();
				listItem.add(new BookmarkablePageLink<Void>("details", GolferPage.class, new PageParameters().add("id", golfer.getId())).add(new Label("golfer", new PropertyModel<>(listItem.getModel(), "name"))));
				listItem.add(new Label("age", new PropertyModel(listItem.getModel(), "age")));
				listItem.add(new Label("handicap", new PropertyModel(listItem.getModel(), "handicap")));
				listItem.add(new Label("handicapIndex", new PropertyModel(listItem.getModel(), "handicapIndex")));
			}
		});


	}

	/**
	 *
	 * @return List of golfers
	 */
	public List<Golfer> getGolfers()
	{
		// Note: checkAccess() (and thus login etc.) happen after the Page
		// has been instantiated. Thus, you can not realy on user != null.
		// Note2: In any case, all components must be associated with a
		// wicket tag.
		User user = getLibrarySession().getUser();
		if (user == null)
		{
			return new ArrayList<>();
		}

		return user.getCourse().getGolfers();
	}

}
