package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class Bomba {
    private Texture texture;
    private float x;
    private float y;
    private float velocidade = 300f;
    private boolean explosao;

     public Bomba(float x, float y) {
        this.x = x;
        this.y = y;
        this.texture = new Texture("Bomba.png");
        explosao = false;
    }

    public void atualizar(float delta) {
        if (!explosao) {
            x += velocidade * delta;

            // Verificar colisão com borda da tela
            if (x > Gdx.graphics.getWidth()) {
                explosao = true;
            }
        }
    }

    public void desenhar(SpriteBatch batch) {
        if (!explosao) {
            batch.draw(texture, x, y);
        }
    }

    public boolean explosao() {
        return explosao;
    }
}
