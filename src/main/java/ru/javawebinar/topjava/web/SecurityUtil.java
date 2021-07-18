package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.util.MealsUtil;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class SecurityUtil {
    private static int USER_ID = MealsUtil.DEFAULT_USER_ID;

    public static int authUserId() {
        return USER_ID;
    }

    public static void setAuthUserId(int id) {
        USER_ID = id;
    }

    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}