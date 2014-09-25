package com.dreamteam.translator.translator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SearchField extends Activity {

    Button searchButton;
    EditText searchField;
    Intent searchIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_field);
        searchButton = (Button) findViewById(R.id.search_button);
        searchField = (EditText) findViewById(R.id.search_field);
        searchIntent = new Intent(this, LoadingScreen.class);
    }

    public void sendMessage(View view) {
        String query = searchField.getText().toString();
        Log.i("QUERY MSG", query);
        searchIntent.putExtra("query", query);
        startActivity(searchIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
