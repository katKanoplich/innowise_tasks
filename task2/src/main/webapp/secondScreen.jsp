<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SecondScreen</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>Hello <%= request.getSession().getAttribute("name")%> !</h1>
<h1>Make your order</h1>

<form action="secondScreen" method="post" >
    <label for="items"></label>
    <select id="items" name="items" multiple="multiple">
        <option value="Book 5,5 $">Book (5,5 $)</option>
        <option value="Mobile Phone 15,5 $">Mobile Phone (15,5 $)</option>
        <option value="Notebook 3,5 $" >Notebook (3,5 $)</option>
        <option value="Laptop 100,7 $" >Laptop (100,7 $)</option>
        <option value="Headphones 18,0 $" >Headphones (18,0 $)</option>
    </select>
    <br><br>
    <button type="submit">Submit</button>
</form>
</body>
</html>