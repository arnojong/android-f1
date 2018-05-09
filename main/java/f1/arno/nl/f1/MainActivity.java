package f1.arno.nl.f1;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static f1.arno.nl.f1.Constants.LOG_TAG;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Requesting a string response from the provided URL
        StringRequest strReq = new StringRequest(Request.Method.GET,
                Constants.URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    // Parsing JSON until we're in the right table
                    JSONObject mrData = new JSONObject(response);
                    mrData = mrData.getJSONObject("MRData");
                    JSONObject driversTable = mrData.getJSONObject("DriverTable");
                    JSONArray drivers = driversTable.getJSONArray("Drivers");

                    // Looping through the Array to collect all Driver information
                    Driver[] driver_array = new Driver[drivers.length()];
                    for (int i = 0; i < drivers.length(); i ++) {
                        JSONObject driver = drivers.getJSONObject(i);

                        String givenName = driver.getString("givenName");
                        String familyName = driver.getString("familyName");
                        String nationality = driver.getString("nationality");
                        String code = driver.getString("code");
                        String permanentNumber = driver.getString("permanentNumber");
                        String driverId = driver.getString("driverId");

                        // Adding the driver object to the driver array
                        Driver driver_object = new Driver(givenName, familyName, nationality, code, permanentNumber, driverId);
                        driver_array[i] = driver_object;

                    }

                    GridView gridView = findViewById(R.id.gridview);
                    final DriversAdapter driversAdapter = new DriversAdapter(getApplicationContext(), driver_array);
                    gridView.setAdapter(driversAdapter);

                    // Adding an onClick to the GridView and starting the intent with the right driver
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                            Driver driver = (Driver)driversAdapter.getItem(position);
                            Intent intent = new Intent(getApplicationContext(), InformationActivity.class);
                            intent.putExtra("driver", driver);
                            startActivity(intent);
                        }
                    });
                }
                catch (JSONException e) {
                    Log.e(LOG_TAG,"String Request Error");
                }

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        // Adding the request to the request queue
        AppController.getInstance().addToRequestQueue(strReq);
    }

    // Building the settings menu
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // Launching the settings activity
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
