package shaurya.uberintegration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.uber.sdk.android.rides.RideRequestView;
import com.uber.sdk.android.rides.UberSdk;
import com.uber.sdk.android.rides.auth.AccessTokenManager;
import com.uber.sdk.android.rides.auth.LoginManager;

public class MainActivity extends AppCompatActivity {
    LoginManager loginManager;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_bar);
        UberSdk.initialize(this, "cWKvkdCxmGX7XtcYk50pBiU_PVGCAJ13");
        UberSdk.setRedirectUri("https://www.google.com");
        UberSdk.setSandboxMode(true);
        ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressBar_main) ;
        AccessTokenManager accessTokenManager=new AccessTokenManager(this);
        if(accessTokenManager.getAccessToken()!=null){
            progressBar.setVisibility(View.GONE);
            startActivity(new Intent(getApplicationContext(),TabActivity.class));
        }
        else {
            setContentView(R.layout.activity_main);
            button = (Button) findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                }
            });
        }
    }



}
