package com.news.app.com.sport.app.groupchat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import com.news.app.com.sport.app.utilities.Constant;
import com.quickblox.chat.model.QBDialog;
import com.quickblox.core.QBSettings;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

public class ApplicationSingleton extends Application {

    private static final String TAG = ApplicationSingleton.class.getSimpleName();


    public static final String MyPREFERENCES = "Logins" ;
    private static final String APP_ID = "22950";
    private static final String AUTH_KEY = "TRfNfhHpURK8sO3";
    private static final String AUTH_SECRET = "AJJkDmt7XTJ2Ccj";

    public static String USER_LOGIN ;
    public static String USER_PASSWORD ;

    SharedPreferences sharedpreferences;

    private static ApplicationSingleton instance;


    public static ApplicationSingleton getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate");

        instance = this;
        sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        USER_LOGIN = sharedpreferences.getString(Constant.USERNAME, "");
        USER_PASSWORD = sharedpreferences.getString(Constant.PASSWORD, "");

        // Initialise QuickBlox SDK
        //
        QBSettings.getInstance().fastConfigInit(APP_ID, AUTH_KEY, AUTH_SECRET);

    }


    private QBUser currentUser;

    private Map<Integer, QBUser> dialogsUsers = new HashMap<Integer, QBUser>();


    public QBUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(QBUser currentUser) {
        this.currentUser = currentUser;
    }

    public Map<Integer, QBUser> getDialogsUsers() {
        return dialogsUsers;
    }

    public void setDialogsUsers(List<QBUser> setUsers) {
        dialogsUsers.clear();

        for (QBUser user : setUsers) {
            dialogsUsers.put(user.getId(), user);
        }
    }

    public void addDialogsUsers(List<QBUser> newUsers) {
        for (QBUser user : newUsers) {
            dialogsUsers.put(user.getId(), user);
        }
    }

    public Integer getOpponentIDForPrivateDialog(QBDialog dialog){
        Integer opponentID = -1;
        for(Integer userID : dialog.getOccupants()){
            if(!userID.equals(getCurrentUser().getId())){
                opponentID = userID;
                break;
            }
        }
        return opponentID;
    }
}
