package com.example.task2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/secondScreen")
public class SecondScreenServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
        String[] items = request.getParameterValues("items");
        request.setAttribute("items", items);

        double totalCost = 0.0;
        Map<String, Double> itemPrices = new HashMap<>();
        itemPrices.put("Book 5,5 $", 5.5);
        itemPrices.put("Mobile Phone 15,5 $", 15.5);
        itemPrices.put("Notebook 3,5 $", 3.5);
        itemPrices.put("Laptop 100,7 $", 100.7);
        itemPrices.put("Headphones 18,0 $", 18.0);

        if (items != null) {
            for (String item : items) {
                if (itemPrices.containsKey(item)) {
                    totalCost += itemPrices.get(item);
                }
            }
        }
        request.setAttribute("total", String.valueOf(totalCost));
        request.getRequestDispatcher("thirdScreen.jsp").forward(request, response);

    }
}
