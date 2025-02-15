package com.example.tipcalculator;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View; // Import the View class
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    private String billAmountString ="";
    private float tipPercent = 0.15f;

    private EditText billAmountEditText;
    private TextView percentTextView;
    private Button percentUpButton;
    private Button percentDownButton;
    private TextView tiptTextView;
    private TextView totalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Setup Toolbar as the Action Bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // This enables the toolbar as ActionBar

        billAmountEditText = findViewById(R.id.billAmountEditText);
        percentTextView = findViewById(R.id.percentTextView);
        percentUpButton = findViewById(R.id.percentDownButton);
        percentDownButton = findViewById(R.id.percentUpButton);
        tiptTextView = findViewById(R.id.tipTextView);
        totalTextView = findViewById(R.id.totalTextView);

        // Set up listeners
        billAmountEditText.setOnEditorActionListener(this);
        percentUpButton.setOnClickListener(this);
        percentDownButton.setOnClickListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("billAmountString", billAmountString);
        outState.putFloat("tipPercent", tipPercent);
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null){
            billAmountString = savedInstanceState.getString("billAmountString", "");
            tipPercent = savedInstanceState.getFloat("tipPercent", 0.15f);

//            billAmountEditText.setText(billAmountString);
            calculateAndDisplay();
        }

    }

    // Handle action on EditText (like "done" button in keyboard)
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//        if (actionId == EditorInfo.IME_ACTION_DONE) {
//            // Handle the action when "done" is clicked on the keyboard
//            String billAmount = billAmountEditText.getText().toString();
//            Toast.makeText(this, "Bill Amount: " + billAmount, Toast.LENGTH_SHORT).show();
//            return true;
//        }
        calculateAndDisplay();
        return false;
    }
    public void calculateAndDisplay(){
        billAmountString = billAmountEditText.getText().toString();
        float billAmount;
        if(billAmountString.equals("")){
            billAmount =0.0f;
        }else{
            billAmount = Float.parseFloat(billAmountString);
        }

        //calculate tip and total
        float tipAmount = billAmount* tipPercent;
        float totalAmount = billAmount + tipAmount;

        //display the result with formatting
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tiptTextView.setText(currency.format(tipAmount));
        totalTextView.setText(currency.format(totalAmount));


        NumberFormat percent = NumberFormat.getPercentInstance();
        percentTextView.setText(percent.format(tipPercent));

    }
    // Handle clicks on the buttons
    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.percentUpButton:
//                // Increase the tip percentage
//                tipPercent = tipPercent + 0.01f;
//                break;
//            case R.id.percentDownButton:
//                // Decrease the tip percentage
//                tipPercent = tipPercent -0.01f;
//                break;
//            default:
//                break;
//        }

        if(v.getId()== R.id.percentUpButton){
            tipPercent = tipPercent +0.01f;
            calculateAndDisplay();
        }else if(v.getId() == R.id.percentDownButton){
            tipPercent = tipPercent - 0.01f;
            calculateAndDisplay();
        }
    }
}
