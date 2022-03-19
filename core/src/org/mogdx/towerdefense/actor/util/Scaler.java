package org.mogdx.towerdefense.actor.util;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Scaler {

    public static Texture scale(Pixmap map, int w, int h) {
        Pixmap newMap = new Pixmap(w, h, map.getFormat());
        newMap.drawPixmap(map,
                0, 0, map.getWidth(), map.getHeight(),
                0, 0, newMap.getWidth(), newMap.getHeight()
        );
        final Texture texture = new Texture(newMap);
        map.dispose();
        newMap.dispose();
        return texture;
    }
}
