package com.greatwideweb.youtube.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.greatwideweb.mock.UITestDataProvider;
import com.greatwideweb.youtube.ui.support.PicassoCardThumbnail;
import com.greatwideweb.youtube.vo.VideoVO;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardGridArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.view.CardGridView;


/**
 * Created by dave on 5/12/2016.
 */
public class SubscriptionMainFragment extends Fragment {

    List<VideoVO> mockVideos  = null;
    UITestDataProvider testDataProvider  = new UITestDataProvider();
    ArrayList<Card> cards = new ArrayList<Card>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mockVideos = (ArrayList<VideoVO>)getArguments().getSerializable("videos");

    }

    @Override
    public void onPause() { super.onPause(); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        cards = new ArrayList<Card>();
        for(VideoVO v : mockVideos) {
            //Create a Card
            Card card = new Card(getContext());

            //Create a CardHeader
            CardHeader header = new CardHeader(getContext());
            header.setTitle(v.getTitle());
            //Add Header to card
            card.addCardHeader(header);


            PicassoCardThumbnail thumbnail = new PicassoCardThumbnail(getContext());
            thumbnail.setUrlResource(v.getDefaultImageImage().getURL());
            card.addCardThumbnail(thumbnail);
            cards.add(card);
        }
        CardGridArrayAdapter cardArrayAdapter = new CardGridArrayAdapter(getContext(),cards);
        View v  = inflater.inflate(R.layout.video_card_grid, container, false);
        CardGridView cardGridView = (CardGridView) v.findViewById(R.id.myGrid);
        cardGridView.setAdapter(cardArrayAdapter);
        return v;
    }

}
