package com.aaronyamil.retrofittutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aaronyamil.retrofittutorial.model.RandomUser;
import com.aaronyamil.retrofittutorial.model.User;
import com.aaronyamil.retrofittutorial.service.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ImageView pictureUser;
    TextView nameUser;
    TextView emailUser;
    TextView genderUser;

    Button newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pictureUser = (ImageView) findViewById(R.id.ivPicture);
        nameUser = (TextView) findViewById(R.id.tvName);
        emailUser = (TextView) findViewById(R.id.tvEmail);
        genderUser = (TextView) findViewById(R.id.tvGender);

        newUser = (Button) findViewById(R.id.btNewUser);

        newUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getUser();
            }
        });

    }

    private void getUser() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Service service = retrofit.create(Service.class);
        Call<RandomUser> call = service.GetUserData();

        call.enqueue(new Callback<RandomUser>() {
            @Override
            public void onResponse(Call<RandomUser> call, Response<RandomUser> response) {
                RandomUser result = response.body();
                List<User> listUsers= result.getResults();

                User user = listUsers.get(0);

                nameUser.setText(user.getName().getFirst() + ' ' + user.getName().getLast());
                emailUser.setText(user.getEmail());
                genderUser.setText(user.getGender());

                Picasso.with(getBaseContext()).load(user.getPicture().getMedium()).into(pictureUser);
                Toast.makeText(MainActivity.this,"New User",Toast.LENGTH_LONG);
            }

            @Override
            public void onFailure(Call<RandomUser> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG);
            }
        });
    }
}
