<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit meal</h2>
<form method="post" action="meals">
    <table>
        <tr>
            <td><label for="mealdate">DateTime:</label></td>
            <td><input type="date" id="mealdate" name="mealdate"></td>
        </tr>
        <tr>
            <td><label for="description">Description:</label></td>
            <td><input type="text" id="description" name="description"></td>
        </tr>
        <tr>
            <td><label for="calories">Calories:</label></td>
            <td><input type="number" id="calories" name="calories"></td>
        </tr>
    </table>
    <input type="submit" value="Save">
</form>
</body>
</html>
