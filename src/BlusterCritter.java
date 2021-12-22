/*
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.awt.*;
import java.util.ArrayList;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter
{
    private int courageFactor;

    public BlusterCritter(int c){
        super();
        courageFactor = c;
    }

    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        Location loc = getLocation();
        for (int r =loc.getRow() - 2; r <= loc.getRow() + 2; r++) {
            for (int c = loc.getCol() - 2; c < loc.getCol() + 2; c++) {
                Location tempLoc = new Location(r, c);
                if (getGrid().isValid(tempLoc)) {
                    Actor a = getGrid().get(tempLoc);
                    if (a != null && a != this)
                        actors.add(a);
                }
            }
        }
        return actors;
    }

    /**
     *
     * @param actors
     */

    public void processActors(ArrayList<Actor> actors)
    {
        int count = 0;
        for (Actor a : actors) {
            if(a instanceof Critter){
                count++;
            } if(count < courageFactor){
                lighten();
            }else{
                darken();
            }
        }
    }

    /**
     * darkens the color of the critter
     */

    private void darken(){
        Color c = getColor();
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();

        if(red > 0){
            red--;
        }if(green > 0){
            green--;
        }if(blue > 0){
            blue--;
        }
        setColor(new Color(red, green, blue));
    }

    /**
     * lightens the color of the critter
     */

    private void lighten(){
        Color c = getColor();
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();

        if(red < 255){
            red++;
        }if(green < 255){
            green++;
        }if(blue < 255){
            blue++;
        }
        setColor(new Color(red, green, blue));
    }

    /**
     * Turns towards the new location as it moves.
     */
    public void makeMove(Location loc)
    {
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }

}
