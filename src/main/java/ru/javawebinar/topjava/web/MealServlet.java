package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.IAbstractDb;
import ru.javawebinar.topjava.storage.InMemoryDb;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    IAbstractDb db = InMemoryDb.getInstance();

    @Override
    public void init() throws ServletException {
        super.init();
        MealsUtil.initializeMeals();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action == null ? "list" : action) {
            case "create":
                log.debug("Begin meal creating...");
                request.getRequestDispatcher("/edit_meal.jsp").forward(request, response);
                break;
            case "update":
                String updateId = request.getParameter("id");
                if (updateId != null) {
                    request.setAttribute("meal", db.getMealById(Integer.parseInt(updateId)));
                    request.getRequestDispatcher("/edit_meal.jsp").forward(request, response);
                }
                Meal meal = new Meal();
                request.setAttribute("meal", meal);
                log.debug("Updating meal with id = " + updateId);
                request.getRequestDispatcher("/edit_meal.jsp").forward(request, response);
            case "delete":
                int id = Integer.parseInt(request.getParameter("id"));
                log.debug("Deleting meal with id = " + id);
                db.deleteMeal(id);
                response.sendRedirect("meals");
//                request.setAttribute("meals", db.getAllMealsExceed(LocalTime.of(0, 0), LocalTime.of(23, 59), 2000));
//                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
            case "list":
            default:
//                String act = (String) request.getAttribute("action");
//                if (act != null && act.equals("list"))
                request.setAttribute("meals", db.getAllMealsExceed(LocalTime.of(0, 0), LocalTime.of(23, 59), 2000));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        LocalDateTime mealdate = null;
        LocalDateTime mealdate = LocalDate.parse(request.getParameter("mealdate"), DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
        int calories = Integer.parseInt(request.getParameter("calories"));
        String description = request.getParameter("description");
        Meal meal = new Meal(mealdate, description, calories);
        db.addMeal(meal);
        response.sendRedirect("meals");
    }
}
