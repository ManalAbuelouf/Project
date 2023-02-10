package com.example.project;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.net.SocketTimeoutException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SortedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SortedFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SortedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SortedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SortedFragment newInstance(String param1, String param2) {
        SortedFragment fragment = new SortedFragment();
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
        String ascendingValue = bundle.getString("messageAscending");
        String descendingValue = bundle.getString("messageDescending");
        String[] ascendingLines = ascendingValue.split("[\r\n]+");
        String[] descendingLines = descendingValue.split("[\r\n]+");
        View rootView = inflater.inflate(R.layout.fragment_sorted, container, false);
        String[] options = { "Ascending", "Descending" };
        Spinner SortingSpinner = rootView.findViewById(R.id.spinner_sort);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, options);
        SortingSpinner.setAdapter(adapter);
        LinearLayout linearLayout=(LinearLayout) rootView.findViewById(R.id.linear);
        SortingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String opt = SortingSpinner.getSelectedItem().toString();
                if (opt == "Ascending"){
                    linearLayout.removeAllViews();
                    for (int j = 0 ; j<ascendingLines.length ; j++){
                        TextView textView1 = new TextView(getActivity());
                        textView1.setText(ascendingLines[j]);
                        textView1.setTextSize(20);
                        textView1.setTextColor(-1);
                        textView1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(getActivity(),DestinationActivity.class);
                                intent.putExtra("key",textView1.getText().toString());
                                startActivity(intent);
                            }
                        });
                        linearLayout.addView(textView1);
                    }

                    imageView.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.translate));
                }

                else if (opt == "Descending"){
                    linearLayout.removeAllViews();
                    for (int j = 0 ; j<ascendingLines.length ; j++) {
                        TextView textView2 = new TextView(getActivity());
                        textView2.setText(descendingLines[j]);
                        textView2.setTextSize(20);
                        textView2.setTextColor(-1);
                        textView2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(getActivity(),DestinationActivity.class);
                                intent.putExtra("key",textView2.getText().toString());
                                startActivity(intent);
                            }
                        });
                        linearLayout.addView(textView2);
                    }
                    imageView.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.translate2));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return rootView;
    }
}