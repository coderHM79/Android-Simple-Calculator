package com.example.mhmcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button[] numericButtons;
    Button btnClear, btnClearDisplay, btnNegative, btnDelete,
            btnDivision, btnMultiplication, btnSubmission, btnSum, btnEqual, btnDot;

    TextView tvDisplay, tvPhrase;

    boolean isOperatorPressed = false;
    boolean isEqualPressed = false;

    double num1 = 0;
    double num2 = 0;
    char op = ' ';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        numericButtons = new Button[10];
        for (int i = 0; i < 10; i++) {
            int btnID = getResources().getIdentifier("btn_" + i, "id", getPackageName());
            numericButtons[i] = findViewById(btnID);

            int finalI = i;
            numericButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (isOperatorPressed) {
                        tvDisplay.setText("");
                        isOperatorPressed = false;
                    }

                    if (isEqualPressed) {
                        tvPhrase.setText("");
                        tvDisplay.setText("");
                        isEqualPressed = false;
                    }

                    String tvDisplayText = tvDisplay.getText().toString();
                    if (tvDisplayText.equals("0")) {
                        tvDisplayText = "";
                    }
                    tvDisplayText += finalI;
                    tvDisplay.setText(tvDisplayText);
                }
            });

        }

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isOperatorPressed = false;
                num1 = 0;
                num2 = 0;
                op = ' ';
                tvPhrase.setText("");
                tvDisplay.setText("0");
            }
        });

        btnClearDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDisplay.setText("0");
            }
        });

        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tvDisplayText = tvDisplay.getText().toString();
                if (tvDisplayText.contains("-")) {
                    tvDisplayText = tvDisplayText.substring(1);
                } else {
                    tvDisplayText = "-" + tvDisplayText;
                }
                tvDisplay.setText(tvDisplayText);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isOperatorPressed = false;
                String tvDisplayText = tvDisplay.getText().toString();
                if (tvDisplayText.length() > 0) {
                    tvDisplayText = tvDisplayText.substring(0, tvDisplayText.length() - 1);
                    if (tvDisplayText.length() == 0) {
                        tvDisplayText = "0";
                    }
                    tvDisplay.setText(tvDisplayText);
                }
            }
        });

        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isOperatorPressed = true;
                String tvDisplayText = tvDisplay.getText().toString();

                if (isEqualPressed) {
                    tvPhrase.setText("");
                    isEqualPressed = false;
                }

                if (op != '÷') {
                    num1 = Double.parseDouble(tvDisplayText);
                    String temp = tvDisplayText + " ÷ ";
                    tvPhrase.setText(temp);
                    op = '÷';
                } else {
                    num2 = Double.parseDouble(tvDisplayText);
                    if (num1 == 0 || num2 == 0)
                        return;
                    num1 = calculate(num1, num2, op);

                    String strNum1 = "";
                    if (isInteger(num1)) {
                        strNum1 = String.valueOf(num1).substring(0, String.valueOf(num1).indexOf("."));
                    } else {
                        strNum1 = String.valueOf(num1);
                    }

                    String temp = strNum1 + " ÷ ";
                    tvPhrase.setText(temp);

                    tvDisplay.setText(strNum1);
                }

            }
        });

        btnMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isOperatorPressed = true;
                String tvDisplayText = tvDisplay.getText().toString();

                if (isEqualPressed) {
                    tvPhrase.setText("");
                    isEqualPressed = false;
                }

                if (op != 'x') {
                    num1 = Double.parseDouble(tvDisplayText);
                    String temp = tvDisplayText + " x ";
                    tvPhrase.setText(temp);
                    op = 'x';
                } else {
                    num2 = Double.parseDouble(tvDisplayText);
                    num1 = calculate(num1, num2, op);

                    String strNum1 = "";
                    if (isInteger(num1)) {
                        strNum1 = String.valueOf(num1).substring(0, String.valueOf(num1).indexOf("."));
                    } else {
                        strNum1 = String.valueOf(num1);
                    }

                    String temp = strNum1 + " x ";
                    tvPhrase.setText(temp);

                    tvDisplay.setText(strNum1);
                }
            }
        });

        btnSubmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isOperatorPressed = true;
                String tvDisplayText = tvDisplay.getText().toString();

                if (isEqualPressed) {
                    tvPhrase.setText("");
                    isEqualPressed = false;
                }


                if (op != '-') {
                    num1 = Double.parseDouble(tvDisplayText);
                    String temp = tvDisplayText + " - ";
                    tvPhrase.setText(temp);
                    op = '-';
                } else {
                    num2 = Double.parseDouble(tvDisplayText);
                    num1 = calculate(num1, num2, op);

                    String strNum1 = "";
                    if (isInteger(num1)) {
                        strNum1 = String.valueOf(num1).substring(0, String.valueOf(num1).indexOf("."));
                    } else {
                        strNum1 = String.valueOf(num1);
                    }

                    String temp = strNum1 + " - ";
                    tvPhrase.setText(temp);

                    tvDisplay.setText(strNum1);
                }
            }
        });

        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isOperatorPressed = true;
                String tvDisplayText = tvDisplay.getText().toString();

                if (isEqualPressed) {
                    tvPhrase.setText("");
                    isEqualPressed = false;
                }


                if (op != '+') {
                    num1 = Double.parseDouble(tvDisplayText);
                    String temp = tvDisplayText + " + ";
                    tvPhrase.setText(temp);
                    op = '+';
                } else {
                    num2 = Double.parseDouble(tvDisplayText);
                    num1 = calculate(num1, num2, op);

                    String strNum1 = "";
                    if (isInteger(num1)) {
                        strNum1 = String.valueOf(num1).substring(0, String.valueOf(num1).indexOf("."));
                    } else {
                        strNum1 = String.valueOf(num1);
                    }

                    String temp = strNum1 + " + ";
                    tvPhrase.setText(temp);

                    tvDisplay.setText(strNum1);
                }

            }

        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tvDisplayText = tvDisplay.getText().toString();
                if (op != ' ') {
                    isEqualPressed = true;
                    isOperatorPressed = false;
                    num2 = Double.parseDouble(tvDisplayText);
                    double res = calculate(num1, num2, op);

                    String strNum1 = "";
                    if (isInteger(num1)) {
                        strNum1 = String.valueOf(num1).substring(0, String.valueOf(num1).indexOf("."));
                    } else {
                        strNum1 = String.valueOf(num1);
                    }

                    String strNum2 = "";
                    if (isInteger(num2)) {
                        strNum2 = String.valueOf(num2).substring(0, String.valueOf(num2).indexOf("."));
                    } else {
                        strNum2 = String.valueOf(num2);
                    }

                    String strRes = "";
                    if (isInteger(res)) {
                        strRes = String.valueOf(res).substring(0, String.valueOf(res).indexOf("."));
                    } else {
                        strRes = String.valueOf(res);
                    }

                    tvDisplay.setText(strRes);
                    String temp = strNum1 + " " + op + " " + strNum2 + " = ";
                    tvPhrase.setText(temp);
                    op = ' ';
                    num1 = 0;
                    num2 = 0;
                }

            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tvDisplayText = tvDisplay.getText().toString();
                if (tvDisplayText.isEmpty() || tvDisplayText.contains(".")) {
                    return;
                }
                tvDisplayText += '.';
                tvDisplay.setText(tvDisplayText);
            }
        });

    }

    private boolean isInteger(double num) {
        return Math.ceil(num) == Math.floor(num);
    }

    private double calculate(double num1, double num2, char op) {
        switch (op) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case 'x':
                return num1 * num2;
            case '÷':
                return num1 / num2;
            default:
                return 0;
        }
    }

    private void findViews() {
        btnClear = findViewById(R.id.btn_clear);
        btnClearDisplay = findViewById(R.id.btn_clear_display);
        btnNegative = findViewById(R.id.btn_negative);
        btnDelete = findViewById(R.id.btn_delete);
        btnDivision = findViewById(R.id.btn_division);
        btnMultiplication = findViewById(R.id.btn_multiplication);
        btnSubmission = findViewById(R.id.btn_submission);
        btnSum = findViewById(R.id.btn_sum);
        btnEqual = findViewById(R.id.btn_equal);
        btnDot = findViewById(R.id.btn_dot);
        tvDisplay = findViewById(R.id.tv_display);
        tvPhrase = findViewById(R.id.tv_phrase);
    }

}// end of MainActivity class
