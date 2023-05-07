package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    // Calculator result display
    private EditText resultTextView,expresionEdt;

    // Calculator buttons
    private Button btn0,btn00, btn1, btn2, btn3, btn4, btn5, btn6, btn7,btn8,btn9, btnPlus, btnMinus, btnMultiply, btnDivide, btnModulus, btnClear, btnEquals, btnDecimal,button_del;

    // Calculator variables
    private String operator = "";
    private double firstNumber = 0.0;
    private double secondNumber = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize calculator buttons
        resultTextView = findViewById(R.id.display);
        expresionEdt = findViewById(R.id.expresionEdt);

        btn0 = findViewById(R.id.button_0);
        btn00 = findViewById(R.id.button_00);
        btn1 = findViewById(R.id.button_1);
        btn2 = findViewById(R.id.button_2);
        btn3 = findViewById(R.id.button_3);
        btn4 = findViewById(R.id.button_4);
        btn5 = findViewById(R.id.button_5);
        btn6 = findViewById(R.id.button_6);
        btn7 = findViewById(R.id.button_7);
        btn8 = findViewById(R.id.button_8);
        btn9 = findViewById(R.id.button_9);
        btnPlus = findViewById(R.id.button_add);
        btnMinus = findViewById(R.id.button_subtract);
        btnMultiply = findViewById(R.id.button_multiply);
        btnDivide = findViewById(R.id.button_divide);
        btnModulus = findViewById(R.id.button_percent);
        btnClear = findViewById(R.id.button_clear);
        btnEquals = findViewById(R.id.button_equal);
        btnDecimal = findViewById(R.id.button_dot);
        button_del = findViewById(R.id.button_del);


        // Set button click listeners
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView.append("0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView.append("1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView.append("2");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView.append("3");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView.append("4");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView.append("5");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView.append("6");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView.append("7");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView.append("8");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView.append("9");
            }
        });

        btn00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView.append("00");
            }
        });

        button_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String exp = resultTextView.getText().toString();
                String temp="";
                if(exp.length()>0){
                    for(int i=0;i<exp.length()-1;i++){
                        temp =temp+exp.charAt(i);
                    }
                }
                resultTextView.setText(temp);
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = resultTextView.getText().toString();
                if(temp.length() > 0)
                    resultTextView.setText(temp+"+");
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = resultTextView.getText().toString();
                if(temp.length() > 0)
                    resultTextView.setText(temp+"-");
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = resultTextView.getText().toString();
                if(temp.length() > 0){
                    resultTextView.setText(temp+"*");
                }
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = resultTextView.getText().toString();
                if(temp.length() > 0)
                    resultTextView.setText(temp+"/");
            }
        });

        btnModulus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = resultTextView.getText().toString();
                if(temp.length() > 0)
                    resultTextView.setText(temp+"%");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resultTextView.setText("");
                expresionEdt.setText("");
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expresionEdt.setText(resultTextView.getText().toString());

                try{
                    double result = evaluateExpression(resultTextView.getText().toString());
                    resultTextView.setText(String.valueOf(result));
                }
                catch (Exception e){
                    e.printStackTrace();

                    if(e.toString().equals("NaN")){
                        resultTextView.setText("Can't divide by zero");
                    }
                    else{
                        resultTextView.setText("invalid input !");
                    }

                }

            }
        });

        btnDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (resultTextView.getText().toString().contains(".")) {
                    return;
                }
                resultTextView.append(".");

            }
        });




//        ,................
    }

    public static double evaluateExpression(String expression) {
        Stack<Double> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                String numStr = "" + c;
                while (i + 1 < expression.length() && Character.isDigit(expression.charAt(i + 1))) {
                    numStr += expression.charAt(i + 1);
                    i++;
                }
                double num = Double.parseDouble(numStr);
                operands.push(num);
            }
            else if (c == '(') {
                operators.push(c);
            }
            else if (c == ')') {
                while (operators.peek() != '(') {
                    double result = performOperation(operands.pop(), operands.pop(), operators.pop());
                    operands.push(result);
                }
                operators.pop(); // remove the '(' from the operators stack
            }
            else if (isOperator(c)) {
                while (!operators.isEmpty() && hasPrecedence(c, operators.peek())) {
                    double result = performOperation(operands.pop(), operands.pop(), operators.pop());
                    operands.push(result);
                }
                operators.push(c);
            }
        }

        while (!operators.isEmpty()) {
            double result = performOperation(operands.pop(), operands.pop(), operators.pop());
            operands.push(result);
        }

        return operands.pop();
    }

    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    public static double performOperation(double operand2, double operand1, char operator) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
            default:
                return 0;
        }
    }

}