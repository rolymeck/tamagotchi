package tamagotchi.controller;

import tamagotchi.states.State;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

  private boolean leftPressed;
  private int mouseX, mouseY;

  public MouseManager() {
  }

  // Getters

  public boolean isLeftPressed() {
    return leftPressed;
  }

  public int getMouseX() {
    return mouseX;
  }

  public int getMouseY() {
    return mouseY;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
      leftPressed = true;
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
      leftPressed = false;
    }
    State.getState().getUiManager().onMouseRelease(e);
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    mouseX = e.getX();
    mouseY = e.getY();
    State.getState().getUiManager().onMouseMove(e);
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    // TODO Stub
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // TODO Stub
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Stub
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Stub
  }
}