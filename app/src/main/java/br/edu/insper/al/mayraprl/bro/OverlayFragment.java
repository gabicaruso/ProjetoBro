package br.edu.insper.al.mayraprl.bro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link OverlayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OverlayFragment extends Fragment {

    public static OverlayFragment newInstance() {
        OverlayFragment fragment = new OverlayFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overlay, container, false);

        Button close = view.findViewById(R.id.close);
        close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        Button link = view.findViewById(R.id.button);
        link.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.google.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                getActivity().finish();

            }
        });
        return view;
    }
}

