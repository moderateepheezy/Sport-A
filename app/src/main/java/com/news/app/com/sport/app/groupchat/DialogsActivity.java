package com.news.app.com.sport.app.groupchat;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.news.app.R;
import com.news.app.com.sport.app.utilities.Constant;
import com.quickblox.auth.QBAuth;
import com.quickblox.auth.model.QBSession;
import com.quickblox.chat.QBChatService;
import com.quickblox.chat.model.QBDialog;
import com.quickblox.core.QBCallbackImpl;
import com.quickblox.core.QBEntityCallbackImpl;
import com.quickblox.core.QBSettings;
import com.quickblox.core.result.Result;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;
import com.quickblox.users.result.QBUserResult;

import org.jivesoftware.smack.SmackException;

import java.util.ArrayList;
import java.util.List;


public class DialogsActivity extends BaseActivity {

    SharedPreferences sharedpreferences;
    private final String TAG = ApplicationSingleton.class.getSimpleName();

    private ProgressBar progressBar;
    private ListView lsv_group;

    private static final String APP_ID = "22950";
    private static final String AUTH_KEY = "TRfNfhHpURK8sO3";
    private static final String AUTH_SECRET = "AJJkDmt7XTJ2Ccj";

    static final int AUTO_PRESENCE_INTERVAL_IN_SECONDS = 30;


    private QBChatService chatService;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public static final String MyPREFERENCES = "Logins" ;

    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);

        lsv_group = (ListView) findViewById(R.id.lsv_group);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        progressBar.setVisibility(View.VISIBLE);

        QBChatService.setDebugEnabled(true);
        QBSettings.getInstance().fastConfigInit(APP_ID, AUTH_KEY, AUTH_SECRET);
        if (!QBChatService.isInitialized()) {
            QBChatService.init(this);
        }
        chatService = QBChatService.getInstance();

        datLog();

        if (isSessionActive()) {
            getDialogs();
        }

    }

    private void datLog(){
        final QBUser user = new QBUser();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        final String USER_LOGIN = sharedpreferences.getString("name", "");
        final String USER_PASSWORD = Constant.PASSWORD;
        Toast.makeText(getApplicationContext(), "username " + USER_LOGIN,Toast.LENGTH_LONG).show();
        user.setLogin(USER_LOGIN);
        user.setPassword(USER_PASSWORD);
        QBAuth.createSession(user, new QBEntityCallbackImpl<QBSession>(){
            @Override
            public void onSuccess(QBSession session, Bundle args) {
                DataHolder.getDataHolder().setSignInQbUser(user);
                String x = DataHolder.getDataHolder().getSignInQbUser().getLogin().toString();
                // save current user
                //
                user.setId(session.getUserId());
                //if(user.getId() == 1678050){
                //	value = 1678050;
                // }
                ((ApplicationSingleton)getApplication()).setCurrentUser(user);

                // login to Chat
                //getAllUser();
                //

                Toast.makeText(getApplicationContext(), "username successful " + user.getLogin().toString(),
                        Toast.LENGTH_LONG).show();
                loginToChat(user);
            }

            @Override
            public void onError(List<String> errors) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(DialogsActivity.this);
                dialog.setMessage("create session errors now: " + errors).create().show();

                Intent intent = new Intent(DialogsActivity.this, DialogsActivity.class);
                //intent.putExtra("id", value);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "username not successful " + user.getLogin().toString(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getDialogs() {
        progressBar.setVisibility(View.VISIBLE);

        // Get dialogs
        //
        ChatService.getInstance().getDialogs(new QBEntityCallbackImpl() {
            @Override
            public void onSuccess(Object object, Bundle bundle) {
                progressBar.setVisibility(View.GONE);

                final ArrayList<QBDialog> dialogs = (ArrayList<QBDialog>) object;

                // build list view
                //
                buildListView(dialogs);
            }

            @Override
            public void onError(List errors) {
                progressBar.setVisibility(View.GONE);

                AlertDialog.Builder dialog = new AlertDialog.Builder(DialogsActivity.this);
                dialog.setMessage("get dialogs errors: " + errors).create().show();

                Intent i = new Intent(DialogsActivity.this, DialogsActivity.class);
                startActivity(i);
            }
        });
    }

    void buildListView(List<QBDialog> dialogs) {
        final DialogsAdapter adapter = new DialogsAdapter(dialogs, DialogsActivity.this);
        lsv_group.setAdapter(adapter);

        // choose dialog
        //
        lsv_group.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                QBDialog selectedDialog = (QBDialog) adapter.getItem(position);

                Bundle bundle = new Bundle();
                bundle.putSerializable(ChatActivity.EXTRA_DIALOG, selectedDialog);

                // Open chat activity
                //
                ChatActivity.start(DialogsActivity.this, bundle);

                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_blan, menu);
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
    }

    private void loginToChat(final QBUser user){

        chatService.login(user, new QBEntityCallbackImpl() {
            @Override
            public void onSuccess() {

                // Start sending presences
                //
                try {
                    chatService.startAutoSendPresence(AUTO_PRESENCE_INTERVAL_IN_SECONDS);
                } catch (SmackException.NotLoggedInException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getApplicationContext(), "Login"+user.getLogin().toString(), Toast.LENGTH_LONG).show();
                // go to Dialogs screen
                //
            }

            @Override
            public void onError(List errors) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(DialogsActivity.this);
                dialog.setMessage("chat login errors: " + errors).create().show();
            }
        });
    }

}