package org.mogdx.towerdefense.actor.util;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Hit {

    public static boolean hit(Actor first, Actor second) {
        Rectangle rect1 = new Rectangle(first.getX(), first.getY(), first.getWidth(), first.getHeight());
        Rectangle rect2 = new Rectangle(second.getX(), second.getY(), second.getWidth(), second.getHeight());
        return rect1.overlaps(rect2);

    }
}
