package com.example.project1.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project1.R;
import com.example.project1.presenter.CalculatorPresenter;

public class MainActivity extends AppCompatActivity implements CalculatorView{

    private ViewGroup operationsGrid;
    private TextView currentExpression;
    private TextView previousExpression;
    private Button clear;
    private Button backspace;

    CalculatorPresenter presenter = new CalculatorPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        operationsGrid = (ViewGroup) findViewById(R.id.operationsGrid);
        currentExpression = (TextView)findViewById(R.id.currentExpression);
        presenter.onCreate();
    }

    public void setCurrentExpression(String expression) {
        currentExpression.setText(expression);
    }

    public void onGridButtonClick(View view) {
        for(int index=0; index < ((GridLayout)view).getChildCount(); ++index)
        {
            Button button = (Button)((GridLayout)view).getChildAt(index);
            final String buttonText = (String)button.getText();

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Button Hit: " + buttonText, Toast.LENGTH_SHORT).show();
                    presenter.onGridClick(buttonText);
                }
            });
        }
    }

    @Override
    public void clearCalculator() {
        currentExpression.setText("");
        previousExpression.setText("");
    }
}
