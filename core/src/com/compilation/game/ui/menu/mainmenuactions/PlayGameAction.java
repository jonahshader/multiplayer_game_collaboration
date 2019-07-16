package com.compilation.game.ui.menu.mainmenuactions;

import com.compilation.game.MainGame;
import com.compilation.game.screens.GameScreen;
import com.compilation.game.ui.menu.MenuAction;

public class PlayGameAction implements MenuAction {
    private MainGame game;

    public PlayGameAction(MainGame game) {
        this.game = game;
    }

    @Override
    public void executeAction() {
        System.out.println("TODO: start game by changing screen");
        game.setScreen(new GameScreen());
    }
}
