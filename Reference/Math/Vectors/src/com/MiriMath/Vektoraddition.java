package com.MiriMath;

import org.jetbrains.annotations.NotNull;

public class Vektoraddition {

    public Vector3 vectorPlusVector(@NotNull Vector3 vector1, Vector3 vector2){
        return new Vector3( vector1.getX() + vector2.getX(),vector1.getY()+vector2.getY(),vector1.getZ() + vector2.getZ());
    }

}
