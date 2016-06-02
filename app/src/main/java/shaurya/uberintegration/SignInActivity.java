package shaurya.uberintegration;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.uber.sdk.android.rides.auth.AccessToken;
import com.uber.sdk.android.rides.auth.AccessTokenManager;
import com.uber.sdk.android.rides.auth.AuthenticationError;
import com.uber.sdk.android.rides.auth.LoginCallback;
import com.uber.sdk.android.rides.auth.LoginManager;
import com.uber.sdk.android.rides.auth.Scope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ishan Shaurya Jaiswal.
 */
public class SignInActivity extends AppCompatActivity {

    private LoginManager loginManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatActivity activity=this;
        AccessTokenManager accessTokenManager = new AccessTokenManager(activity);
        loginManager = new LoginManager(accessTokenManager);
        List<Scope> scopes = new ArrayList<Scope>();
        scopes.add(Scope.PROFILE);
        scopes.add(Scope.HISTORY);
        scopes.add(Scope.RIDE_WIDGETS);
        loginManager.loginWithScopes(activity, scopes);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        LoginCallback loginCallback = new LoginCallback() {
            @Override
            public void onLoginCancel() {
                Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_SHORT);
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                Log.d("message","cancel");
            }

            @Override
            public void onLoginError(@NonNull AuthenticationError error) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                Log.d("message","error");
                // Error occurred during login
            }

            @Override
            public void onLoginSuccess(@NonNull AccessToken accessToken) {
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT);
                Log.d("message","success");
                Intent i=new Intent(getApplicationContext(),TabActivity.class);
                startActivity(i);
            }
        };
        loginManager.onActivityResult(requestCode, resultCode, data,
                loginCallback);
        Log.d("message","done");
        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT);
    }

}
