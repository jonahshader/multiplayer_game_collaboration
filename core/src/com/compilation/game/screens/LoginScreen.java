package com.compilation.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.compilation.game.MainGame;
import com.compilation.game.managers.SqlManager;

public class LoginScreen implements Screen {

    private Stage stage;
    private Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));;

    private MainGame game;

    TextButton btnLogin = new TextButton("Login", skin);
    TextButton btnRegister = new TextButton("Register", skin);
    Label lblLogin = new Label("Username:", skin);
    TextField txtUserName = new TextField("", skin);
    Label lblPassword = new Label("Password:", skin);
    Label lblError = new Label("", skin);

    public LoginScreen(MainGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        btnLogin.setX(10);
        btnLogin.setY(270);
        btnLogin.setWidth(100);
        btnLogin.setHeight(30);

        btnRegister.setX(120);
        btnRegister.setY(270);
        btnRegister.setWidth(100);
        btnRegister.setHeight(30);

        lblError.setX(10);
        lblError.setY(223);
        lblError.setWidth(100);
        lblError.setHeight(30);

        lblLogin.setX(10);
        lblLogin.setY(430);
        lblLogin.setWidth(200);
        lblLogin.setHeight(30);

        txtUserName.setX(10);
        txtUserName.setY(400);
        txtUserName.setWidth(210);
        txtUserName.setHeight(30);
        stage.addActor(txtUserName);

        lblPassword.setX(10);
        lblPassword.setY(360);
        lblPassword.setWidth(200);
        lblPassword.setHeight(30);

        TextField txtPassword = new TextField("", skin);
        txtPassword.setX(10);
        txtPassword.setY(330);
        txtPassword.setWidth(210);
        txtPassword.setHeight(30);

        stage.addActor(txtPassword);
        stage.addActor(txtUserName);
        stage.addActor(lblLogin);
        stage.addActor(lblPassword);
        stage.addActor(btnLogin);
        stage.addActor(btnRegister);
        stage.addActor(lblError);

        btnRegister.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if(txtUserName.getText().length() < 7){
                    lblError.setText("Your username must be at least 7 characters");
                    return;
                }

                if(txtPassword.getText().length() < 7){
                    lblError.setText("Your password must be at least 7 characters");
                    return;
                }

                SqlManager sql = new SqlManager();
                int result = sql.getSingleValue("EXEC AccountRegister '" + txtUserName.getText() + "', '" + txtPassword.getText() + "';");
                if(result == 1){
                    game.setScreen(new MainMenuScreen(game));
                }else{
                    lblError.setText("This username already exists!");
                }
            };
        });

        btnLogin.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                SqlManager sql = new SqlManager();
                int result = sql.getSingleValue("EXEC AccountLogin '" + txtUserName.getText() + "', '" + txtPassword.getText() + "';");
                if(result == 1){
                    game.setScreen(new MainMenuScreen(game));
                }else{
                    lblError.setText("Invalid Login Information");
                }
            };
        });

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
