package br.com.dio.model;

import java.util.Collection;
import java.util.List;

import static br.com.dio.model.GameStatusEnum.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Board {

    private final List<List<Space>> spaces;

    private int moveCount;
    private long startTime;
    private long endTime;

    public Board(final List<List<Space>> spaces) {
        this.spaces = spaces;
        this.startTime = System.currentTimeMillis();
        this.moveCount = 0;
    }

    public List<List<Space>> getSpaces() {
        return spaces;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public String getFormattedTime() {
        long currentTime = (endTime == 0)
                ? System.currentTimeMillis()
                : endTime;

        long elapsedMillis = currentTime - startTime;
        long seconds = elapsedMillis / 1000;
        long minutes = seconds / 60;
        seconds %= 60;

        return String.format("%02d:%02d", minutes, seconds);
    }

    public GameStatusEnum getStatus() {
        if (spaces.stream().flatMap(Collection::stream)
                .noneMatch(s -> !s.isFixed() && nonNull(s.getActual()))) {
            return NON_STARTED;
        }

        return spaces.stream().flatMap(Collection::stream)
                .anyMatch(s -> isNull(s.getActual()))
                ? INCOMPLETE
                : COMPLETE;
    }

    public boolean hasErrors() {
        if (getStatus() == NON_STARTED) {
            return false;
        }

        return spaces.stream().flatMap(Collection::stream)
                .anyMatch(s ->
                        nonNull(s.getActual()) &&
                                !s.getActual().equals(s.getExpected())
                );
    }

    public boolean changeValue(final int col, final int row, final int value) {
        var space = spaces.get(col).get(row);
        if (space.isFixed()) {
            return false;
        }

        space.setActual(value);
        moveCount++;
        return true;
    }

    public boolean clearValue(final int col, final int row) {
        var space = spaces.get(col).get(row);
        if (space.isFixed()) {
            return false;
        }

        space.clearSpace();
        moveCount++;
        return true;
    }

    public void reset() {
        spaces.forEach(c -> c.forEach(Space::clearSpace));
        moveCount = 0;
        startTime = System.currentTimeMillis();
        endTime = 0;
    }

    public boolean gameIsFinished() {
        if (!hasErrors() && getStatus().equals(COMPLETE)) {
            endTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }
}
