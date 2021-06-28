<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit meal</h2>
<form method="post" action="meals">
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <input type="hidden" id="mealid" name="mealid" value="${meal.id}">
    <table>
        <tr>
            <td><label for="mealdate">DateTime:</label></td>
            <td><input type="date" id="mealdate" name="mealdate" value="${meal.date}"></td>
        </tr>
        <tr>
            <td><label for="description">Description:</label></td>
            <td><input type="text" id="description" name="description" value="${meal.description}"></td>
        </tr>
        <tr>
            <td><label for="calories">Calories:</label></td>
            <td><input type="number" id="calories" name="calories" value="${meal.calories == 0 ? "" : meal.calories}"></td>
        </tr>
    </table>
    <input type="submit" value="Save">
</form>
</body>
</html>
