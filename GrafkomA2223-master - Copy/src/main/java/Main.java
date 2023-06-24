import Engine.*;
import Engine.Object;
import org.joml.*;
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
    private Window window = new Window(1080, 1080, "Hello World");
    ArrayList<Object> objects = new ArrayList<>();
    ArrayList<Object> objectGround = new ArrayList<>();
    ArrayList<Object> objectTrack = new ArrayList<>();
    ArrayList<Object> objectOuterWall = new ArrayList<>();
    ArrayList<Object> objectFinishLine = new ArrayList<>();
    ArrayList<Object> objectLighthouse = new ArrayList<>();
    ArrayList<Object> objectPagar= new ArrayList<>();
    Camera camera = new Camera();
    Projection projection = new Projection(window.getWidth(), window.getHeight());
    float distance = 1f;
    float angle = 0f;
    float rotation = (float)Math.toRadians(1f);
    float speed = 0.025f;
    List<Float> temp;
    int carPos = 0;
    int modeToggle = 0;
    int carPos2 = 0;
    boolean delay = false;
    int delayCounter = 0;
    boolean start = false;
    boolean malam = true;
    boolean delay2 = false;
    int delayCounter2 = 0;
    boolean delay3 = false;
    int delayCounter3 = 0;
    int derajat = 0;


    public void run() throws IOException {

        init();
        loop();

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void init() throws IOException {
        window.init();
        GL.createCapabilities();
        camera.setPosition(-0.2f, 2f, 5f + distance);
//        camera.addRotation(0.5f,0f);

        // mobil (ObjectObj(0))
        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f,0.0f,0.0f,1.0f),
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
        objects.get(3).translateObject(2.0f, -0.7f, 1.0f);


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
//        objects.get(5).scaleObject(2f ,2f, 2f);
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
//        objects.get(5).scaleObject(2f ,2f, 2f);
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
//        objects.get(5).scaleObject(2f ,2f, 2f);
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

        temp = objects.get(0).getCenterPoint();

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

        temp = objects.get(0).getCenterPoint();

    }

    public void input() {
        angle = angle % (float) Math.toRadians(360);
        objects.get(0).updateCenterPoint();
        System.out.println(temp.get(0) +" " + temp.get(1)+ " " +temp.get(2));

        System.out.println("derajat"+derajat);
        if (window.isKeyPressed(GLFW_KEY_W)) {
            if (derajat == 0 || derajat == 90) {
                objects.get(0).translateObject(0f, 0f, -speed);
                camera.moveForward(speed);
                derajat = 0;
            }
            if (derajat == 180) {
                objects.get(0).translateObject(0f, 0f, speed);  // Move backward instead of forward
                camera.moveForward(-speed);  // Move camera backward
                derajat = 0;
            }

//            if (modeToggle == 2) {
//                camera.moveForward(move);
//            } else if (modeToggle == 0) {
//                objects.get(0).translateObject(0f, 0f, -move);
//                camera.setPosition(temp.get(0), temp.get(1), temp.get(2));
//                camera.moveForward(move);
//                if (angle > (float) Math.toRadians(0) && angle < (float) Math.toRadians(180)) {
//                    objects.get(0).translateObject(-temp.get(0), -temp.get(1), -temp.get(2));
//                    objects.get(0).rotateObject(-rotation, 0f, 1f, 0f);
//                    objects.get(0).translateObject(temp.get(0), temp.get(1), temp.get(2));
//                    angle = angle - rotation;
//                } else if (angle > (float) Math.toRadians(180) && angle < (float) Math.toRadians(360)) {
//                    objects.get(0).translateObject(-temp.get(0), -temp.get(1), -temp.get(2));
//                    objects.get(0).rotateObject(rotation, 0f, 1f, 0f);
//                    objects.get(0).translateObject(temp.get(0), temp.get(1), temp.get(2));
//                    angle = angle + rotation;
//                } else if (angle > (float) Math.toRadians(-180) && angle < (float) Math.toRadians(0)) {
//                    objects.get(0).translateObject(-temp.get(0), -temp.get(1), -temp.get(2));
//                    objects.get(0).rotateObject(rotation, 0f, 1f, 0f);
//                    objects.get(0).translateObject(temp.get(0), temp.get(1), temp.get(2));
//                    angle = angle + rotation;
//                } else if (angle > (float) Math.toRadians(-360) && angle < (float) Math.toRadians(-180)) {
//                    objects.get(0).translateObject(-temp.get(0), -temp.get(1), -temp.get(2));
//                    objects.get(0).rotateObject(-rotation, 0f, 1f, 0f);
//                    objects.get(0).translateObject(temp.get(0), temp.get(1), temp.get(2));
//                    angle = angle - rotation;
//                }
//            }
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            camera.moveLeft(speed);
            if (derajat != 90) {
                if (derajat == 0) {
                    camera.setPosition(temp.get(2) - 5f, 2f, temp.get(0));
                    camera.addRotation(0f, (float) Math.toRadians(90f));
                    objects.get(0).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
                    derajat = 90;
                }
                if (derajat == 180) {
                    camera.setPosition(temp.get(2), temp.get(1), temp.get(0));
                    camera.addRotation(0f, (float) Math.toRadians(90f));
                    objects.get(0).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
                    derajat = 90;
                }
                objects.get(0).translateObject(-speed, 0f, 0f);
            }

//            if (modeToggle == 2) {
//                camera.moveLeft(speed);
//            } else if (modeToggle == 0) {
//                objects.get(0).translateObject(-speed, 0f, 0f);
//                camera.setPosition(temp.get(0), temp.get(1), temp.get(2));
//                camera.moveBackwards(distance);
//                if (angle > (float) Math.toRadians(90) && angle < (float) Math.toRadians(270)) {
//                    objects.get(0).translateObject(-temp.get(0), -temp.get(1), -temp.get(2));
//                    objects.get(0).rotateObject(-rotation, 0f, 1f, 0f);
//                    objects.get(0).translateObject(temp.get(0), temp.get(1), temp.get(2));
//                    angle = angle - rotation;
//                } else if (angle > (float) Math.toRadians(270) && angle < (float) Math.toRadians(450)) {
//                    objects.get(0).translateObject(-temp.get(0), -temp.get(1), -temp.get(2));
//                    objects.get(0).rotateObject(rotation, 0f, 1f, 0f);
//                    objects.get(0).translateObject(temp.get(0), temp.get(1), temp.get(2));
//                    angle = angle + rotation;
//                } else if (angle > (float) Math.toRadians(-90) && angle < (float) Math.toRadians(90)) {
//                    objects.get(0).translateObject(-temp.get(0), -temp.get(1), -temp.get(2));
//                    objects.get(0).rotateObject(rotation, 0f, 1f, 0f);
//                    objects.get(0).translateObject(temp.get(0), temp.get(1), temp.get(2));
//                    angle = angle + rotation;
//                } else if (angle > (float) Math.toRadians(-270) && angle < (float) Math.toRadians(-90)) {
//                    objects.get(0).translateObject(-temp.get(0), -temp.get(1), -temp.get(2));
//                    objects.get(0).rotateObject(-rotation, 0f, 1f, 0f);
//                    objects.get(0).translateObject(temp.get(0), temp.get(1), temp.get(2));
//                    angle = angle - rotation;
//                } else if (angle > (float) Math.toRadians(-360) && angle < (float) Math.toRadians(-270)) {
//                    objects.get(0).translateObject(-temp.get(0), -temp.get(1), -temp.get(2));
//                    objects.get(0).rotateObject(rotation, 0f, 1f, 0f);
//                    objects.get(0).translateObject(temp.get(0), temp.get(1), temp.get(2));
//                    angle = angle + rotation;
//                }
//            }
        }

        if (window.isKeyPressed(GLFW_KEY_S)) {
            camera.moveBackwards(speed);
//            if (derajat != 180){
//                if (derajat == 0){
//                    camera.setPosition(temp.get(0), 2f, -5f - distance);
//                    camera.addRotation(0f,(float) Math.toRadians(180f));
//                    objects.get(0).rotateObject((float)Math.toRadians(180f),0f,1f,0f);
//                    derajat+=180;
//                }
//                if (derajat == 90){
//                    camera.setPosition(temp.get(0)+5f + distance, 2f, 0f);
//                    camera.addRotation(0f,(float) Math.toRadians(180f));
//                    objects.get(0).rotateObject((float)Math.toRadians(180f),0f,1f,0f);
//                    derajat-=180;
//                }
//            }
//            objects.get(0).translateObject(0f, 0f, -speed);


//            camera.moveForward(speed);
//            if (modeToggle == 2) {
//                camera.moveBackwards(speed);
//            } else if (modeToggle == 0) {
//                objects.get(0).translateObject(0f, 0f, speed);
//                camera.setPosition(temp.get(0), temp.get(1), temp.get(2));
//                camera.moveBackwards(distance);
//                if (angle > (float) Math.toRadians(180) && angle < (float) Math.toRadians(360)) {
//                    objects.get(0).translateObject(-temp.get(0), -temp.get(1), -temp.get(2));
//                    objects.get(0).rotateObject(-rotation, 0f, 1f, 0f);
//                    objects.get(0).translateObject(temp.get(0), temp.get(1), temp.get(2));
//                    angle = angle - rotation;
//                } else if (angle > (float) Math.toRadians(0) && angle < (float) Math.toRadians(180)) {
//                    objects.get(0).translateObject(-temp.get(0), -temp.get(1), -temp.get(2));
//                    objects.get(0).rotateObject(rotation, 0f, 1f, 0f);
//                    objects.get(0).translateObject(temp.get(0), temp.get(1), temp.get(2));
//                    angle = angle + rotation;
//                } else if (angle > (float) Math.toRadians(-360) && angle < (float) Math.toRadians(-180)) {
//                    objects.get(0).translateObject(-temp.get(0), -temp.get(1), -temp.get(2));
//                    objects.get(0).rotateObject(rotation, 0f, 1f, 0f);
//                    objects.get(0).translateObject(temp.get(0), temp.get(1), temp.get(2));
//                    angle = angle + rotation;
//                } else if (angle > (float) Math.toRadians(-180) && angle < (float) Math.toRadians(0)) {
//                    objects.get(0).translateObject(-temp.get(0), -temp.get(1), -temp.get(2));
//                    objects.get(0).rotateObject(-rotation, 0f, 1f, 0f);
//                    objects.get(0).translateObject(temp.get(0), temp.get(1), temp.get(2));
//                    angle = angle - rotation;
//                }
//            }
        }

        if (window.isKeyPressed(GLFW_KEY_D)) {
            camera.moveRight(speed);
//            if (modeToggle == 2) {
//                camera.moveRight(speed);
//            } else if (modeToggle == 0) {
//                objects.get(0).translateObject(speed, 0f, 0f);
//                camera.setPosition(temp.get(0), temp.get(1), temp.get(2));
//                camera.moveBackwards(distance);
//                if (angle > (float) Math.toRadians(-90) && angle < (float) Math.toRadians(90)) {
//                    objects.get(0).translateObject(-temp.get(0), -temp.get(1), -temp.get(2));
//                    objects.get(0).rotateObject(-rotation, 0f, 1f, 0f);
//                    objects.get(0).translateObject(temp.get(0), temp.get(1), temp.get(2));
//                    angle = angle - rotation;
//                } else if (angle > (float) Math.toRadians(90) && angle < (float) Math.toRadians(270)) {
//                    objects.get(0).translateObject(-temp.get(0), -temp.get(1), -temp.get(2));
//                    objects.get(0).rotateObject(rotation, 0f, 1f, 0f);
//                    objects.get(0).translateObject(temp.get(0), temp.get(1), temp.get(2));
//                    angle = angle + rotation;
//                } else if (angle > (float) Math.toRadians(270) && angle < (float) Math.toRadians(360)) {
//                    objects.get(0).translateObject(-temp.get(0), -temp.get(1), -temp.get(2));
//                    objects.get(0).rotateObject(-rotation, 0f, 1f, 0f);
//                    objects.get(0).translateObject(temp.get(0), temp.get(1), temp.get(2));
//                    angle = angle - rotation;
//                } else if (angle > (float) Math.toRadians(-270) && angle < (float) Math.toRadians(-90)) {
//                    objects.get(0).translateObject(-temp.get(0), -temp.get(1), -temp.get(2));
//                    objects.get(0).rotateObject(rotation, 0f, 1f, 0f);
//                    objects.get(0).translateObject(temp.get(0), temp.get(1), temp.get(2));
//                    angle = angle + rotation;
//                } else if (angle > (float) Math.toRadians(-450) && angle < (float) Math.toRadians(-270)) {
//                    objects.get(0).translateObject(-temp.get(0), -temp.get(1), -temp.get(2));
//                    objects.get(0).rotateObject(-rotation, 0f, 1f, 0f);
//                    objects.get(0).translateObject(temp.get(0), temp.get(1), temp.get(2));
//                    angle = angle - rotation;
//                }
//            }
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


            // Restore state
            glDisableVertexAttribArray(0);
            // Poll for window events.
            // The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().run();
    }
}
