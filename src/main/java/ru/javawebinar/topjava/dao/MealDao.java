package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.InMemoryDb;

import java.util.List;

public class MealDao {
    private InMemoryDb inMemoryDb;

    public MealDao() {
        this.inMemoryDb = new InMemoryDb();
    }

    public void addMeal(Meal meal) {
        inMemoryDb.addMeal(meal);
    }

    public void deleteMeal(int id) {
        inMemoryDb.deleteMeal(id);
    }

    public List<Meal> getAllMeals() {
        return inMemoryDb.getAllMeals();
    }

    public Meal getMealById(int id) {
        return inMemoryDb.getMealById(id);
    }
}
