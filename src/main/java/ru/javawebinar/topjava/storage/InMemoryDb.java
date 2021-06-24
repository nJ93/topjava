package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDb implements IAbstractDb {
    private static InMemoryDb instance = new InMemoryDb();

    public static InMemoryDb getInstance() {
        return instance;
    }

    private Map<Integer, Meal> storage;

    private InMemoryDb() {
        this.storage = new ConcurrentHashMap<>();
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

    @Override
    public List<MealTo> getAllMealsExceed(LocalTime startTime, LocalTime endTime) {
        return MealsUtil.filteredByStreams(getAllMeals(), startTime, endTime, 2000);
    }
}
