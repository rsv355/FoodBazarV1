package foodbazar.webmyne.com.foodbazar;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.navdrawer.SimpleSideDrawer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import foodbazar.webmyne.com.foodbazar.fragments.CitySelectionFragment;
import foodbazar.webmyne.com.foodbazar.fragments.HotelListFragment;
import foodbazar.webmyne.com.foodbazar.model.LoginClass;
import foodbazar.webmyne.com.foodbazar.utils.PrefUtils;

public class HomeScreen extends ActionBarActivity {

    private CitySelectionFragment newFragment;

    private TextView name;
    private TextView emailId;

    private Toolbar toolbar;
    private SimpleSideDrawer mNav;
    private ExpandableListView listDrawer;
    ExpandableListAdapter listAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    LoginClass loginClass;


    String color_names[] = {"Select Location", "Select Hotel",  "Order History", "User Wallet", "Contact Us", "Log Out"};
    Integer image_id[] = {R.drawable.ic_maps_local_pizza, R.drawable.ic_maps_local_restaurant,  R.drawable.ic_action_assignment, R.drawable.ic_action_account_balance_wallet, R.drawable.ic_communication_ring_volume, R.drawable.ic_communication_ring_volume};


   // private int iconGroup[] = {R.drawable.ic_action_room,R.drawable.ic_action_account_balance,R.drawable.ic_action_assignment,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

         newFragment = CitySelectionFragment.newInstance();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

        Customlistadapter adapter = new Customlistadapter(this, image_id, color_names);



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.mipmap.ic_action_menu);
            toolbar.setTitle("Food Baazar");
            setSupportActionBar(toolbar);
        }
        mNav = new SimpleSideDrawer(this);
        mNav.setLeftBehindContentView(R.layout.drawer_layout);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNav.toggleLeftDrawer();
            }
        });




        emailId = (TextView)findViewById(R.id.signInEmailId);

        loginClass = PrefUtils.getLogin(HomeScreen.this);

        if (loginClass != null){

            emailId.setText(loginClass.loginObject.EmailAddress);

        }

        else {

            emailId.setText("Click for Sign In");
        }

        ListView lv = (ListView) findViewById(R.id.listDrawer);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                mNav.closeLeftSide();

                switch (position){



                    case 0:   CitySelectionFragment newFragment = CitySelectionFragment.newInstance();

                        FragmentTransaction transaction = getFragmentManager().beginTransaction();

                        // Replace whatever is in the fragment_container view with this fragment,
                        // and add the transaction to the back stack if needed
                        transaction.replace(R.id.container, newFragment);
                        transaction.addToBackStack(null);

                        // Commit the transaction
                        transaction.commit();

                        break;

                    case 1:

                        Fragment newFragment1 = HotelListFragment.newInstance();

                        FragmentTransaction transaction1 = getFragmentManager().beginTransaction();

                        // Replace whatever is in the fragment_container view with this fragment,
                        // and add the transaction to the back stack if needed
                        transaction1.replace(R.id.container, newFragment1);
                        transaction1.addToBackStack(null);

                        // Commit the transaction
                        transaction1.commit();


                        break;


                    case 2 :

                        break;

                    case 3 :

                        Intent newActivity1 = new Intent(HomeScreen.this, WalletActivity.class);
                        startActivity(newActivity1);

                        break;

                    case 4 :

                        Intent newActivity2 = new Intent(HomeScreen.this, ContactActivity.class);
                        startActivity(newActivity2);
                        break;

                    case 5 :

                        PrefUtils.clearLogin(HomeScreen.this);

                        emailId.setText(loginClass.loginObject.EmailAddress);


//                        Intent newActivity3 = new Intent(HomeScreen.this, LoginActivity.class);
//                        startActivity(newActivity3);
                        break;




                }


            }
        });


        // preparing list data
        prepareListData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
//        listDrawer.setAdapter(listAdapter);

    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("HOME"); //0
        listDataHeader.add("PROFILE"); //1
        listDataHeader.add("FIND FRIENDS"); //2
        listDataHeader.add("REFER AND ENJOY"); //3
        listDataHeader.add("SPORT DIARIES"); //4
        listDataHeader.add("SUGGEST"); //5


        List<String> profile = new ArrayList<String>();
        profile.add("PERSONAL PROFILE");
        profile.add("SPORT PROFILE");


        List<String> sport_diaries = new ArrayList<String>();
        sport_diaries.add("TEAMS");
        sport_diaries.add("PLAYERS");
        sport_diaries.add("CENTERS");
        sport_diaries.add("SHOPS");
        sport_diaries.add("EVENTS");
        sport_diaries.add("NEWS FEED");

        List<String> suggest = new ArrayList<String>();
        suggest.add("CENTER");
        suggest.add("SHOP");


        listDataChild.put(listDataHeader.get(0), new ArrayList<String>()); // Header, Child data
        listDataChild.put(listDataHeader.get(1), profile);
        listDataChild.put(listDataHeader.get(2), new ArrayList<String>());
        listDataChild.put(listDataHeader.get(3),  new ArrayList<String>());
        listDataChild.put(listDataHeader.get(4), sport_diaries);
        listDataChild.put(listDataHeader.get(5), suggest);
    }

    public class ExpandableListAdapter extends BaseExpandableListAdapter {
        private Context _context;
        private List<String> _listDataHeader; // header titles
        // child data in format of header title, child title
        private HashMap<String, List<String>> _listDataChild;

        public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                     HashMap<String, List<String>> listChildData) {
            this._context = context;
            this._listDataHeader = listDataHeader;
            this._listDataChild = listChildData;
        }

        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .get(childPosititon);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            final String childText = (String) getChild(groupPosition, childPosition);

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_item, null);
            }

            TextView txtListChild = (TextView) convertView
                    .findViewById(R.id.lblListItem);

            txtListChild.setText(childText);
            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return this._listDataHeader.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return this._listDataHeader.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            String headerTitle = (String) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_group, null);
            }

            ImageView imgIndicator = (ImageView)convertView.findViewById(R.id.imgListIndicator);
            ImageView imgIcon = (ImageView)convertView.findViewById(R.id.imgIconGroup);
        //    imgIcon.setImageResource(iconGroup[groupPosition]);

            if(groupPosition == 1 || groupPosition == 4 || groupPosition == 5){
                imgIndicator.setVisibility(View.VISIBLE);
            }else{
                imgIndicator.setVisibility(View.INVISIBLE);
            }

            if(isExpanded){
                imgIndicator.setImageResource(R.drawable.ic_action_keyboard_arrow_up);




            }else{
                imgIndicator.setImageResource(R.drawable.ic_action_keyboard_arrow_down);

            }

            TextView lblListHeader = (TextView) convertView
                    .findViewById(R.id.lblListHeader);
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle);

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }





/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/




    public class Customlistadapter extends ArrayAdapter<String> {
        String[] color_names;
        Integer[] image_id;
        Context context;
        public Customlistadapter(Activity context,Integer[] image_id, String[] text){
            super(context, R.layout.drawer_list_item, text);
            // TODO Auto-generated constructor stub
            this.color_names = text;
            this.image_id = image_id;
            this.context = context;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View single_row = inflater.inflate(R.layout.drawer_list_item, null,
                    true);
            TextView textView = (TextView) single_row.findViewById(R.id.textView);
            ImageView imageView = (ImageView) single_row.findViewById(R.id.imageView);
            textView.setText(color_names[position]);
            imageView.setImageResource(image_id[position]);
            return single_row;
        }
    }


}
