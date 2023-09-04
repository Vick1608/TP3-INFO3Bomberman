package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Bomberman {
    private Texture bombermanFrente, bombermanCostas, bombermanDireita, bombermanEsquerda;
    private float x;
    private float y;
    private float velocidade = 200f;

    private List<Bomba> bombas;

    public Bomberman(float x, float y) {
        this.x = x;
        this.y = y;

        bombermanCostas = new Texture("Costas.png"); 
        bombermanFrente = new Texture("Frente.png"); 
        bombermanDireita =  new Texture("Direita.png"); 
        bombermanEsquerda = new Texture("Esquerda.png"); 

        bombas = new ArrayList<>();
    } 

  
    public void atualizar(float delta) {
        float prevX = x;
        float prevY = y;
    
     
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x -= velocidade * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += velocidade * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            y += velocidade * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            y -= velocidade * delta;
        }

        // Verificar limites da tela e corrigir a posição se necessário
        if (x < 0) {
            x = 0;
        }
        if (x > Gdx.graphics.getWidth() - bombermanFrente.getWidth()) {
            x = Gdx.graphics.getWidth() - bombermanFrente.getWidth();
        }
        if (y < 0) {
            y = 0;
        }
        if (y > Gdx.graphics.getHeight() - bombermanFrente.getHeight()) {
            y = Gdx.graphics.getHeight() - bombermanFrente.getHeight();
        }
    
        // Se a posição não foi corrigida, aplique a atualização
        if (x == prevX && y == prevY) {
            x += 0;
            y += 0;
        }

        // Verificar lançamento de bomba
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Bomba bomba = new Bomba(x + bombermanFrente.getWidth(), y);
            bombas.add(bomba);
        }

        // Atualizar as bombas lançadas
        Iterator<Bomba> iterator = bombas.iterator();
        while (iterator.hasNext()) {
            Bomba bomba = iterator.next();
            bomba.atualizar(delta);
            if (bomba.explosao()) {
                iterator.remove();
            }
        }
    }

     public void desenhar(SpriteBatch batch) {
        Texture textura = bombermanFrente;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            textura = bombermanEsquerda;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            textura = bombermanDireita;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            textura = bombermanCostas;
        }

        // Desenhar as bombas lançadas
        for (Bomba bomba : bombas) {
            bomba.desenhar(batch);
        }

        batch.draw(textura, x, y); 

    }

	public Object atualizar(String jogadorId) {
		return null;
	}
}
