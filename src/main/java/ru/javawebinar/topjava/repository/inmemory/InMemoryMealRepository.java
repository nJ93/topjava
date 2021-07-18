package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> this.save(meal, MealsUtil.DEFAULT_USER_ID));
        MealsUtil.mealsSec.forEach(meal -> this.save(meal, 2));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        Map<Integer, Meal> mealMap = repository.computeIfAbsent(userId, k -> new ConcurrentHashMap<>());
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            mealMap.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return mealMap.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        Meal meal = this.get(id, userId);
        if (meal != null) {
            repository.get(userId).remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> mealMap = repository.get(userId);
        if (mealMap != null) {
            return mealMap.get(id);
        }
        return null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        Map<Integer, Meal> mealMap = repository.get(userId);
        if (mealMap != null) {
            return mealMap.values()
                    .stream()
                    .sorted(Comparator.comparing(Meal::getDate).reversed())
                    .collect(Collectors.toList());
        }
        return null;
    }
}

