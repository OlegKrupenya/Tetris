package com.udev.process;

import com.udev.domain.Cell;
import com.udev.domain.Field;
import com.udev.domain.figures.Figure;
import com.udev.domain.figures.RotationState;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: taipan
 * Date: 12.10.13
 */
public class ZFigureRotationManager implements RotationManager {
    @Override
    public void rotate(Figure figure, Field field) {
        Cell[][] data = field.getCells();
        List<Cell> cells = figure.getCells();
        RotationState state = figure.getRotationState();
        if (state == RotationState.HORIZONTAL && isPossibleToRotateVertically(figure, field)) {
            Cell firstCell = cells.get(0);
            data[firstCell.getI()][firstCell.getJ()] = new Cell(firstCell.getI(), firstCell.getJ(), Field.ZERO);
            data[firstCell.getI() - 1][firstCell.getJ() + 2] = new Cell(firstCell.getI() - 1, firstCell.getJ() + 2,
                    Field.ONE);
            firstCell.setI(firstCell.getI() - 1);
            firstCell.setJ(firstCell.getJ() + 2);

            Cell fourthCell = cells.get(3);
            data[fourthCell.getI()][fourthCell.getJ()] = new Cell(fourthCell.getI(), fourthCell.getJ(), Field.ZERO);
            data[fourthCell.getI() - 1][fourthCell.getJ()] = new Cell(fourthCell.getI() - 1, fourthCell.getJ(),
                    Field.ONE);
            fourthCell.setI(fourthCell.getI() - 1);
            figure.setRotationState(RotationState.VERTICAL);
        } else if (state == RotationState.VERTICAL && isPossibleToRotateHorizontally(figure, field)) {
            Cell firstCell = cells.get(0);
            data[firstCell.getI()][firstCell.getJ()] = new Cell(firstCell.getI(), firstCell.getJ(), Field.ZERO);
            data[firstCell.getI() + 1][firstCell.getJ() - 2] = new Cell(firstCell.getI() + 1, firstCell.getJ() - 2,
                    Field.ONE);
            firstCell.setI(firstCell.getI() + 1);
            firstCell.setJ(firstCell.getJ() - 2);

            Cell fourthCell = cells.get(3);
            data[fourthCell.getI()][fourthCell.getJ()] = new Cell(fourthCell.getI(), fourthCell.getJ(), Field.ZERO);
            data[fourthCell.getI() + 1][fourthCell.getJ()] = new Cell(fourthCell.getI() + 1, fourthCell.getJ(),
                    Field.ONE);
            fourthCell.setI(fourthCell.getI() + 1);
            figure.setRotationState(RotationState.HORIZONTAL);
        }
    }

    private boolean isPossibleToRotateHorizontally(Figure figure, Field field) {
        boolean canRotate = true;
        Cell[][] data = field.getCells();
        List<Cell> cells = figure.getCells();
        Cell firstCell = cells.get(0);
        if (firstCell.getJ() - 2 < 0 || data[firstCell.getI() + 2][firstCell.getJ()].getData() == Field.ONE
                || data[firstCell.getI() + 1][firstCell.getJ() - 2].getData() == Field.ONE) {
            canRotate = false;
        }
        return canRotate;
    }

    private boolean isPossibleToRotateVertically(Figure figure, Field field) {
        boolean canRotate = true;
        Cell[][] data = field.getCells();
        List<Cell> cells = figure.getCells();
        Cell firstCell = cells.get(0);
        if (data[firstCell.getI() - 1][firstCell.getJ() + 2].getData() == Field.ONE ||
                data[firstCell.getI()][firstCell.getJ() + 2].getData() == Field.ONE) {
            canRotate = false;
        }
        return canRotate;
    }
}
