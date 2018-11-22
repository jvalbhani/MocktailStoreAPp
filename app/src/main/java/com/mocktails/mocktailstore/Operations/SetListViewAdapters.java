package com.mocktails.mocktailstore.Operations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mocktails.mocktailstore.Entities.Mocktail;
import com.mocktails.mocktailstore.Entities.Rating;
import com.mocktails.mocktailstore.HomeActivity;
import com.mocktails.mocktailstore.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class SetListViewAdapters extends BaseAdapter {

    private Mocktail[] mctList;
    private Context context;

    public SetListViewAdapters(Context context, Mocktail[] mctList)
    {
        this.context = context;
        this.mctList = mctList;
    }

    @Override
    public int getCount() {
        return mctList.length;
    }

    @Override
    public Object getItem(int i) {
        return mctList[i];
    }

    @Override
    public long getItemId(int i) {
        return mctList[i].getId();
    }

    public int x = 0;
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).
                    inflate(R.layout.mocktail_list_view, viewGroup, false);
        }
        final TextView mockName = (TextView) view.findViewById(R.id.textViewMocktail);
        final RatingBar mockRating = (RatingBar) view.findViewById(R.id.ratingBarMocktail);
        final ImageView mockImage = (ImageView) view.findViewById(R.id.imageViewMocktail);
        final TextView ratingCount = (TextView) view.findViewById(R.id.ratingCount);
        final Mocktail mocktail = mctList[i];
        final TextView mockTag = (TextView) view.findViewById(R.id.mocktailTagLine);
        mockName.setText(mocktail.getName());
        float rating = 0;
        int count = 0;
        for(Rating x : mocktail.getRatings())
        {
            rating += x.getStar();
            count++;
        }
        mockRating.setRating( rating / (float)count);
        final Bitmap image[] = new Bitmap[1];
        try {
            new AsyncTask<String, Void, Void>(){

                @Override
                protected Void doInBackground(String... strings) {
                    try {
                        image[0] = BitmapFactory.decodeStream((InputStream) new URL(strings[0]).getContent());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }.execute(mocktail.getImageUrl()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mockImage.setImageBitmap(image[0]);
        ratingCount.setText(count+"");
        mockTag.setText(mocktail.getTagLine());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SetAppView().setMocktailView((FragmentActivity) context);
//                Snackbar.make(v,"Item Selected - " + mocktail.getName(), Snackbar.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
