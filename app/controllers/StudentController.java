package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.data.*;
import play.Logger.*;

import javax.inject.Inject;
import javax.inject.Singleton;

import models.*;

import views.html.*;
import java.util.*;

/**
 * This controller demonstrates how to use dependency injection to
 * bind a component into a controller class. The class contains an
 * action that shows an incrementing count to users. The {@link Counter}
 * object is injected by the Guice dependency injection system.
 */
@Singleton
public class StudentController extends Controller {

    private final ArrayList<Student> studentList;
    private FormFactory formFactory;
    private Form<Student> studentForm;

    private ALogger logger;

    @Inject
    public StudentController(FormFactory formFactory) {
        this.studentList = new ArrayList<>();
        this.formFactory = formFactory;

        studentForm = formFactory.form(Student.class);
        logger = play.Logger.of(getClass());
    }

    /**
     * An action that responds with the {@link Counter}'s current
     * count. The result is plain text. This action is mapped to
     * <code>GET</code> requests with a path of <code>/count</code>
     * requests by an entry in the <code>routes</code> config file.
     */
    public Result showStudents() {
        return ok(studentsView.render(studentList));
    }

    public Result addStudent() {
        return ok(studentsAdd.render(studentForm));
    }

    public Result add() {
        final Form<Student> form = studentForm.bindFromRequest();

        if (form.hasErrors()) {
            logger.error("errors = {}", form.errors());
        } else {
            Student s = form.bindFromRequest().get();
            logger.info("Student added {}.", s);
            studentList.add(s);
        }
        return redirect(routes.StudentController.showStudents());
    }
}
