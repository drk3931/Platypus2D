package com;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class GameRunningUI {


    private Label healthLabel;


    private void genHealthLabel(){


        Label.LabelStyle labelStyle=new Label.LabelStyle(new BitmapFont(), Color.RED);
        healthLabel=new Label(String.format("HEALTH %03d",0),labelStyle);

    }

    public void setHealthLabel(int health){

    
        healthLabel.setText(String.format("HEALTH %03d", health));
        
    }

    public GameRunningUI(){
        genHealthLabel();
        setHealthLabel(100);
    }
    
    
}
