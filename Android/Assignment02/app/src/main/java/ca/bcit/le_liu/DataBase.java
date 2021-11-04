package ca.bcit.le_liu;

import android.content.Context;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class DataBase {
    public interface DatabaseLoadListener {
        void onLoadFinish();

        void onLoadFail();
    }

    static private DatabaseReference databaseReference;
    static private ArrayList<Person> persons;
    static private DatabaseLoadListener databaseLoadListener;
    static private WeakReference<Context> context;
    static private final DataBase inst = new DataBase();

    private DataBase() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        persons = new ArrayList<>();
    }

    public static DataBase getInstance() {
        return inst;
    }

    public void loadData(DatabaseLoadListener listener) {
        databaseLoadListener = listener;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        queryData();
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    private void queryData() {
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                databaseLoadListener.onLoadFinish();

                Person person = null;
                try {
                    person = dataSnapshot.getValue(Person.class);
                    person.id = dataSnapshot.getKey();
                } catch (Exception e) {
                    return;
                }

                persons.add(person);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                databaseLoadListener.onLoadFail();
            }
        });
    }
}
