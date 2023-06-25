import Engine.*;
import Engine.Object;
import org.joml.*;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import java.io.IOException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL30.*;

public class Main {
    private Window window = new Window(720, 720, "Hello World");
    ArrayList<Object> objects = new ArrayList<>();
    boolean eagle=false;
    Camera camera = new Camera();
    Projection projection = new Projection(window.getWidth(), window.getHeight());
    float speed = 0.025f;
    List<Float> posisi;
    int derajat = 0;
    boolean tpp=true;
    boolean fpp=false;
    boolean disabled=false;
    float movex,movey,movez;


    public void run() throws IOException {

        init();
        loop();

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void init() throws IOException {
        window.init();
        GL.createCapabilities();
        camera.setPosition(-0.2f, 2f, 6f);
        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f,-1.5f,0.0f,1.0f),
                "resources/objects/ninja_postac.obj"
        ));
        objects.get(0).scaleObject(0.3f,0.3f,0.3f);
        objects.get(0).translateObject(-0.2f, -1f, 0f);

        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.0f,1.0f,1.0f),
                "resources/objects/rumah.obj"
        ));
        objects.get(1).scaleObject(3f,3f,3f);
        objects.get(1).translateObject(8f, 0f, 0f);

        // ground (objectGround(0))
        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,1f,0f,1.0f),
                "resources/objects/tanah.obj"
        ));
        objects.get(2).scaleObject(3f ,2f, 2f);
        objects.get(2).translateObject(0f, 0f, 0f);

        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1f,1f,0f,1.0f),
                "resources/objects/fobj_lamp.obj"
        ));
        objects.get(3).scaleObject(0.05f ,0.05f, 0.05f);
        objects.get(3).translateObject(2.0f, -1.5f, 1.0f);


        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(3f,1f,0f,1.0f),
                "resources/objects/sumur.obj"
        ));
        objects.get(4).scaleObject(1f ,1f, 1f);
        objects.get(4).translateObject(0.0f, 0.1f, 1.0f);

        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(220/255f,220/255f,220/255f,1.0f),
                "resources/objects/batu.obj"
        ));
        objects.get(5).scaleObject(2f ,2f, 2f);
        objects.get(5).translateObject(1.0f, 0.0f, 0.0f);

        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(34/255f,139/255f,34/255f,1.0f),
                "resources/objects/pohon.obj"
        ));
        objects.get(6).translateObject(-8.0f, 0.0f, 0.0f);

        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(34/255f,139/255f,34/255f,1.0f),
                "resources/objects/pohon.obj"
        ));
        objects.get(7).translateObject(-8.0f, 0.0f, -6.0f);

        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(34/255f,139/255f,34/255f,1.0f),
                "resources/objects/pohon.obj"
        ));
        objects.get(8).translateObject(-8.0f, 0.0f, 8.0f);

        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(210/255f,105/255f,30/255f,1.0f),
                "resources/objects/pagar.obj"
        ));
        objects.get(9).scaleObject(3f ,3f, 3f);
        objects.get(9).translateObject(6f, 0.05f, -8f);

        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(210/255f,180/255f,140/255f,1.0f),
                "resources/objects/soil.obj"
        ));
        objects.get(10).scaleObject(3f ,3f, 3f);
        objects.get(10).translateObject(6f, 0.75f, -8f);

        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(50/255f,205/255f,50/255f,1.0f),
                "resources/objects/rumput2.obj"
        ));
        objects.get(11).scaleObject(3f ,3f, 3f);
        objects.get(11).translateObject(6f, 0f, -8f);

        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(50/255f,205/255f,50/255f,1.0f),
                "resources/objects/rumput2.obj"
        ));
        objects.get(12).scaleObject(3f ,3f, 3f);
        objects.get(12).translateObject(6f, 0f, -11.5f);

        posisi = objects.get(0).getCenterPoint();

        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(50/255f,205/255f,50/255f,1.0f),
                "resources/objects/rumput2.obj"
        ));
        objects.get(13).scaleObject(3f ,3f, 3f);
        objects.get(13).translateObject(6f, 0f, -14.5f);

        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1f,0f,0f,1.0f),
                "resources/objects/barn.obj"
        ));
        objects.get(14).scaleObject(3f ,3f, 2f);
        objects.get(14).translateObject(3f, 0.5f, 0f);

        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1f,1f,1f,1.0f),
                "resources/objects/sapi.obj"
        ));
        objects.get(15).scaleObject(2f ,2f, 2f);
        objects.get(15).translateObject(8f, 0f, 0f);

        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(255/255f,192/255f,203/255f,1.0f),
                "resources/objects/babi.obj"
        ));
        objects.get(16).scaleObject(2f ,2f, 2f);
        objects.get(16).translateObject(8f, 0f, 0f);

        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1f,1f,0f,1.0f),
                "resources/objects/fobj_lamp.obj"
        ));
        objects.get(17).scaleObject(0.05f ,0.05f, 0.05f);
        objects.get(17).translateObject(2.0f, -1.5f, -13.0f);

        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1f,1f,0f,1.0f),
                "resources/objects/fobj_lamp.obj"
        ));
        objects.get(18).scaleObject(0.05f ,0.05f, 0.05f);
        objects.get(18).translateObject(25.0f, -1.5f, -13.0f);

        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1f,1f,0f,1.0f),
                "resources/objects/fobj_lamp.obj"
        ));
        objects.get(19).scaleObject(0.05f ,0.05f, 0.05f);
        objects.get(19).translateObject(25.0f, -1.5f, 1.0f);

        posisi = objects.get(0).getCenterPoint();

    }

    public void input() {
        objects.get(0).updateCenterPoint();
//        System.out.println(posisi.get(0) +" " + posisi.get(1)+ " " +posisi.get(2));
        System.out.println(camera.getPosition().x +" " + camera.getPosition().y + " " + camera.getPosition().z);
        System.out.println("derajat"+derajat);


        if (window.isKeyPressed(GLFW_KEY_W)) {
            if (posisi.get(2) > -20){
                if(!disabled) {
                    if (derajat == 0) {
                        objects.get(0).translateObject(0f, 0f, -speed);
                        camera.moveForward(speed);
                        derajat = 0;
                    }
                    if (derajat == 180) {
                        objects.get(0).translateObject(0f, 0f, speed);  // Move backward instead of forward
                        camera.moveForward(-speed);  // Move camera backward
                        derajat = 0;
                    }
                    if (derajat == -90){
                        objects.get(0).rotateObject((float)Math.toRadians(-90f),0f,1f,0f);
                        camera.setPosition(posisi.get(2) + 0f,2f,posisi.get(0));
                        camera.moveLeft(5f);
                        camera.addRotation(0f,(float) Math.toRadians(90f));
                        objects.get(0).translateObject(0f, 0f, -speed);
                        camera.moveForward(speed);
                        derajat = 0;
                    }
                    if (derajat == 90){
                        objects.get(0).rotateObject((float)Math.toRadians(90f),0f,1f,0f);
                        camera.setPosition(posisi.get(2) + 0f,2f,posisi.get(0));
                        camera.moveRight(5f);
                        camera.addRotation(0f,(float) Math.toRadians(-90f));
                        objects.get(0).translateObject(0f, 0f, -speed);
                        camera.moveForward(speed);
                        derajat = 0;
                    }


                }else{
                    if (derajat == 0) {
                        camera.moveForward(speed);
                        derajat = 0;
                    }
                    if (derajat == 180) {
                        camera.addRotation(0f,(float) Math.toRadians(180f));
                        camera.moveForward(speed);  // Move camera backward
                        derajat = 0;
                    }
                    if (derajat == -90){
//                        camera.setPosition(posisi.get(2) + 0f,2f,posisi.get(0));
//                        camera.moveLeft(5f);
                        camera.addRotation(0f,(float) Math.toRadians(90f));
                        camera.moveForward(speed);
                        derajat = 0;
                    }
                    if (derajat == 90){
//                        camera.setPosition(posisi.get(2) + 0f,2f,posisi.get(0));
//                        camera.moveRight(5f);
                        camera.addRotation(0f,(float) Math.toRadians(-90f));
                        camera.moveForward(speed);
                        derajat = 0;
                    }
                }
            }
        }


            if (window.isKeyPressed(GLFW_KEY_A)) {
                if (posisi.get(0) > -12){
                    if (!disabled) {
                        if (derajat != -90)
                            if (derajat == 0) {
                                camera.setPosition(posisi.get(2) + 5f, 2f, posisi.get(0));
                                camera.addRotation(0f, (float) Math.toRadians(-90f));
                                objects.get(0).rotateObject((float) Math.toRadians(-90f), 0f, 1f, 0f);
                                derajat = -90;
                            }

                        objects.get(0).translateObject(-speed, 0f, 0f);
                        camera.moveForward(speed);
                    } else {
//                        if (camera.getPosition().z > -1 && camera.getPosition().z < -10) {
//                            if (camera.getPosition().x > -3.5) {
                                if (derajat == 0) {
//                            camera.setPosition(posisi.get(2) + 5f, 2f, posisi.get(0));
                                    camera.addRotation(0f, (float) Math.toRadians(-90f));
                                    derajat = -90;

                                }
                                camera.moveForward(speed);
                            }
//                        }
//                    }
                }
            }
        if (window.isKeyPressed(GLFW_KEY_S)) {
            if (posisi.get(2) < 18){
                if(!eagle) {
                    if (!disabled) {
                        if (derajat == 0 || derajat == 90) {
                            objects.get(0).translateObject(0f, 0f, speed);
                            camera.moveForward(-speed);
                            derajat = 0;
                        }
                        if (derajat == 180) {
                            objects.get(0).translateObject(0f, 0f, speed);  // Move backward instead of forward
                            camera.moveForward(-speed);  // Move camera backward
                            derajat = 0;
                        }

                    } else {
                        if (derajat == 0) {
                            camera.addRotation(0f, (float) Math.toRadians(180f));
                            derajat = 180;
                        }
                        camera.moveForward(speed);
                    }
                }
            }
        }




            if (window.isKeyPressed(GLFW_KEY_D)) {
                if (posisi.get(0) < 26){
                    if (!disabled) {
                        if (derajat != 90) {
                            if (derajat == 0) {
                                camera.setPosition(posisi.get(2) - 5f, 2f, posisi.get(0));
                                camera.addRotation(0f, (float) Math.toRadians(90f));
                                objects.get(0).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
                                derajat = 90;

                            }
                        }
                        objects.get(0).translateObject(speed, 0f, 0f);
                        camera.moveForward(speed);
                    } else {

                        if (derajat == 0) {
                            camera.setPosition(posisi.get(2) + 5f, 2f, posisi.get(0));
                            camera.addRotation(0f, (float) Math.toRadians(90f));
                            derajat = 90;

                        }
                        camera.moveForward(speed);


                    }
                }
            }



                if (window.isKeyPressed(GLFW_KEY_UP)) {
                    camera.addRotation(-0.01f, 0f);
                }
                if (window.isKeyPressed(GLFW_KEY_DOWN)) {
                    camera.addRotation(0.01f, 0f);
                }
                if (window.isKeyPressed(GLFW_KEY_LEFT)) {
                    camera.addRotation(0f, -0.01f);
                }
                if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
                    camera.addRotation(0f, 0.01f);
                }

                if (window.isKeyPressed(GLFW_KEY_4)) {
                    if (tpp) {
                        camera.moveForward(7f);
                        tpp = false;
                        fpp = true;
                    }
                }

                if (window.isKeyPressed(GLFW_KEY_5)) {
                    if (fpp) {
                        camera.moveBackwards(7f);
                        tpp = true;
                        fpp = false;
                    }
                }


                if (window.isKeyPressed(GLFW_KEY_6)) {
                    if (!disabled) {
                        Vector3f tmp = camera.getPosition();
                        movex = tmp.x;
                        movey = tmp.y;
                        movez = tmp.z;
                        disabled = true;
                    }
                }

                if (window.isKeyPressed(GLFW_KEY_7)) {
                    if (disabled) {
                        camera.setPosition(movex, movey, movez);
                        disabled = false;
                    }
                }

                if (window.isKeyPressed(GLFW_KEY_E)) {
                    if (!eagle) {
                        camera.setPosition(0.2f, 70f, 0.1f);
                        camera.setRotation((float) Math.toRadians(90f), 0f);
                        eagle = true;
                    }
                }
                if(window.isKeyPressed(GLFW_KEY_F)){
                    if(eagle){
                        camera.setPosition(posisi.get(0) - 5f, 2f,0.1f);
                        camera.setRotation((float)Math.toRadians(0f),0f);
                        camera.moveRight(5f);
                        camera.moveBackwards(5f);
                        eagle=false;
                    }
                }


            }


    public void loop() {
        while (window.isOpen()) {
            window.update();
            glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

            GL.createCapabilities();

            input();


            for (Object object: objects) {
                object.draw(camera, projection);
            }

            glDisableVertexAttribArray(0);
            glfwPollEvents();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().run();
    }
}
