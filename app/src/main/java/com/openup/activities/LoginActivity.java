package com.openup.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.openup.R;
import com.openup.base.BaseActivity;
import com.openup.data.pref.PreferenceManager;
import com.openup.widgets.OpenUpTextview;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.internal.Utils;

public class LoginActivity extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener {

    @BindView(R.id.facebook_login)
    LoginButton facebookLogin;


    private GoogleApiClient mGoogleApiClient;

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final int RC_SIGN_IN = 9001;

    private static final String TAG = "MainActivity";

    private String idToken, name, email, photo;
    public PreferenceManager sharedPrefManager;
    private final Context mContext = this;
    private Uri photoUri;
    private SignInButton mSignInButton;
    GoogleSignInAccount account;

    //facebook login
    CallbackManager mCallbackManager;

    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        configureSignIn();
        mAuth = com.google.firebase.auth.FirebaseAuth.getInstance();



        //this is where we start the Auth state Listener to listen for whether the user is signed in or not

        mAuthListener = new FirebaseAuth.AuthStateListener(){

            @Override

            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                // Get signedIn user

                FirebaseUser user = firebaseAuth.getCurrentUser();



                //if user is signed in, we call a helper method to save the user details to Firebase

                if (user != null) {

                    // User is signed in

                    //createUserInFirebaseHelper();

                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                } else {

                    // User is signed out

                    Log.d(TAG, "onAuthStateChanged:signed_out");

                }

            }

        };

        mCallbackManager = CallbackManager.Factory.create();
        facebookLogin.setReadPermissions("email");
        facebookLogin.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Log.e(TAG,"Hello"+loginResult.getAccessToken().getToken());
                //  Toast.makeText(MainActivity.this, "Token:"+loginResult.getAccessToken(), Toast.LENGTH_SHORT).show();

                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


        mAuthListener = new FirebaseAuth.AuthStateListener(){


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user!=null){
                    name = user.getDisplayName();
                    Toast.makeText(LoginActivity.this,""+user.getDisplayName(),Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(LoginActivity.this,"something went wrong",Toast.LENGTH_LONG).show();
                }


            }
        };


    }

    @OnClick(R.id.google_login)
    public void GoogleLogin(){
        showToast("click on google login");
        signIn();
        /*Intent intent = new Intent(this, CreateProfileActivity.class);
        startActivity(intent);*/
    }

   /* @OnClick(R.id.facebook_login)
    public void FacebookLogin(){
        showToast("click on facebook login");
        Intent intent = new Intent(this, CreateProfileActivity.class);
        startActivity(intent);
    }*/

    //This method creates a new user on our own Firebase database

    //after a successful Authentication on Firebase

    //It also saves the user info to SharedPreference

    /*private void createUserInFirebaseHelper(){



        //Since Firebase does not allow "." in the key name, we'll have to encode and change the "." to ","

        // using the encodeEmail method in class Utils

        final String encodedEmail = Utils.encodeEmail(email.toLowerCase());



        //create an object of Firebase database and pass the the Firebase URL

        final Firebase userLocation = new Firebase(Constants.FIREBASE_URL_USERS).child(encodedEmail);



        //Add a Listerner to that above location

        userLocation.addListenerForSingleValueEvent(new com.firebase.client.ValueEventListener() {

            @Override

            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue() == null){

                    *//* Set raw version of date to the ServerValue.TIMESTAMP value and save into dateCreatedMap *//*

                    HashMap<String, Object> timestampJoined = new HashMap<>();

                    timestampJoined.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);



                    // Insert into Firebase database

                    User newUser = new User(name, photo, encodedEmail, timestampJoined);

                    userLocation.setValue(newUser);



                    Toast.makeText(MainActivity.this, "Account created!", Toast.LENGTH_SHORT).show();



                    // After saving data to Firebase, goto next activity

//                    Intent intent = new Intent(MainActivity.this, NavDrawerActivity.class);

//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

//                    startActivity(intent);

//                    finish();

                }

            }



            @Override

            public void onCancelled(FirebaseError firebaseError) {



                Log.d(TAG, getString(R.string.log_error_occurred) + firebaseError.getMessage());

                //hideProgressDialog();

                if (firebaseError.getCode() == FirebaseError.EMAIL_TAKEN){

                }

                else {

                    Toast.makeText(LoginActivity.this, firebaseError.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }

        });

    }*/



    // This method configures Google SignIn

    public void configureSignIn(){

// Configure sign-in to request the user's basic profile like name and email

        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)

                .requestIdToken(getString(R.string.default_web_client_id))

                .requestEmail()

                .build();



        // Build a GoogleApiClient with access to GoogleSignIn.API and the options above.

        mGoogleApiClient = new GoogleApiClient.Builder(mContext)

                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)

                .addApi(Auth.GOOGLE_SIGN_IN_API, options)

                .build();

        mGoogleApiClient.connect();

    }



    // This method is called when the signIn button is clicked on the layout

    // It prompts the user to select a Google account.

    private void signIn() {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

        startActivityForResult(signInIntent, RC_SIGN_IN);

    }





    // This IS the method where the result of clicking the signIn button will be handled

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);



        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);

        if (requestCode == RC_SIGN_IN) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {

                // Google Sign In was successful, save Token and a state then authenticate with Firebase

                account = result.getSignInAccount();

                idToken = account.getIdToken();

                name = account.getDisplayName();

                email = account.getEmail();

                photoUri = account.getPhotoUrl();

                photo = photoUri.toString();



                // Save Data to SharedPreference

               /* sharedPrefManager = new PreferenceManager(mContext);

                sharedPrefManager.saveIsLoggedIn(mContext, true);



                sharedPrefManager.saveEmail(mContext, email);

                sharedPrefManager.saveName(mContext, name);

                sharedPrefManager.savePhoto(mContext, photo);



                sharedPrefManager.saveToken(mContext, idToken);*/

                //sharedPrefManager.saveIsLoggedIn(mContext, true);



                AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

                firebaseAuthWithGoogle(credential);

            } else {

                // Google Sign In failed, update UI appropriately

                Log.e(TAG, "Login Unsuccessful. ");

                Toast.makeText(this, "Login Unsuccessful", Toast.LENGTH_SHORT)

                        .show();

            }

        }else{
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }

    }



    //After a successful sign into Google, this method now authenticates the user with Firebase

    private void firebaseAuthWithGoogle(AuthCredential credential){

        showProgressDialog();

        mAuth.signInWithCredential(credential)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {

                            Log.w(TAG, "signInWithCredential" + task.getException().getMessage());

                            task.getException().printStackTrace();

                            Toast.makeText(LoginActivity.this, "Authentication failed.",

                                    Toast.LENGTH_SHORT).show();

                        }else {

                            //createUserInFirebaseHelper();

                            Toast.makeText(LoginActivity.this, "Login successful",

                                    Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, CreateProfileActivity.class);

                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("idToken",idToken);
                            intent.putExtra("name",name);
                            intent.putExtra("email",email);
                            intent.putExtra("photo",photo);

                            startActivity(intent);

                            finish();

                        }

                        hideProgressDialog();

                    }

                });

    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(LoginActivity.this, "Success",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(LoginActivity.this, "Authentication error",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }


    @Override

    protected void onStart() {

        super.onStart();

        if (mAuthListener != null){

            FirebaseAuth.getInstance().signOut();

        }

        mAuth.addAuthStateListener(mAuthListener);

    }



    @Override

    protected void onStop() {

        super.onStop();

        if (mAuthListener != null){

            mAuth.removeAuthStateListener(mAuthListener);

        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //-----------show update loader --------------//
    public void showProgressDialog() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Relax while we fetch your details");
        pDialog.setIndeterminate(false);
        pDialog.setMax(100);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setCancelable(true);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
