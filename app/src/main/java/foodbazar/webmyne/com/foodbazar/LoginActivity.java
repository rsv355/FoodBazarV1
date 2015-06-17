package foodbazar.webmyne.com.foodbazar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import foodbazar.webmyne.com.foodbazar.model.AppConstants;
import foodbazar.webmyne.com.foodbazar.model.LoginClass;
import foodbazar.webmyne.com.foodbazar.utils.PostServiceCall;
import foodbazar.webmyne.com.foodbazar.utils.PrefUtils;

public class LoginActivity extends ActionBarActivity {

    private ProgressDialog progressDialog;
    private TextView btnLogin, btnSignUp;
    private EditText etEmail,etPassword;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin= (TextView) findViewById(R.id.btnLogin);

        btnSignUp = (TextView)findViewById(R.id.btnSignUp);

        etEmail= (EditText) findViewById(R.id.etEmail);
        etPassword= (EditText) findViewById(R.id.etPassword);
//        etEmail.setText("nirav@gmail.com");
//        etPassword.setText("123456");
       setToolbar();


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent it = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(it);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etEmail.getText().toString() == null || etPassword.getText().toString() == null){

                    Toast.makeText(getApplicationContext(), "Please fill up the details", Toast.LENGTH_SHORT).show();
                }

                else {
                    callLoginService();
                }
            }
        });
    }
    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {

            toolbar.setTitle("Log In");

            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_login, menu);
//        return true;
//    }

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
    }

    private void callLoginService() {
        progressDialog=new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        JSONObject object=new JSONObject();
        try {

            object.put("EmailAddress",etEmail.getText().toString().trim());
            object.put("Password",etPassword.getText().toString().trim());
            object.put("Roleid","3");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new PostServiceCall(AppConstants.LOGIN,object){

            @Override
            public void response(String response) {
                progressDialog.dismiss();
                LoginClass loginClass= new GsonBuilder().create().fromJson(response, LoginClass.class);
                PrefUtils.setLogin(loginClass, LoginActivity.this);
                Log.e("login response:", loginClass.OwnerId + "");
                Log.e("RESPONSE : ", response);
                finish();
            }

            @Override
            public void error(String error) {
                Log.e("login response:", error + "");
            }
        }.call();

    }

}
