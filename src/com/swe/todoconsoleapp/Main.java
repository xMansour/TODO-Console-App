package com.swe.todoconsoleapp;

import com.swe.todoconsoleapp.entity.ToDo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) throws Exception {

        var toDo = new ToDo();
        toDo.setTitle("hahha");
        var fos = new FileOutputStream("t.txt");
        var oos = new ObjectOutputStream(fos);
        oos.writeObject(toDo);
        oos.close();
        var fis = new FileInputStream("t.txt");
        var ois = new ObjectInputStream(fis);
        var result = (ToDo) ois.readObject();
        System.out.println(result.getTitle());

        ois.close();
    }
}
