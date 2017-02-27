package com.example.yara.recyclerview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    MoviesAdapter adapter ;
    List<Movie> moviesList = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView) ;

        prepareMovieData();
        adapter = new MoviesAdapter(moviesList) ;

        prepareRecyclerView();



    }

    private void prepareMovieData() {
        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015");
        moviesList.add(movie);

        movie = new Movie("Inside Out", "Animation, Kids & Family", "2015");
        moviesList.add(movie);

        movie = new Movie("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        moviesList.add(movie);

        movie = new Movie("Shaun the Sheep", "Animation", "2015");
        moviesList.add(movie);

        movie = new Movie("The Martian", "Science Fiction & Fantasy", "2015");
        moviesList.add(movie);

        movie = new Movie("Mission: Impossible Rogue Nation", "Action", "2015");
        moviesList.add(movie);

        movie = new Movie("Up", "Animation", "2009");
        moviesList.add(movie);

        movie = new Movie("Star Trek", "Science Fiction", "2009");
        moviesList.add(movie);

        movie = new Movie("The LEGO Movie", "Animation", "2014");
        moviesList.add(movie);

        movie = new Movie("Iron Man", "Action & Adventure", "2008");
        moviesList.add(movie);

        movie = new Movie("Aliens", "Science Fiction", "1986");
        moviesList.add(movie);

        movie = new Movie("Chicken Run", "Animation", "2000");
        moviesList.add(movie);

        movie = new Movie("Back to the Future", "Science Fiction", "1985");
        moviesList.add(movie);

        movie = new Movie("Raiders of the Lost Ark", "Action & Adventure", "1981");
        moviesList.add(movie);

        movie = new Movie("Goldfinger", "Action & Adventure", "1965");
        moviesList.add(movie);

        movie = new Movie("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        moviesList.add(movie);
    }

    private void prepareRecyclerView ()
    {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext()) ;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        prepareSwipe();
    }

    private void prepareSwipe()
    {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0 , ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT)
        {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.onItemRemove(viewHolder,recyclerView);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon ;
                Paint p = new Paint() ;

                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE)
                {
                    View itemView = viewHolder.itemView;

                    float height = (float)itemView.getBottom() - (float)itemView.getTop();
                    float width = height / 3;

                    if (dX > 0)
                    {
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
                        c.drawRect(background, p);
                        icon = BitmapFactory.decodeResource(getResources(),R.drawable.true_icon);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);

                    }
                    else if (dX < 0)
                    {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background, p);
                        icon = BitmapFactory.decodeResource(getResources() , R.drawable.delete_icon);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
