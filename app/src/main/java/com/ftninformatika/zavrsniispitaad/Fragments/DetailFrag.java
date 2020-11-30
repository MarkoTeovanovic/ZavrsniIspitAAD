package com.ftninformatika.zavrsniispitaad.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.ftninformatika.zavrsniispitaad.MainActivity;
import com.ftninformatika.zavrsniispitaad.Model.Movie;
import com.ftninformatika.zavrsniispitaad.Network.RESTservice;
import com.ftninformatika.zavrsniispitaad.R;
import com.squareup.picasso.Picasso;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailFrag extends Fragment {
    
    private String ID;
    
    private TextView etTitle, etYear, etRelaseDate, etGenre, etDirector, etActors, etPlot, etCountry;
    private ImageView imageView;
    
    private Movie movie;
    
    public static final String NOTIF_CHANEL_ID = "moj notif kanal";
    public static final int NOTIF_ID = 1;
    
    public DetailFrag () {}

    public void setID(String ID) {
        this.ID = ID;
    }
    
    private void showMovies (Movie movie) {
        this.movie = movie;
        
        etActors.setText((CharSequence) movie.getActors());
        etCountry.setText(movie.getCountry());
        etDirector.setText(movie.getDirector());
        etGenre.setText(movie.getGenre());
        etPlot.setText(movie.getPlot());
        etRelaseDate.setText(movie.getReleased());
        etTitle.setText(movie.getTitle());
        etYear.setText(movie.getYear());

        Picasso.get().load(movie.getPoster()).into(imageView);
    } 
    private void moviesByID () {
        HashMap<String, String> query = new HashMap<>();
        query.put("apikey", RESTservice.API_KEY);
        query.put("i", ID);

        Call<Movie> call = RESTservice.apiInterface().getMovieDetails(query);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.code() == 200) {
                    showMovies(response.body());
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_details, container, false);
        setHasOptionsMenu(true);

        etTitle = view.findViewById(R.id.textView_Title);
        etYear = view.findViewById(R.id.textView_Year);
        etRelaseDate = view.findViewById(R.id.textView_Released);
        etGenre = view.findViewById(R.id.textView_Genre);
        etDirector = view.findViewById(R.id.textView_Director);
        etActors = view.findViewById(R.id.textView_Actors);
        etPlot = view.findViewById(R.id.textView_Plot);
        etCountry = view.findViewById(R.id.textView_Country);

        imageView = view.findViewById(R.id.imageView_Poster);

        moviesByID();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_details, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add:
                doAddElement();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void showNotif (boolean exist) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), NOTIF_CHANEL_ID);
        if (exist) {
            builder.setContentTitle("Favourites")
                    .setContentText("Film nije dodat u fav.")
                    .setSmallIcon(R.drawable.icon);
        } else {
            builder.setContentTitle("Favourites")
                    .setContentText("Film je uspesno dodat u fav.")
                    .setSmallIcon(R.drawable.icon);
        }
    }
    private void doAddElement () {
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            boolean enabledNotifications = sharedPreferences.getBoolean("notification_settings", true);

            List<Movie> moviesindb = ((MainActivity) getActivity()).getDatabaseHelper().getMovieDao().queryForAll();
            boolean exist = false;
            for (int i = 0; i < moviesindb.size(); i++) {
                if (moviesindb.get(i).getImdbID().equals(ID)) {
                    exist = true;
                    break;
                }
            }

            if (enabledNotifications) {
                //ovde obvezno pozovemo showNotification
                showNotif(exist);
            }

            if (exist) {
                if (!enabledNotifications) {
                    Toast.makeText(getActivity(), getString(R.string.add_favorites_unsuccess), Toast.LENGTH_SHORT).show();
                }
            } else {
                if (!enabledNotifications) {
                    Toast.makeText(getActivity(), getString(R.string.add_favorites_success), Toast.LENGTH_SHORT).show();
                }
                ((MainActivity) getActivity()).getDatabaseHelper().getMovieDao().create(movie);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
    
