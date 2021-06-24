package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalTime;
import java.util.List;

public interface IAbstractDb {
    void addMeal(Meal meal);
    void deleteMeal(int id);
    void updateMeal(Meal meal);
    List<Meal> getAllMeals();
    Meal getMealById(int id);
    List<MealTo> getAllMealsExceed(LocalTime startTime, LocalTime endTime);
}
