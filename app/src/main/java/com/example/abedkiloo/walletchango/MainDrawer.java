package com.example.abedkiloo.walletchango;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    //a list to store all the products
    List<Projects> projectsList = new ArrayList<>();

    //the recyclerview
    RecyclerView recyclerView;

    ProjectAdapter adapter;

    Projects projects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), CreateFundRequest.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //initializing the projectlist
        projectsList = new ArrayList<>();

//getting the recyclerview from xml
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProjectAdapter(getApplicationContext(), projectsList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

        getProjects();


//        //adding some items to our list
//        projectsList.add(
//                new Projects(
//                        "1",
//                        "Apple MacBook",
//                        "Apple MacBook sdgjljmsg jsdlfjgmlsjdg",
//                        1300,
//                        2600
//                ));
//        projectsList.add(
//                new Projects(
//                        "1",
//                        "Share Listing",
//                        "Share is the public amout of sahres ",
//                        1700,
//                        2600
//                ));
//        projectsList.add(
//                new Projects(
//                        "1",
//                        "Stroer Listing",
//                        "Share is the public amout of sahres ",
//                        7800,
//                        8600
//                ));


    }

    private void getProjects() {
        Log.e("CALL", "MAKING");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();


        //creating the api interface
        Api api = retrofit.create(Api.class);

        //now making the call object
        //Here we are using the api method that we created inside the api interface
        Call<List<Projects>> call = api.getProjects();

        call.enqueue(new Callback<List<Projects>>() {
            @Override
            public void onResponse(Call<List<Projects>> call, Response<List<Projects>> response) {

                //In this point we got our Projects list
                //thats damn easy right ;) 
                List<Projects> projectsList2 = response.body();

                Projects projects2 ;

                for (int i = 0; i < projectsList2.size(); i++) {
                    projects2 = new Projects();
                    projects2.setId(projectsList2.get(i).getId());
                    projects2.setProject_name(projectsList2.get(i).getProject_name());
                    projects2.setProject_description(projectsList2.get(i).getProject_description());
                    projects2.setAmount_deposited(projectsList2.get(i).getAmount_deposited());
                    projects2.setTarget_investment(projectsList2.get(i).getTarget_investment());
                    projectsList.add(projects2);

                }

                adapter.notifyDataSetChanged();

                //creating recyclerview adapter



                //now we can do whatever we want with this list

            }

            @Override
            public void onFailure(Call<List<Projects>> call, Throwable t) {
                Log.e("RETROFIT_ERROR", t.toString());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_wallet) {
            startActivity(new Intent(getApplicationContext(), Wallet.class));
        } else if (id == R.id.nav_profile) {
            startActivity(new Intent(getApplicationContext(), Profile.class));

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
