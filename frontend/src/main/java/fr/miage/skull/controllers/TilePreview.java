package fr.miage.skull.controllers;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

final class TilePreview {
    private TilePreview() {
    }

    static HBox create(int id) {
        HBox tiles = new HBox(18);
        tiles.setAlignment(Pos.CENTER);
        String[] flowers = {"♣", "✿", "❀", "✾"};
        String[] colors = {"#49632f", "#527b39", "#9b4a57", "#365b83"};
        int index = Math.floorMod(id - 1, flowers.length);
        for (int i = 0; i < 4; i++) {
            Label tile = new Label(i == 0 ? "☠" : flowers[index]);
            tile.getStyleClass().add("market-tile");
            tile.setStyle("-fx-text-fill: " + (i == 0 ? "#382b25" : colors[index]) + ";");
            tiles.getChildren().add(tile);
        }
        return tiles;
    }
}
