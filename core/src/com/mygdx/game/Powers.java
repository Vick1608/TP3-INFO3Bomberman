package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Powers {
    private List<Texture> powersTextures; 
    private List<Rectangle> hitboxes;

    public Powers(){
        powersTextures =  new ArrayList<>(); 
        hitboxes =  new ArrayList<>(); 
    }

    public void adicionarPowers(Texture texture, float x, float y ){
        powersTextures.add(texture); 
        hitboxes.add(new Rectangle(x, y, texture.getWidth(), texture.getHeight())); 

    }

     public void desenhar(SpriteBatch batch) {
        for (int i = 0; i < powersTextures.size(); i++) {
            Texture texture = powersTextures.get(i);
            Rectangle hitbox = hitboxes.get(i);
            batch.draw(texture, hitbox.x, hitbox.y);
        }
    }

    public Rectangle getHitbox(int index) {
        return hitboxes.get(index);
    }

    public void dispose() {
        for (Texture texture : powersTextures) {
            texture.dispose();
        }
    }
}
