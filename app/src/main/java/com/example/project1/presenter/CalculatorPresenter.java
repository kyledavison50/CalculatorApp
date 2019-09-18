package com.example.project1.presenter;

import com.example.project1.model.Calculator;
import com.example.project1.view.CalculatorView;
import com.example.project1.view.MainActivity;

public class CalculatorPresenter implements Presenter {

    private MainActivity view;
    private Calculator model;
    private String expression;

    public CalculatorPresenter(MainActivity view) {
        expression = "";
        this.view = view;
        this.model = new Calculator();
    }

    @Override
    public void onCreate() {
        model = new Calculator();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public void onGridClick(String expressionComponent)
    {
        expression.concat(expressionComponent);
        updateCurrentExpression("5000");
        //TODO: Handle pressing backspace

        //TODO: Perform checks to prevent error (no two symbols in a row unless negative, etc)

        //TODO: Set text of Current Expression View to add this

        //TODO: Set expression of Model to add this to equation
    }

    public void updateCurrentExpression(String expression) {
        view.setCurrentExpression(expression);
        model.setCurrentExpression(expression);
    }

    public void onClearSelected() {
        view.clearCalculator();
        model.clearExpressions();
    }
}
