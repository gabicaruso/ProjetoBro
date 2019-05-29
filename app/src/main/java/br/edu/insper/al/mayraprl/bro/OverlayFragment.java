package br.edu.insper.al.mayraprl.bro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
        final GestureDetector gesture = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {
            

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {
                // Deslizar para a direita para abrir o site
                if (e2.getY() > e1.getY()) {
                    Uri uri = Uri.parse("https://www.cocacola.com.br/pt/home/");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                    getActivity().finish();

                }
                // Deslizar para a esquerda para desbloquar o telefone
                else if (e2.getY() < e1.getY()) {
                    getActivity().finish();
                }
                return true;
            }

        });

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });
        return view;
    }

}

