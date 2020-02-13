package tamagotchi.controller;

import tamagotchi.states.State;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

  @Override
  public void mouseReleased(MouseEvent e) {
    State.getState().getUiManager().onMouseRelease(e);
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    State.getState().getUiManager().onMouseMove(e);
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // TODO Stub
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