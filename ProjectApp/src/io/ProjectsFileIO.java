package io;

import Model.Project;

import java.io.*;
import java.util.List;

public class ProjectsFileIO {
    public static void serializeToFile(File file, List<Project> projects) throws IOException {
        if(projects == null){
            throw new IOException("Listan är tom");
        }
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(projects);
        out.close();
    }

    public static List<Project> deSerializeFromFile(File file) throws IOException, ClassNotFoundException{
        if(!file.exists()){
            throw new FileNotFoundException("Filen finns inte");
        }

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        List<Project> projects = (List<Project>) in.readObject();
        in.close();
        return projects;
    }

    ProjectsFileIO(){};
}
