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
    private Window window = new Window(1920, 1080, "Hello World");
    ArrayList<Object> objects = new ArrayList<>();
    boolean eagle=false;
    Camera camera = new Camera();
    Projection projection = new Projection(window.getWidth(), window.getHeight());
    float speed = 0.025f;
    List<Float> posisi;
    float derajat = 0;
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
                new Vector4f(255/255f,204/255f,153/255f,1.0f),
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
                new Vector4f(246/255f,135/255f,25/255f,0.0f),
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
                new Vector4f(96/255f,96/255f,96/255f,1.0f),
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
                new Vector4f(224/255f,224/255f,224/255f,1.0f),
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
                new Vector4f(96/255f,96/255f,96/355f,1.0f),
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
                new Vector4f(102/255f,255/255f,102/255f,1.0f),
                "resources/objects/pohon.obj"
        ));
        objects.get(6).translateObject(-8.0f, 0.0f, 0.0f);

        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0/255f,102/255f,51/255f,1.0f),
                "resources/objects/pohon.obj"
        ));
        objects.get(7).translateObject(-8.0f, 0.0f, -6.0f);

        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(178/255f,255/255f,102/255f,1.0f),
                "resources/objects/pohon.obj"
        ));
        objects.get(8).translateObject(-8.0f, 0.0f, 8.0f);

        objects.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(224/255f,224/255f,224/255f,1.0f),
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
                new Vector4f(125/255f,65/255f,6/255f,1.0f),
                "resources/objects/soil.obj"
        ));
        objects.get(10).scaleObject(3f ,3f, 3f);
        objects.get(10).translateObject(6f, 0.5f, -8f);

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
                new Vector4f(255/255f,255/255f,51/255f,1.0f),
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
                new Vector4f(153/255f,0/255f,0/255f,1.0f),
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
                new Vector4f(255/255f,178/255f,102/255f,1.0f),
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
                new Vector4f(255/255f,204/255f,229/255f,1.0f),
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
                new Vector4f(224/255f,224/255f,224/255f,1.0f),
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
                new Vector4f(224/255f,224/255f,224/255f,1.0f),
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
                new Vector4f(224/255f,224/255f,224/255f,1.0f),
                "resources/objects/fobj_lamp.obj"
        ));
        objects.get(19).scaleObject(0.05f ,0.05f, 0.05f);
        objects.get(19).translateObject(25.0f, -1.5f, 1.0f);

        posisi = objects.get(0).getCenterPoint();
    }

    public void input() {
        objects.get(0).updateCenterPoint();
//        System.out.println(posisi.get(0) +" " + posisi.get(1)+ " " +posisi.get(2));
        System.out.println(camera.getPosition().x + " " + camera.getPosition().y + " " + camera.getPosition().z);
        System.out.println("derajat" + derajat);


        if (window.isKeyPressed(GLFW_KEY_W)) {
            if (posisi.get(2) > -20 && camera.getPosition().z > -20) {
                if (!disabled) {
                    if (derajat == 0) {
                        objects.get(0).translateObject(0f, 0f, -speed);
                        camera.moveForward(speed);
                    } else {
                        float angleRadians = (float) Math.toRadians(derajat);
                        float xMovement = (float) Math.sin(angleRadians) * speed;
                        float zMovement = (float) Math.cos(angleRadians) * speed;

                        objects.get(0).translateObject(xMovement, 0f, -zMovement);
                        camera.moveForward(speed);
                    }
                }else{
                    camera.moveForward(speed);
                }
            }
        }


            if (window.isKeyPressed(GLFW_KEY_A)) {
                if (posisi.get(0) > -12 && camera.getPosition().x > -12) {
                    if (window.isKeyPressed(GLFW_KEY_A)) {
                        Vector3f target = objects.get(0).updateCenterPoint();
                        Vector3f sub = new Vector3f(camera.getPosition().x - target.x, camera.getPosition().y - target.y,
                                camera.getPosition().z - target.z);
                        if (disabled) {
                            camera.addRotation(0f, (float) Math.toRadians(-0.2f));
                            System.out.println(camera.getPosition());
                        }
                        if (!disabled) {
                            float xnow = (float) ((sub.x * Math.cos(Math.toRadians(0f))) + (sub.z * Math.sin(Math.toRadians(0f))));
                            float ynow = sub.y;
                            float znow = (float) ((-sub.x * Math.sin(Math.toRadians(0f))) + (sub.z * Math.cos(Math.toRadians(0f))));
                            camera.addRotation(0f, (float) Math.toRadians(-0.2f));
                            derajat -= 0.2f;
                            camera.setPosition(xnow + target.x, ynow + target.y, znow + target.z);
                            objects.get(0).translateObject((target.x + xnow) * -1, (target.y + ynow) * -1, (target.z + znow) * -1);
                            objects.get(0).rotateObject((float) Math.toRadians(0.2f), 0f, 1f, 0f);
                            objects.get(0).translateObject((target.x + xnow) * 1, (target.y + ynow) * 1, (target.z + znow) * 1);
                        }
                    }
                }
            }
            if (window.isKeyPressed(GLFW_KEY_S)) {
                if (posisi.get(2) > -20 && camera.getPosition().z > -20) {
                    if (!disabled) {
                        if (derajat == 0) {
                            objects.get(0).translateObject(0f, 0f, speed);
                            camera.moveBackwards(speed);
                        } else {
                            float angleRadians = (float) Math.toRadians(derajat);
                            float xMovement = (float) Math.sin(angleRadians) * speed;
                            float zMovement = (float) Math.cos(angleRadians) * speed;

                            objects.get(0).translateObject(-xMovement, 0f, zMovement);
                            camera.moveBackwards(speed);
                        }
                    }else{
                        camera.moveBackwards(speed);
                    }
                }
            }


            if (window.isKeyPressed(GLFW_KEY_D)) {
                if (posisi.get(0) > -12 && camera.getPosition().x > -12) {
                    if (window.isKeyPressed(GLFW_KEY_D)) {
                        Vector3f target = objects.get(0).updateCenterPoint();
                        Vector3f sub = new Vector3f(camera.getPosition().x - target.x, camera.getPosition().y - target.y,
                                camera.getPosition().z - target.z);
                        if (disabled) {
                            camera.addRotation(0f, (float) Math.toRadians(0.2f));
                            System.out.println(camera.getPosition());
                        }
                        if (!disabled) {
                            float xnow = (float) ((sub.x * Math.cos(Math.toRadians(0f))) + (sub.z * Math.sin(Math.toRadians(0f))));
                            float ynow = sub.y;
                            float znow = (float) ((-sub.x * Math.sin(Math.toRadians(0f))) + (sub.z * Math.cos(Math.toRadians(0f))));
                            camera.addRotation(0f, (float) Math.toRadians(0.2f));
                            derajat += 0.2f;
                            camera.setPosition(xnow + target.x, ynow + target.y, znow + target.z);
                            objects.get(0).translateObject((target.x + xnow) * -1, (target.y + ynow) * -1, (target.z + znow) * -1);
                            objects.get(0).rotateObject((float) Math.toRadians(-0.2f), 0f, 1f, 0f);
                            objects.get(0).translateObject((target.x + xnow) * 1, (target.y + ynow) * 1, (target.z + znow) * 1);
                        }
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

            if (window.isKeyPressed(GLFW_KEY_T)) {
                camera.moveBackwards(speed);

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
            if (window.isKeyPressed(GLFW_KEY_F)) {
                if (eagle) {
                    camera.setPosition(posisi.get(0) - 5f, 2f, 0.1f);
                    camera.setRotation((float) Math.toRadians(0f), 0f);
                    camera.moveRight(5f);
                    camera.moveBackwards(5f);
                    eagle = false;
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
