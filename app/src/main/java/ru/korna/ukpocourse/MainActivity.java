package ru.korna.ukpocourse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.editText_rule) EditText editText_rule;
    @BindView(R.id.editText_string) EditText editText_string;
    @BindView(R.id.button_action)Button button_action;

    Parser parser = null;
    Pattern pattern = null;
    String line = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        parser = new Parser();

        button_action.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_action:

                pattern = parser.parseRule(editText_rule.getText().toString());
                line = editText_string.getText().toString();

                clearInput();

                ArrayList<Integer> list = parser.findIndexes(line, pattern);
                String result = mapResult(list);

                showResult(result);
                break;
            default:
                break;
        }
    }

    private String mapResult(List<Integer> list){
        String result = null;

        if (list.size() != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Найденные индексы:");

            for (Integer integer : list)
                stringBuilder.append(String.valueOf(integer));

            result = stringBuilder.toString();
        }else{
            result = "Совпадений не найдено.";
        }
        return result;
    }

    private void showResult(String result){
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    private void clearInput(){
        editText_string.setText("");
    }
}
