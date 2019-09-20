package com.example.project1.model;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Calculator
{
    public Calculator()
    {

    }

    public String Evaluate(String expression)
    {
        String result;
        try {
            Expression e = new ExpressionBuilder(expression).build();
            result = String.valueOf(e.evaluate());
        } catch(Exception ex) {
            result = "Error";
        }
        return result;
    }

}
