package com.example.project1.model;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Calculator {

    private String currentExpression;
    private String previousExpression;

    public Calculator()
    {
        currentExpression = "";
        previousExpression = "";
    }

    public void setCurrentExpression(String currentExpression) { this.currentExpression = currentExpression; }
    public String getCurrentExpression() { return currentExpression; }
    public String getPreviousExpression() { return previousExpression; }

    public void clearExpressions()
    {
        currentExpression = "";
        previousExpression = "";
    }

    public void Evaluate()
    {
        Expression e = new ExpressionBuilder(currentExpression).build();
        double result = e.evaluate();
        previousExpression = currentExpression;
        currentExpression = String.valueOf(result);

        //TODO: Update Presenter to update the View with updated values
    }

}
