package com.adarsh.maskinput;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EdgeEffect;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText timeField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeField = findViewById(R.id.edittime);


        timeField.addTextChangedListener(new TextWatcher() {
            String beforeTXT;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                Log.i("WWWWWWWWWWWWW", " BEFORE : " + s + " and " + start + " and " + count + "and " + after + " BEFORETXT " + beforeTXT);
                beforeTXT = "" + s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int input;
                boolean alpha = false;
                if (s.toString().length() >= 1) {
                    String[] array = s.toString().split("");
                    for (int i = 0; i < array.length; i++) {
                        if (array[i].matches("[a-zA-Z]")) {
                            Log.d("regex", "matches :" + array[i] + " in " + s.toString());
                            alpha = true;
                        }


                    }

                }

                if (!alpha) {
                    if (!s.toString().matches("[a-zA-Z]")) {
                        //first determine whether user is at hrs side or min side
                        if (s.toString().equals("")) {
                            return;
                        }
                        if (s.toString().length() > 2 && start <= 2) { //means the user is at hour side
                            input = Integer.parseInt(s.toString().substring(0, 1)) % 10;

                        } else if (s.toString().length() > 2 && start >= 3) {//means that user is at min side
                            input = Integer.parseInt("0" + s.toString().substring(3)) % 10;

                        } else if (s.toString().indexOf(":") == 1) { // if we have for eg 1: or 0: then we take first character for parsing
                            input = Integer.parseInt(s.toString().charAt(0) + "");
                        } else { //else it is default where the user is at first position
                            input = Integer.parseInt(s.toString()) % 10;
                        }

                        //Special case where 00: is autommatically converted to 12: in 12hr time format
                        if (s.toString().contains("00:")) {
                            Log.i("INsisde )))", "i am called ");
                            timeField.setText("12:");
                            return;
                        }

                        if(beforeTXT.contains(":") && s.toString().length()==2)
                        {

                            timeField.setText("");
                        }



                        //Now we manipulate the input and its formattin and cursor movement
                        if (input <= 1 && start == 0) { //thiis is for first input value to check .... time shouldnt exceed 12 hr
                            //do nothing
                        } else if (input > 2 && start == 0 && !s.toString().startsWith("1")) { //if at hour >1 is press then automaticc set the time as 02: or 05: etc
                            timeField.setText("0" + s + ":");
                        } else if (input > 3 && start == 1 && !s.toString().startsWith("0") && s.toString().startsWith("2")) { //whe dont have greater than 12 hrs so second postionn shouldn't exceed value 2
                            timeField.setText(beforeTXT);
                        } else if (start == 1 && !s.toString().startsWith("0") && s.toString().startsWith("1") && !s.toString().contains(":") && beforeTXT.length() < s.toString().length()) { //whe dont have greater than 12 hrs so second postionn shouldn't exceed value 2
                            timeField.setText(s.toString() + ":");
                        } else if (start == 1 && s.toString().startsWith("1")) { //whe dont have greater than 12 hrs so second postionn shouldn't exceed value 2
                            timeField.setText(s.toString());
                        }
                        else if (start == 1 && !beforeTXT.contains(":"))
                        {  //if valid input 10 or 11 or 12 is given then convert it to 10: 11: or 12:
                            timeField.setText(s.toString() + ":");

                            if (s.toString().length() == 1 && s.toString().startsWith("2")) {
                                timeField.setText("");
                            }
                            if (s.toString().length() == 2 && s.toString().startsWith("1")) {
                                timeField.setText("");
                            }
                            if (s.toString().startsWith("1") && s.toString().length() == 1 && s.toString().contains(":")) { //on back space convert 1: to 01:
                                timeField.setText("");
                            }

                        }





                        else if (start == 3 && input > 5) { //min fig shouldn't exceed 59 so ...if at first digit of min input >5 then do nothing or codpy the earlier text
                            timeField.setText(beforeTXT);
                        } else if (start > 4 && s.toString().length() > 5) { // the total string lenght shouldn't excced 5
                            timeField.setText(beforeTXT);
                        } else if (start < 2 && beforeTXT.length() > 2 && alpha) {
                            timeField.setText(beforeTXT);

                        }

                    }
                } else
                    {
//                        if(s.toString().length()==1)
//                        {
//
//                            timeField.setText("");
//                        }
                    timeField.setText(beforeTXT);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("after  TEXT TEXXT", " this : " + s);
                timeField.setSelection(timeField.getText().toString().length());
            }
        });

    }
}
