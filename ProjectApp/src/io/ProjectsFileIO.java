package io;

import Model.Project;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectsFileIO {

    private ProjectsFileIO() {} //

    public static void serializeToFile(File file, List<Project> projects) throws IOException {
        if (projects == null) {
            projects = new ArrayList<>();
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(projects);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Project> deSerializeFromFile(File file)
            throws IOException, ClassNotFoundException {

        // om filen inte finns, returnera tom lista istället för att kasta fel
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = in.readObject();

            if (obj instanceof List<?>) {
                return (List<Project>) obj;
            } else {
                throw new IOException("Filen innehåller inte en giltig projektlista.");
            }
        }
    }
}
