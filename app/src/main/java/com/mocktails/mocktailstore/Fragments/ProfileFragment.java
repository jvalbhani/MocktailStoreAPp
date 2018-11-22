package com.mocktails.mocktailstore.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mocktails.mocktailstore.ApiCalls.fetchResultApi;
import com.mocktails.mocktailstore.Entities.User;
import com.mocktails.mocktailstore.HomeActivity;
import com.mocktails.mocktailstore.LoginActivity;
import com.mocktails.mocktailstore.Operations.SetAppView;
import com.mocktails.mocktailstore.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
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

    private OnFragmentInteractionListener mListener;

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
        return inflater.inflate(R.layout.profile_fragment, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final SharedPreferences prefs = getActivity().getSharedPreferences(getString(R.string.login), Context.MODE_PRIVATE);
        String jsonString = prefs.getString(getString(R.string.login_id), null);
        if(jsonString == null)
        {
            getActivity().finish();
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
        View v = getView();
        TextView username = (TextView) v.findViewById(R.id.profileUsername);
        TextView email = (TextView) v.findViewById(R.id.profileEmail);
        final EditText fname = (EditText) v.findViewById(R.id.profileFName);
        final EditText lname = (EditText) v.findViewById(R.id.profileLName);
        final EditText contact = (EditText) v.findViewById(R.id.profileContact);
        final EditText pass = (EditText) v.findViewById(R.id.profilePass);
        final EditText cPass = (EditText) v.findViewById(R.id.profileCPass);
        Log.d("-----------4", jsonString);
        final User user = new Gson().fromJson(jsonString, User.class);
        Log.d("-----------4", user.toString());

        username.setText("Hello " + user.getFirstName());
        email.setText(user.getEmail());
        fname.setText(user.getFirstName());
        lname.setText(user.getLastName());
        contact.setText(user.getContactNo());
        Button update = (Button) getActivity().findViewById(R.id.profileUpdateBtn);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setFirstName(fname.getText().toString().trim());
                user.setLastName(lname.getText().toString().trim());
                user.setContactNo(contact.getText().toString().trim());
                if(pass.getText().toString().trim().length() > 7)
                    user.setPassword(pass.getText().toString().trim());
                String jsonElem = new Gson().toJson(user);
                Log.d("-------------4", "profile edit called");
                final fetchResultApi result = new fetchResultApi();
                result.callApi("POST", getString(R.string.mocktailstore) + "account/editProfile", jsonElem);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(!result.getResult().equalsIgnoreCase("error"))
                        {
                            prefs.edit().remove(getString(R.string.login_id));
                            if(result.isFinished())
                                prefs.edit().putString(getString(R.string.login_id), result.getResult()).apply();
                            new SetAppView().setDashBoardView(getActivity());
                        }
                        else
                            Toast.makeText(getContext(),"Some error occoured", Toast.LENGTH_SHORT).show();
                    }
                }, 3000);
            }
        });


        Button logOut = (Button) getActivity().findViewById(R.id.logOutBtn);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefs.edit().remove(getString(R.string.login_id)).apply();
                getActivity().finish();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
