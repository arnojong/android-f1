package f1.arno.nl.f1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        // Receiving the sent intent
        Intent i = getIntent();

        // Getting the passed driver data from Serializable
        Driver driver = (Driver) i.getSerializableExtra("driver");

        // Loading the preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        TextView name = findViewById(R.id.name);
        // Is the preference switch on? Show the name
        if (preferences.getBoolean("givenName_switch",true)){
            name.setText(driver.getGivenName() + " " + driver.getFamilyName());
        // If not, hide it
        } else {
            name.setVisibility(View.GONE);
        }

        TextView nationality = findViewById(R.id.nationality);
        if (preferences.getBoolean("nationality_switch",true)) {
            nationality.setText(driver.getNationality());
        } else {
            nationality.setVisibility(View.GONE);
        }

        TextView code = findViewById(R.id.code);
        if (preferences.getBoolean("code_switch",true)) {
            code.setText(driver.getCode());
        } else {
            code.setVisibility(View.GONE);
        }

        TextView permanentNumber = findViewById(R.id.permanentNumber);
        if (preferences.getBoolean("number_switch",true)) {
            permanentNumber.setText(driver.getPermanentNumber());
        } else {
            permanentNumber.setVisibility(View.GONE);
        }

        ImageView image = findViewById(R.id.imageView);
        image.setImageResource(DriversAdapter.getStringIdentifier(getApplicationContext(), driver.getDriverId()));
    }

}

