package com.compilation.game.ui.menu.mainmenuactions;

import com.badlogic.gdx.Gdx;
import com.compilation.game.ui.menu.MenuAction;

public class ExitGameAction implements MenuAction {
    @Override
    public void executeAction() {
        Gdx.app.exit();
    }
}
