package foodbazar.webmyne.com.foodbazar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SplashActivity extends Activity {



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);


		Shimmer.ShimmerTextView txtFoodBazar = (Shimmer.ShimmerTextView)findViewById(R.id.txt_splash);
		//txtFoodBazar = (TextView)findViewById(R.id.txt_splash);
		txtFoodBazar.setText("FOOD BAZAR");
		Shimmer.Shimmer shimmer = new Shimmer.Shimmer();
		shimmer.start(txtFoodBazar);



		txtFoodBazar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent it = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(it);
			}
		});

	}

}
