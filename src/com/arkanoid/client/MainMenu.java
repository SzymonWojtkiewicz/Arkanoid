package com.arkanoid.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;

public class MainMenu {
    interface MainMenuUiBinder extends UiBinder<DivElement, MainMenu> {
    }

    private static MainMenuUiBinder ourUiBinder = GWT.create(MainMenuUiBinder.class);

    public MainMenu() {
        DivElement rootElement = ourUiBinder.createAndBindUi(this);
    }
}