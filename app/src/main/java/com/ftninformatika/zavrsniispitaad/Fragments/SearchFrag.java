package com.ftninformatika.zavrsniispitaad.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ftninformatika.zavrsniispitaad.Adapters.SearchAdapter;
import com.ftninformatika.zavrsniispitaad.Model.Movie;
import com.ftninformatika.zavrsniispitaad.Model.Search;
import com.ftninformatika.zavrsniispitaad.Network.RESTservice;
import com.ftninformatika.zavrsniispitaad.R;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class SearchFrag extends Fragment {

    public static class SearchFragment extends Fragment {

        private EditText etSearch;
        private Button bSearch;
        private ListView lvMovies;

        private onListItemClickListener listener;

        public SearchFragment() {
        }


        private void showMovies(List<Search> movies) {
            if (movies != null) {
                SearchAdapter adapter = new SearchAdapter(movies, getActivity());
                lvMovies.setAdapter(adapter);
                lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        listener.onListItemClicked(adapter.getItem(position).getImdbID());
                    }
                });
            }
        }

        private void getMoviesByTitle(String title) {
            HashMap<String, String> query = new HashMap<>();
            query.put("apikey", RESTservice.API_KEY);
            query.put("s", title);

            Call<Movie> call = RESTservice.apiInterface().getMovieByActor(query);
            call.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    if (response.code() == 200) {
                      // showMovies(response.body().getWriter());
                    }
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_search, container, false);

            etSearch = view.findViewById(R.id.editText_Search);
            bSearch = view.findViewById(R.id.buttonSearch);
            lvMovies = view.findViewById(R.id.listMovies);

            bSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!etSearch.getText().toString().equals("")) {

                        getMoviesByTitle(etSearch.getText().toString().trim());

                    } else {
                        Toast.makeText(getActivity(), getString(R.string.search_error), Toast.LENGTH_SHORT).show();
                    }
                }
            });

            return view;
        }
        public void onAttach(@NonNull Context context) {
            super.onAttach(context);
            if (context instanceof onListItemClickListener) {
                listener = (onListItemClickListener) context;
            } else {
                Toast.makeText(getActivity(), "Morate implementirati interface", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onDetach() {
            super.onDetach();
            listener = null;
        }

        public interface onListItemClickListener {
            void onListItemClicked(String ImdbID);
        }

    }
}
