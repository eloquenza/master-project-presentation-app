package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.data.*;

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

    @Inject
    public StudentController(FormFactory formFactory) {
        this.studentList = new ArrayList<>();
        studentForm = formFactory.form(Student.class);
        this.formFactory = formFactory;
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
        Student s = studentForm.bindFromRequest().get();
        studentList.add(s);
        return redirect(routes.StudentController.showStudents());
    }
}
