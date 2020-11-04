package app.powered.by.google.jetpackpaging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    Boolean isScrolling  = false;
    int currentItems,totalItems,scrollOutItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        ItemViewModel itemViewModel;
        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);




        final ItemAdapter adapter = new ItemAdapter(this);

        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<Item>>() {
            @Override
            public void onChanged(@Nullable PagedList<Item> items) {
                adapter.submitList(items);

            }
        });

        recyclerView.setAdapter(adapter);



//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//
//                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
//                    isScrolling= true;
//                }
//
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                currentItems = layoutManager.getChildCount();
//                totalItems = layoutManager.getItemCount();
//                scrollOutItems = layoutManager.findFirstVisibleItemPosition();
//
//                if(isScrolling && (currentItems+scrollOutItems==totalItems)){
//                    isScrolling = false;
//
//
//                    ItemDataSource itemDataSource= new ItemDataSource();
//
//
//                    ItemDataSource.FIRST_PAGE =+1;
//
//                }
//
//
//            }
//        });


//        Call<StackApiResponse> call=
//                RetrofitClient.getInstance()
//                .getApi()
//                .getAnswers(1,50,"stackoverflow");
//
//
//        call.enqueue(new Callback<StackApiResponse>() {
//            @Override
//            public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {
//                StackApiResponse apiResponse= response.body();
//
//                Toast.makeText(MainActivity.this, String.valueOf(apiResponse.items.size() ), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<StackApiResponse> call, Throwable t) {
//
//            }
//        });
    }
}
