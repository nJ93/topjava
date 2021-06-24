package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.IAbstractDb;
import ru.javawebinar.topjava.storage.InMemoryDb;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    IAbstractDb db = InMemoryDb.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action == null ? "list" : action) {
            case "create":
                log.debug("Begin meal creating...");
                request.getRequestDispatcher("/edit_meal.jsp").forward(request, response);
                break;
            case "update":
                log.debug("Begin meal creating...");
            case "delete":
                int id = Integer.parseInt(request.getParameter("id"));
                log.debug("Deleting meal with id = " + id);
                db.deleteMeal(id);
                response.sendRedirect("meals");
                break;
            case "list":
            default:
                MealsUtil.initializeMeals(LocalTime.of(0, 0), LocalTime.of(23, 59));
                request.setAttribute("meals", db.getAllMeals());
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDateTime mealdate = LocalDateTime.parse(request.getParameter("mealdate"));
        int calories = Integer.parseInt(request.getParameter("calories"));
        String description = request.getParameter("description");
        Meal meal = new Meal(mealdate, description, calories);
        db.addMeal(meal);
        response.sendRedirect("meals");
    }
}
