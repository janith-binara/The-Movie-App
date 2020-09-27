package com.janithbinara.themovie.ui.main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.janithbinara.themovie.CustomAdapter;
import com.janithbinara.themovie.GetDataService;
import com.janithbinara.themovie.RetrofitClientInstance;
import com.janithbinara.themovie.R;
import com.janithbinara.themovie.models.Films;
import com.janithbinara.themovie.models.Result;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private PageViewModel pageViewModel;
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        recyclerView = root.findViewById(R.id.customRecyclerView);

        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        pageViewModel.getText().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer s) {
                textView.setText(String.valueOf(s));

                if (s==1)
                {
                    getPopularList();
                }
                if (s==2)
                {
                    getUpcomingList();
                }

            }
        });
        return root;
    }

    private void getPopularList()
    {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().
                create(GetDataService.class);
        Call<Films> call = service.
                getPopularList("21cf9e58fa9fb18d1769658101c2fa34","1");
        call.enqueue(new Callback<Films>() {
            @Override
            public void onResponse(Call<Films> call, Response<Films> response) {
                progressDoalog.dismiss();
                generateDataList(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Films> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(getActivity(),
                        "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

            }

        });
    }

    private void getUpcomingList()
    {
        GetDataService service = RetrofitClientInstance.
                getRetrofitInstance().create(GetDataService.class);
        Call<Films> call = service.
                getUpcomingList("21cf9e58fa9fb18d1769658101c2fa34","1");
        call.enqueue(new Callback<Films>() {
            @Override
            public void onResponse(Call<Films> call, Response<Films> response) {
                progressDoalog.dismiss();
                generateDataList(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Films> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(getActivity(), "Something went wrong...Please try later!",
                        Toast.LENGTH_SHORT).show();

            }

        });
    }

    private void generateDataList(List<Result> data) {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().
                getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new CustomAdapter(getActivity().getApplicationContext(),data,-111);
        recyclerView.setAdapter(adapter);

    }


}