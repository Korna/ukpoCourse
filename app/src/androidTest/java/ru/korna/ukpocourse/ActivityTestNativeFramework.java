package ru.korna.ukpocourse;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ActivityTestNativeFramework {
    @Test
    public void initialization() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("ru.korna.ukpocourse", appContext.getPackageName());
    }


    @Rule
    public ActivityTestRule<MainActivity> rule  = new  ActivityTestRule<>(MainActivity.class);

    @Test
    public void layoutEditTexts(){
        MainActivity activity = rule.getActivity();

        View v1 = activity.findViewById(R.id.editText_string);
        assertThat(v1,notNullValue());
        assertThat(v1, instanceOf(EditText.class));

        View v2 = activity.findViewById(R.id.editText_rule);
        assertThat(v2,notNullValue());
        assertThat(v2, instanceOf(EditText.class));
    }

    @Test
    public void layoutTextViews(){
        MainActivity activity = rule.getActivity();

        View v1 = activity.findViewById(R.id.textView);
        assertThat(v1,notNullValue());
        assertThat(v1, instanceOf(TextView.class));

        View v2 = activity.findViewById(R.id.textView2);
        assertThat(v2,notNullValue());
        assertThat(v2, instanceOf(TextView.class));
    }

    @Test
    public void layoutButton(){
        MainActivity activity = rule.getActivity();

        View v1 = activity.findViewById(R.id.button_action);
        assertThat(v1,notNullValue());
        assertThat(v1, instanceOf(Button.class));
    }

    @Test
    public void layoutEditTextInputDispose(){
        MainActivity activity = rule.getActivity();

        View v0 = activity.findViewById(R.id.button_action);

        activity.runOnUiThread(v0::performClick);


        TextView textView_string = activity.findViewById(R.id.editText_string);
        activity.runOnUiThread(() -> textView_string.setText("string"));
        assertEquals(textView_string.getText().toString(), "");

        TextView textView_rule = activity.findViewById(R.id.editText_rule);
        activity.runOnUiThread(() -> textView_rule.setText("rule"));
        assertNotEquals(textView_rule.getText().toString(), "");

    }

    @Test
    public void layoutResult(){
        MainActivity activity = rule.getActivity();

        View v0 = activity.findViewById(R.id.layout_result);
        assertThat(v0, instanceOf(ConstraintLayout.class));
    }


    @Test
    public void layoutResultAppear(){
        MainActivity activity = rule.getActivity();

        ConstraintLayout constraintLayout = activity.findViewById(R.id.layout_result);

        assertEquals(constraintLayout.getVisibility(), View.INVISIBLE);

        View button = activity.findViewById(R.id.button_action);
        activity.runOnUiThread(button::performClick);

        assertEquals(constraintLayout.getVisibility(), View.VISIBLE);
    }


}
