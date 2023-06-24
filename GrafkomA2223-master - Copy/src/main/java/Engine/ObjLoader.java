package Engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ObjLoader {
    public static ObjModel loadObjFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<Float> vertices = new ArrayList<>();
        List<Float> normals = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split("\\s+");
            if (tokens.length == 0 || tokens[0].equals("#")) {
                continue; // Skip empty lines and comments
            } else if (tokens[0].equals("v")) {
                // Vertex position
                float x = Float.parseFloat(tokens[1]);
                float y = Float.parseFloat(tokens[2]);
                float z = Float.parseFloat(tokens[3]);
                vertices.add(x);
                vertices.add(y);
                vertices.add(z);
            } else if (tokens[0].equals("vn")) {
                // Vertex normal
                float nx = Float.parseFloat(tokens[1]);
                float ny = Float.parseFloat(tokens[2]);
                float nz = Float.parseFloat(tokens[3]);
                normals.add(nx);
                normals.add(ny);
                normals.add(nz);
            } else if (tokens[0].equals("f")) {
                // Face indices
                for (int i = 1; i < tokens.length; i++) {
                    String[] indicesTokens = tokens[i].split("/");
                    int vertexIndex = Integer.parseInt(indicesTokens[0]) - 1;
                    indices.add(vertexIndex);
                }
            }
        }

        reader.close();

        // Convert lists to arrays
        float[] verticesArray = new float[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            verticesArray[i] = vertices.get(i);
        }

        float[] normalsArray = new float[normals.size()];
        for (int i = 0; i < normals.size(); i++) {
            normalsArray[i] = normals.get(i);
        }

        int[] indicesArray = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            indicesArray[i] = indices.get(i);
        }

        return new ObjModel(verticesArray, normalsArray, indicesArray);
    }

    public static void main(String[] args) {
        try {
            ObjModel model = loadObjFile("resources/Tshirt and Jeans.obj");
            // Gunakan model yang dimuat
            // ...
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ObjModel {
    private float[] vertices;
    private float[] normals;
    private int[] indices;

    public ObjModel(float[] vertices, float[] normals, int[] indices) {
        this.vertices = vertices;
        this.normals = normals;
        this.indices = indices;
    }

    // Metode akses dan lainnya untuk bekerja dengan model
}
