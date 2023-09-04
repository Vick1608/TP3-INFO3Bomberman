package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Movimentos extends Sprite {

    Vector2 previousPosition; 

    public Movimentos(Texture texture){
        super(texture); 
        previousPosition = new Vector2(getX(), getY());
        
    }

    public boolean  movimentar(){
        if(previousPosition.x != getX() || previousPosition.y != getY()){
            previousPosition.x = getX(); 
             previousPosition.y = getY(); 
             return true; 
        }
        return false;
    }
    
}
