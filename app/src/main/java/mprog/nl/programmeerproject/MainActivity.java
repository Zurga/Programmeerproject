package mprog.nl.programmeerproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.R.attr.data;
import static android.R.attr.start;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String userId;
    DatabaseReference databaseRef;
    DatabaseReference ref;

    Button logOutButton;
    Button homeButton;
    Button findButton;
    Button chatButton;
    Button schemeButton;

    TextView welcomeText;
    TextView firstNameText;
    TextView lastNameText;
    TextView streetText;
    TextView numText;
    TextView cityText;
    TextView sportText;
    TextView levelText;

    ListView requestList;

    ListAdapter requestAdapter;

    ArrayList<String> requestArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign firebase variables
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseRef = FirebaseDatabase.getInstance().getReference();
        ref = databaseRef.child("Users");

        if (firebaseUser == null) {
            startActivity(createNewIntent(this, LogInActivity.class));
        }
        else {
            userId = firebaseUser.getUid();

            logOutButton = (Button)findViewById(R.id.mainLogOutButton);
            homeButton = (Button)findViewById(R.id.homeButton);
            findButton = (Button)findViewById(R.id.findButton);
            chatButton = (Button)findViewById(R.id.chatButton);
            logOutButton.setOnClickListener(this);
            homeButton.setOnClickListener(this);
            findButton.setOnClickListener(this);
            chatButton.setOnClickListener(this);

            welcomeText = (TextView)findViewById(R.id.mainWelcomeText);
            firstNameText = (TextView)findViewById(R.id.mainFirstText);
            lastNameText = (TextView)findViewById(R.id.mainLastText);
            streetText = (TextView)findViewById(R.id.mainStreetText);
            numText = (TextView)findViewById(R.id.mainNumberText);
            cityText = (TextView)findViewById(R.id.mainCityText);
            sportText = (TextView)findViewById(R.id.mainSportText);
            levelText = (TextView)findViewById(R.id.mainLevelText);

            requestList = (ListView)findViewById(R.id.mainRequestList);

            requestArray = new ArrayList<String>();

            requestAdapter = new ArrayAdapter<>(this, R.layout.list_item, requestArray);

            DatabaseReference ref = databaseRef.child("Users").child(userId);
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        switch (postSnapshot.getKey()) {
                            case "FirstName" :
                                welcomeText.setText(welcomeText.getText() + "   " + postSnapshot.getValue());
                                firstNameText.setText(firstNameText.getText() + ": " + postSnapshot.getValue());
                                break;
                            case "LastName" :
                                lastNameText.setText(lastNameText.getText() + ": " + postSnapshot.getValue());
                                break;
                            case "Street" :
                                streetText.setText(streetText.getText() + ": " + postSnapshot.getValue());
                                break;
                            case "Number" :
                                numText.setText(numText.getText() + ": " + postSnapshot.getValue());
                                break;
                            case "City" :
                                cityText.setText(cityText.getText() + ": " + postSnapshot.getValue());
                                break;
                            case "Sport" :
                                sportText.setText(sportText.getText() + " " + postSnapshot.getValue());
                                break;
                            case "Level" :
                                levelText.setText(levelText.getText() + " " + postSnapshot.getValue());
                                break;
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            ref.child("UserRequests").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        requestArray.add(postSnapshot.getKey());
                    }
                    requestList.setAdapter(requestAdapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        requestList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ref.child(userId).child("UserRequests").child((String) ((TextView)view).getText()).removeValue();
                requestArray.remove(((TextView)view).getText());
                requestList.setAdapter(requestAdapter);
                return true;
            }
        });

        requestList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String newUserId = (String) ((TextView)view).getText();
                ref.child(userId).child("UserRequests").child(newUserId).removeValue();
                ref.child(userId).child("Chats").child(newUserId).setValue(newUserId);
                ref.child(newUserId).child("Chats").child(userId).setValue(userId);
                databaseRef.child("Chat").child(userId + "," + newUserId).push().child("Message").setValue("Welcome");
                requestArray.remove(newUserId);
                requestList.setAdapter(requestAdapter);
            }
        });
    }

    public static AlertDialog createAlert(String error, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(error)
                .setTitle("Error")
                .setPositiveButton(android.R.string.ok,null);
        AlertDialog dialog = builder.create();
        return dialog;
    }

    public static Intent createNewIntent(Context context, Class newClass) {
        Intent intent = new Intent(context, newClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    public static Intent signOut(Context context, FirebaseAuth firebaseAuth) {
        firebaseAuth.signOut();
        return (createNewIntent(context, LogInActivity.class));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.homeButton:
                startActivity(MainActivity.createNewIntent(MainActivity.this, MainActivity.class));
                break;
            case R.id.findButton:
                startActivity(MainActivity.createNewIntent(MainActivity.this, FindUserActivity.class));
                break;
            case R.id.chatButton:
                startActivity(MainActivity.createNewIntent(MainActivity.this, ChatActvity.class));
                break;
            case R.id.mainLogOutButton:
                startActivity(signOut(MainActivity.this, firebaseAuth));
                break;
        }
    }
}