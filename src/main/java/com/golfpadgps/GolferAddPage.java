package com.golfpadgps;

import components.EditStringForm;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.FormComponentFeedbackBorder;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.ValidatorAdapter;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator;

public class GolferAddPage extends AuthenticatedWebPage {


    public GolferAddPage(PageParameters parameters) {
        super(parameters);

        Course course = getLibrarySession().getUser().getCourse();
        add(new EditGolferForm("golferForm", course, course.newGolfer()));

    }


    static public final class EditGolferForm extends Form<Golfer>{
         private Course course;
         private Golfer golfer;
         public EditGolferForm(String id, final Course course, final Golfer golfer){
            super(id, new CompoundPropertyModel<>(golfer));

            this.course = course;
            this.golfer = golfer;

            final TextField<String> name = new TextField<>("name");
            name.setRequired(true);
            name.add(new StringValidator(null, 30));

            final MarkupContainer nameFeedback = new FormComponentFeedbackBorder("nameFeedback");
            add(nameFeedback);
            nameFeedback.add(name);
            // Create a required text field that edits the book's author
            final NumberTextField<Integer> age = new NumberTextField<>("age");
            age.setRequired(true);
            age.add(new RangeValidator<>(18,99));
            final MarkupContainer ageFeedback = new FormComponentFeedbackBorder("ageFeedback");
            add(ageFeedback);
            ageFeedback.add(age);
        }

        @Override
        protected void onSubmit() {
            super.onSubmit();
            course.addGolfer(golfer);
            setResponsePage(GolferPage.class, new PageParameters().add("id", golfer.getId()));
        }
    }

}
