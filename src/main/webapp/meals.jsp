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
        <tr>
            <td class="${meal.excess ? 'red-text' : 'green-text'}">${meal.date}</td>
            <td class="${meal.excess ? 'red-text' : 'green-text'}">${meal.description}</td>
            <td class="${meal.excess ? 'red-text' : 'green-text'}">${meal.calories}</td>
            <td><a href="#">Update</a></td>
            <td><a href="#">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>