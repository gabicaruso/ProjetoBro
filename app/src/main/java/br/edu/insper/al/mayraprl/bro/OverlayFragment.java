package br.edu.insper.al.mayraprl.bro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;


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

    int[] imageArray = { R.drawable.back2, R.drawable.background};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_overlay, container, false);
        final ImageView backgroundImage= view.findViewById(R.id.BackGround);
        backgroundImage.setImageResource(R.drawable.background);
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i = 0;

            public void run() {

                backgroundImage.setImageResource(imageArray[i]);
                i++;
                if (i > imageArray.length - 1) {
                    i = 0;
                }
                handler.postDelayed(this, 5*1000);
            }
        };
        handler.postDelayed(runnable, 5*1000);

        final GestureDetector gesture = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {
                // Deslizar para a baixo para abrir o site
                if (e2.getY() > e1.getY()) {
                    if (getbackground()== 1) {
                        Uri uri = Uri.parse("https://www.cocacola.com.br/pt/home/");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);

                    } else if (getbackground()== 2) {
                        Uri uri = Uri.parse("https://www.adidas.com.br/");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                }
                // Deslizar para a cima para desbloquar o telefone
                else if (e2.getY() < e1.getY()) {
                    getActivity().finish();
                }
                return true;
            }

            private int getbackground() {
                if (backgroundImage.getDrawable().getConstantState() == getResources().getDrawable( R.drawable.background).getConstantState())
                {
                    return 1;
                }else if (backgroundImage.getDrawable().getConstantState() == getResources().getDrawable( R.drawable.back2).getConstantState()) {
                    return 2;
                }
                return 0;
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

