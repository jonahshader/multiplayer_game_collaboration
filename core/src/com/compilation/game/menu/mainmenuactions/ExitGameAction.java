package com.compilation.game.menu.mainmenuactions;

import com.badlogic.gdx.Gdx;
import com.compilation.game.menu.MenuAction;

public class ExitGameAction implements MenuAction {
    @Override
    public void executeAction() {
        Gdx.app.exit();
    }
}
