package br.edu.insper.al.mayraprl.bro;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("ValidFragment")
public class Pages extends Fragment {

    private int xmlPage;

    @SuppressLint("ValidFragment")
    public Pages(int xmlPage) {
        this.xmlPage = xmlPage;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(xmlPage, container, false);
    }

}
