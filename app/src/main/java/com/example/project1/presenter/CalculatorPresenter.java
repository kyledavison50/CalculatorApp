package com.example.project1.presenter;

import android.util.Log;

import com.example.project1.model.Calculator;
import com.example.project1.view.MainActivity;

import org.apache.commons.lang3.StringUtils;

public class CalculatorPresenter implements Presenter {

    private MainActivity view;
    private Calculator model;
    private String expression;
    private String previousSymbol;
    private Boolean previousSymbolWasOperator;

    private Boolean decimalPlaced;


    public CalculatorPresenter(MainActivity view)
    {
        expression = "";
        previousSymbol = "";
        previousSymbolWasOperator = false;

        decimalPlaced = false;

        this.view = view;
        this.model = new Calculator();
    }

    @Override
    public void onCreate()
    {
        model = new Calculator();
    }

    @Override
    public void onPause()
    {

    }

    @Override
    public void onResume()
    {

    }

    @Override
    public void onDestroy()
    {

    }

    public void buttonHandler(String expressionComponent)
    {
        final boolean operatorCheck = expressionComponent.equals("*") ||
                expressionComponent.equals("+") ||
                expressionComponent.equals("-") ||
                expressionComponent.equals("/");

        // Solve the expression, leave result on the screen
        if(expressionComponent.equals("="))
        {
            String result = model.Evaluate(expression);

            if(!result.equals("Error"))
            {
                expression = result;
                updateCurrentExpression(result);
            }
            else
                view.displayErrorMessage();
            return;
        }

        // Store previous symbol is expression
        if(!(expression.length() == 0))
            previousSymbol = expression.substring(expression.length() - 1);

        // Gets rid of single zero present after a clear of app startup (like a real calculator)
        if(expression.equals("0"))
            expression = StringUtils.chop(expression);

        //Handle pressing backspace or delete
        if(expressionComponent.equals("backspace"))
        {
            expressionComponent = "";
            expression = StringUtils.chop(expression);
        }

        // Handles the clear button
        if(expressionComponent.equals("clear"))
        {
            expression = "0";
            previousSymbol = "";
            updateCurrentExpression(expression);
            view.clearCalculator(); // Might not need this
            return;
        }

        // Prevents multiple decimal places per number
        if(decimalPlaced && expressionComponent.equals("."))
            return;

        // Prevents placing only decimals and operators
        if(previousSymbol.equals(".") && operatorCheck)
            return;

        if(!decimalPlaced && expressionComponent.equals("."))
            decimalPlaced = true;

        // Perform checks to prevent error (no two symbols in a row unless negative, only one decimal per number, etc)
        if(operatorCheck)
        {
            if(previousSymbolWasOperator && !expressionComponent.equals("-"))
            {
                expressionComponent = "";
            }
            previousSymbolWasOperator = true;
            decimalPlaced = false;
        }
        else {
            previousSymbolWasOperator = false;
        }

        Log.d("Previous", "Previous symbol was: " + previousSymbol);
        expression = expression.concat(expressionComponent);
        updateCurrentExpression(expression);
    }

    public void updateCurrentExpression(String expression)
    {
        view.setCurrentExpression(expression);
        //model.setCurrentExpression(expression);
    }

    public void onClearSelected()
    {
        view.clearCalculator();
    }
}
