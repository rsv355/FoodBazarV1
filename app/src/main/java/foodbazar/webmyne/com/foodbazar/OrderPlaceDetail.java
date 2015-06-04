package foodbazar.webmyne.com.foodbazar;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jaydeeprana on 04-06-2015.
 */
public class OrderPlaceDetail extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.order_place_detail, container, false);

        return rootView;
    }
}
