package foodbazar.webmyne.com.foodbazar.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import foodbazar.webmyne.com.foodbazar.R;
import foodbazar.webmyne.com.foodbazar.model.AppConstants;
import foodbazar.webmyne.com.foodbazar.model.Area;
import foodbazar.webmyne.com.foodbazar.model.City;
import foodbazar.webmyne.com.foodbazar.model.CityList;
import foodbazar.webmyne.com.foodbazar.utils.GetServiceCall;
import foodbazar.webmyne.com.foodbazar.utils.PrefUtils;

public class CitySelectionFragment extends Fragment {

    public static final String CLOSE = "Close";
    public static final String BUILDING = "Building";
    private static final int NATIVE_THEME = Integer.MIN_VALUE;
    private int mTheme = -1;
    private  String mCheckedItem;
    private TextView points;
    private ProgressDialog progressDialog;
    private ArrayList<City> cityArrayList;

    private TextView txtChoose;

    private View containerView;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;
    private TextView btnSubmit;

    private Spinner etCity,etArea;

    City city1;

    public static CitySelectionFragment newInstance()
    {
        CitySelectionFragment contentFragment = new CitySelectionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), android.R.drawable.screen_background_light_transparent);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        res = getArguments().getInt(Integer.class.getName());

    }


    @Override
    public void onResume() {
        super.onResume();
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        getCityList();

    }

    private void getCityList() {

        new GetServiceCall(AppConstants.GET_CITY, GetServiceCall.TYPE_JSONOBJECT){

            @Override
            public void response(String response) {

                Log.e("response:", response + "");
                progressDialog.dismiss();

                final CityList cityList = new GsonBuilder().create().fromJson(response, CityList.class);
                cityArrayList=cityList.cityArrayList;

                CitySpinnerAdapter cityAdapter=new CitySpinnerAdapter(getActivity(),cityArrayList);
                etCity.setAdapter(cityAdapter);

                etCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        AreaSpinnerAdapter areaSpinnerAdapter = new AreaSpinnerAdapter(getActivity(), cityArrayList.get(etCity.getSelectedItemPosition()).areaListArrayList);
                        etArea.setAdapter(areaSpinnerAdapter);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                etArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                        PrefUtils.setArea(cityArrayList.get(etCity.getSelectedItemPosition()).areaListArrayList.get(etArea.getSelectedItemPosition()).AreaId + "", getActivity());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


            }

            @Override
            public void error(VolleyError error) {

            }
        }.call();

    }


    public CitySelectionFragment(){}



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.fragment_city_selection, container, false);


        Shimmer.ShimmerTextView txtChoose = (Shimmer.ShimmerTextView)rootView.findViewById(R.id.choose);



        etCity= (Spinner) rootView.findViewById(R.id.etCity);
        etArea= (Spinner) rootView.findViewById(R.id.etArea);
        btnSubmit= (TextView) rootView.findViewById(R.id.btnSubmit);
     //   points= (TextView) rootView.findViewById(R.id.points);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create new fragment and transaction
                Fragment newFragment = HotelListFragment.newInstance();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack if needed
                transaction.replace(R.id.container, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
            }
        });

        return rootView;
    }



    public class CitySpinnerAdapter extends BaseAdapter{

        private final Context activity;
        private ArrayList<City> asr;

        public CitySpinnerAdapter(Context activity, ArrayList<City> asr) {
            this.activity = activity;
            this.asr = asr;
        }


        @Override
        public int getCount() {
            return asr.size();
        }

        @Override
        public Object getItem(int position) {
            return asr.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {

            TextView txt = new TextView(getActivity());
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(18);

            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setText(asr.get(position).cityName);
            txt.setTextColor(Color.parseColor("#000000"));
            return txt;   }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView txt = new TextView(getActivity());
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(18);
//            txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_drop_down, 0);
            txt.setText(asr.get(position).cityName);
            txt.setTextColor(Color.parseColor("#000000"));
            return txt;
        }
    }


    public class AreaSpinnerAdapter extends BaseAdapter{

        private final Context activity;
        private ArrayList<Area> asr;

        public AreaSpinnerAdapter(Context activity, ArrayList<Area> asr) {
            this.activity = activity;
            this.asr = asr;
        }

        @Override
        public int getCount() {
            return asr.size();
        }

        @Override
        public Object getItem(int position) {
            return asr.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {

            TextView txt = new TextView(getActivity());
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(18);
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setText(asr.get(position).AreaName);
            txt.setTextColor(Color.parseColor("#000000"));
            return txt;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView txt = new TextView(getActivity());
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(18);
//            txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_drop_down, 0);
            txt.setText(asr.get(position).AreaName);
            txt.setTextColor(Color.parseColor("#000000"));
            return txt;
        }
    }



}
