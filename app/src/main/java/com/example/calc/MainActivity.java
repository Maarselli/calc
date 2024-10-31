package com.example.calc;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private TextView input; // Declare TextViews for input and output
    private TextView output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //Initialize the input and output TextViews
        input = findViewById(R.id.input);
        output = findViewById(R.id.output);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Устанавливаем TextWatcher для отслеживания изменений текста
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override            public void afterTextChanged(Editable s) {}
        });
        // Set up button listeners
        findViewById(R.id.button_clear).setOnClickListener(v -> clearInput());
        findViewById(R.id.button_bracket_left).setOnClickListener(v -> addToInputText("("));
        findViewById(R.id.button_bracket_right).setOnClickListener(v -> addToInputText(")"));
        findViewById(R.id.button_0).setOnClickListener(v -> addToInputText("0"));
        findViewById(R.id.button_1).setOnClickListener(v -> addToInputText("1"));
        findViewById(R.id.button_2).setOnClickListener(v -> addToInputText("2"));
        findViewById(R.id.button_3).setOnClickListener(v -> addToInputText("3"));
        findViewById(R.id.button_4).setOnClickListener(v -> addToInputText("4"));
        findViewById(R.id.button_5).setOnClickListener(v -> addToInputText("5"));
        findViewById(R.id.button_6).setOnClickListener(v -> addToInputText("6"));
        findViewById(R.id.button_7).setOnClickListener(v -> addToInputText("7"));
        findViewById(R.id.button_8).setOnClickListener(v -> addToInputText("8"));
        findViewById(R.id.button_9).setOnClickListener(v -> addToInputText("9"));
        findViewById(R.id.button_dot).setOnClickListener(v -> addToInputText("."));
        findViewById(R.id.button_division).setOnClickListener(v -> addToInputText("/"));
        findViewById(R.id.button_multiply).setOnClickListener(v -> addToInputText("*"));
        findViewById(R.id.button_subtraction).setOnClickListener(v -> addToInputText("-"));
        findViewById(R.id.button_addition).setOnClickListener(v -> addToInputText("+"));
        findViewById(R.id.button_equals).setOnClickListener(v -> showResult());
        findViewById(R.id.button_percent).setOnClickListener(v -> addToInputText("%"));    }
    private void clearInput() {
        input.setText(""); // Clear the input
        output.setText(""); // Clear the output
    }
    private void addToInputText(String value) {
        input.append(value); // Append text to the input TextView
    }
    // Функция для получения строки ввода
    private String getInputExpression() {
        return input.getText().toString(); // Возвращаем текст из поля ввода
    }
    private void showResult() {
        try {            // Заменяем % на соответствующее значение для деления
            String expression = getInputExpression().replace("%", "/100");
            // Вычисление результата выражения
            double result = new ExpressionBuilder(expression).build().evaluate();
            // вывод результат
            output.setText(new DecimalFormat("0.######").format(result));
            // цвет текста для результата
            output.setTextColor(ContextCompat.getColor(this, R.color.neon_green));
        } catch (Exception e) {            // В случае ошибки устанавливаем текст "Ошибка"
            output.setText("Ошибка");
            output.setTextColor(ContextCompat.getColor(this, R.color.red));
        }    }
}