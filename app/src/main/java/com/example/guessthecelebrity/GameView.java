package com.example.guessthecelebrity;

import android.graphics.Bitmap;

import java.util.Arrays;

public class GameView {
    Bitmap imageOfActor;

    @Override
    public String toString() {
        return "GameView{" +
                "imageOfActor=" + imageOfActor +
                ", correctActorName='" + correctActorName + '\'' +
                ", otherActorsNames=" + Arrays.toString(otherActorsNames) +
                '}';
    }

    String correctActorName;
    String[] otherActorsNames;

    public GameView() { }

    public void setImageOfActor(Bitmap imageOfActor) {
        this.imageOfActor = imageOfActor;
    }

    public void setCorrectActorName(String correctActorName) {
        this.correctActorName = correctActorName;
    }

    public void setOtherActorsNames(String[] otherActorsNames) {
        this.otherActorsNames = otherActorsNames;
    }

    public Bitmap getImageOfActor() {
        return imageOfActor;
    }

    public String getCorrectActorName() {
        return correctActorName;
    }

    public String[] getOtherActorsNames() {
        return otherActorsNames;
    }
}
