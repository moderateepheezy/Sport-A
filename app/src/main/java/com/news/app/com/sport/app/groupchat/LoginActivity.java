package com.news.app.com.sport.app.groupchat;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.news.app.News_All;
import com.news.app.R;
import com.news.app.com.sport.app.utilities.Constant;
import com.quickblox.auth.QBAuth;
import com.quickblox.auth.model.QBSession;
import com.quickblox.chat.QBChatService;
import com.quickblox.core.QBCallbackImpl;
import com.quickblox.core.QBEntityCallbackImpl;
import com.quickblox.core.QBSettings;
import com.quickblox.core.result.Result;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;
import com.quickblox.users.result.QBUserResult;

import org.jivesoftware.smack.SmackException;

import java.util.List;

public class LoginActivity extends ActionBarActivity {

    private static final String APP_ID = "22950";
    private static final String AUTH_KEY = "TRfNfhHpURK8sO3";
    private static final String AUTH_SECRET = "AJJkDmt7XTJ2Ccj";

    static final int AUTO_PRESENCE_INTERVAL_IN_SECONDS = 30;

    private QBUser user = null;


    private QBChatService chatService;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Init Chat
        //
        QBChatService.setDebugEnabled(true);
        QBSettings.getInstance().fastConfigInit(APP_ID, AUTH_KEY, AUTH_SECRET);
        if (!QBChatService.isInitialized()) {
            QBChatService.init(this);
        }
        chatService = QBChatService.getInstance();


        final EditText username = (EditText) findViewById(R.id.username);
        Button btnRegister = (Button) findViewById(R.id.signup);


        sharedPreferences = getApplicationContext().getSharedPreferences("Logins", 0);
// get editor to edit in file
        editor = sharedPreferences.edit();

        btnRegister.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View v) {
               final String usern = username.getText().toString();

                final QBUser user = new QBUser(usern, Constant.PASSWORD);
                QBAuth.createSession(new QBCallbackImpl() {

                    @Override
                    public void onComplete(Result results) {
                        if (results.isSuccess()) {
                            QBUsers.signUp(user, new QBCallbackImpl() {
                                public void onComplete(Result result) {
                                    if (result.isSuccess()) {
                                        QBUserResult qbUserResult = (QBUserResult) result;
                                        Log.d("Registration succesful", "user: "
                                                + qbUserResult.getUser().toString());
                                        editor.putString("name", usern);
                                        editor.commit();

                                       /*new MyAsyncClass().execute();*/
                                        Intent intent = new Intent(LoginActivity.this, News_All.class);
                                        //intent.putExtra("id", value);
                                        startActivity(intent);
                                        // finish();
                                    } else {
                                        Log.e("Errors", result.getErrors().toString());
                                        Toast.makeText(getApplicationContext(),
                                                result.getErrors().toString(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                            dialog.setMessage("create session errors: " + results).create().show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
