package com.znsio.sample.e2e.screen.windows.notepad;

import com.znsio.sample.e2e.screen.notepad.NotepadScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class NotepadScreenWindows
        extends NotepadScreen {
    private static final String SCREEN_NAME = NotepadScreenWindows.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;
    private final By byEditorName = By.name("Text Editor");

    public NotepadScreenWindows(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public NotepadScreen typeMessage(String message) {
        LOGGER.info(String.format("Typing message: '%s'", message));
        driver.findElement(byEditorName).sendKeys(message);
        visually.checkWindow(SCREEN_NAME, "Typed message in Notepad");
        return this;
    }
}
