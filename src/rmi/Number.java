package rmi;

import java.io.Serializable;

public class Number implements Serializable {
  int valor;

  Number(int valor) {
    this.valor = valor;
  }
}
