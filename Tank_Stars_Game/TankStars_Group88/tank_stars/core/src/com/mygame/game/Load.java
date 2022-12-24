package com.mygame.game;

import java.io.*;

public class Load {
    public static void serialize(float x1, float x2, float y1, float y2, int g, float h1, float h2, float f1, float f2, int c, int count, int count1, int Tank1, int Tank2) throws IOException {
        Information initial = new Information(x1, x2, y1, y2, g, h1, h2, f1, f2, c, count, count1, Tank1, Tank2); // assign the values
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream (
                    new FileOutputStream("out.txt"));
            out.writeObject(initial);
        }
        finally {
            out.close();
        }
    }
    public static Information deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        Information s1 = null;
        try {
            in = new ObjectInputStream (new FileInputStream("out.txt"));
            s1 = (Information) in.readObject();
        }
        finally {
            in.close();
        }
        return s1;
    }
}
