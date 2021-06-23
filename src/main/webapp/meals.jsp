<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <style>
        .red-text {
            color: red;
        }
        .green-text {
            color: green;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<a href="meals?action=create">Add Meal</a>
<a href="edit_meal.jsp">Add Meal</a>
<table border="1" width="500" cellspacing="0">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${meals}" var="meal">
        <tr class="${meal.excess ? 'red-text' : 'green-text'}">
            <td>${meal.date}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=update">Update</a></td>
            <td><a href="meals?action=delete">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>