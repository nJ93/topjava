package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface StorageInterface {
    void addMeal(Meal meal);
    void deleteMeal(int id);
    void updateMeal(Meal meal);
    List<Meal> getAllMeals();
    Meal getMealById(int id);
}
