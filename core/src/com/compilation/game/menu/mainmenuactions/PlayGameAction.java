package com.compilation.game.menu.mainmenuactions;

import com.compilation.game.MainGame;
import com.compilation.game.screens.GameScreen;
import com.compilation.game.menu.MenuAction;

public class PlayGameAction implements MenuAction {
    private MainGame game;

    public PlayGameAction(MainGame game) {
        this.game = game;
    }

    @Override
    public void executeAction() {
        game.setScreen(new GameScreen(game));
    }
}
