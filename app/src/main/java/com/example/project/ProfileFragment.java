package com.example.project;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        String profileValue = bundle.getString("messageProfile");
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(), "NAME1", null, 1);

        String[] lines = profileValue.split("\n");
        EditText textView1 = (EditText) rootView.findViewById(R.id.profileEmail);
        EditText textView2 = (EditText) rootView.findViewById(R.id.profilePassword);
        EditText textView3 = (EditText) rootView.findViewById(R.id.profileFname);
        EditText textView4 = (EditText) rootView.findViewById(R.id.profileLname);
        textView1.setText(lines[0]);
        textView2.setText(lines[1]);
        textView3.setText(lines[2]);
        textView4.setText(lines[3]);

        Button update = (Button) rootView.findViewById(R.id.updateB);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBaseHelper.updateRecord(textView1.getText().toString() , textView2.getText().toString() ,textView3.getText().toString(),textView4.getText().toString() );
            }
        });

        return rootView;
    }
}