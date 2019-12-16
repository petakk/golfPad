package com.golfpadgps;

import components.EditStringForm;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class GolferPage extends AuthenticatedWebPage {


    public GolferPage(PageParameters parameters) {
        super(parameters);

        int id = parameters.get("id").toInt(0);

        if (id == 0) {

        }

        if (Golfer.get(id) == null) {
            throw new RestartResponseException(HomePage.class);
        }

        IModel<Golfer> golferModel =  (IModel<Golfer>) () -> Golfer.get(id);;

        add(new Label("name", new PropertyModel<>(golferModel, "name")));
        add(new Label("age", new PropertyModel<>(golferModel, "age")));
        add(new EditStringForm("handicapIndex", new PropertyModel<>(golferModel, "handicapIndex")));
        add(new Label("handicap", new PropertyModel<>(golferModel, "handicap")));


    }
}
