package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryDb implements IAbstractDb {
    private Map<Integer, Meal> storage;

    public InMemoryDb() {
        this.storage = new HashMap<>();
    }

    @Override
    public void addMeal(Meal meal) {
        storage.put(meal.getId(), meal);
    }

    @Override
    public void deleteMeal(int id) {
        storage.remove(id);
    }

    @Override
    public void updateMeal(Meal meal) {
        addMeal(meal);
    }

    @Override
    public List<Meal> getAllMeals() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Meal getMealById(int id) {
        return storage.get(id);
    }
}
