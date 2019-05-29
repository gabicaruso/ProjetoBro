package br.edu.insper.al.mayraprl.bro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PointCounter {

    private float points;

    public PointCounter(float points){
        this.points = points;
    }

    public float addPoints(){
        return points + 10;
    }

    public float getPoints(){
        return points;
    }

    public float subPoints(float moneyCost){
        float pointCost = moneyCost*1000;
        return points - pointCost;
    }
}
