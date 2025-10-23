package io;


import Model.Project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hints on how to implement serialization and deserialization
 * of lists of projects and users.
 */
public class ProjectsFileIO {

    /**
     * Call this method before the application exits, to store the users and projects,
     * in serialized form.
     */
    public static void serializeToFile(File file, List<Project> data) throws IOException {
        ObjectOutputStream oos = null;
        try{
            FileOutputStream fout = new FileOutputStream(file);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(data);
        }

        finally {
            oos.close();
        }
    }

    /**
     * Call this method at startup of the application, to deserialize the users and
     * from file the specified file.
     */
    @SuppressWarnings("unchecked")
    public static List<Project> deSerializeFromFile(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = null;
        List<Project> projects;
        try{
            FileInputStream fin = new FileInputStream(file);
            ois = new ObjectInputStream(fin);
            List<Project> tmp = (ArrayList<Project>) ois.readObject();
            projects = tmp;

        }
        finally {
            ois.close();
        }
        return projects;

    }

    private ProjectsFileIO() {}
}
