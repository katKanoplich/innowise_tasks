<!DOCTYPE html>
<html>
<head>
    <title>Products</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>Dear <%=request.getSession().getAttribute("name") %>, your order:</h1>
<form action="secondScreen" method="post">
    <table>
        <% String[] items = (String[]) request.getAttribute("items");
            if (items != null) {
                for (String item : items) { %>
        <tr>
            <td><%= item %></td>
        </tr>
        <% }
        } %>
    </table>
    <h3 style="text-align: center;">Total: $ <%= request.getAttribute("total") %> </h3>
</form>

</body>
</html>