package com.example.project1.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import com.example.project1.R;
import com.example.project1.presenter.CalculatorPresenter;

public class MainActivity extends AppCompatActivity implements CalculatorView{

    private GridLayout operationsGrid;
    private TextView currentExpression;
    private TextView previousExpression;
    private Button clear;
    private Button backspace;

    CalculatorPresenter presenter = new CalculatorPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clear = (Button)findViewById(R.id.clear);
        backspace = (Button)findViewById(R.id.backspace);
        currentExpression = (TextView)findViewById(R.id.currentExpression);
        operationsGrid = (GridLayout) findViewById(R.id.operationsGrid);

        presenter.onCreate();
        onButtonClick(clear);
        onButtonClick(backspace);
        onGridButtonClick(operationsGrid);
    }

    public void setCurrentExpression(String expression)
    {
        currentExpression.setText(expression);
    }

    public void onButtonClick(View view)
    {
        Button button = (Button)view;
        final String buttonText = (String)button.getText();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonText.equals(getString(R.string.backspace)))
                    presenter.buttonHandler("backspace");
                else
                    presenter.buttonHandler(buttonText);
            }
        });

    }

    public void onGridButtonClick(View view)
    {
        for(int index = 0; index < ((GridLayout)view).getChildCount(); ++index)
        {
            Button button = (Button)((GridLayout)view).getChildAt(index);
            final String buttonText = (String)button.getText();

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.buttonHandler(buttonText);
                }
            });
        }
    }

    public void displayErrorMessage()
    {
        Toast toast = Toast.makeText(getApplicationContext(), "Expression Error: Make sure what is entered is valid", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void clearCalculator()
    {
        currentExpression.setText("0");
        //previousExpression.setText(R.string.empty);
    }
}
