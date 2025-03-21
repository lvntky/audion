package com.leventkaya.model;

/**
 * Made complex class immutable
 */

public class Complex {

  private final double real;
  private final double imag;

  public Complex(double real, double imag) {
    this.real = real;
    this.imag = imag;
  }

  public Complex add(Complex b) {
    return new Complex(this.real + b.real, this.imag + b.imag);
  }

  public Complex subtract(Complex b) {
    return new Complex(this.real - b.real, this.imag - b.imag);
  }

  public Complex multiply(Complex b) {
    double re = this.real * b.real - this.imag * b.imag;
    double im = this.real * b.imag + this.imag * b.real;
    return new Complex(re, im);
  }

  public Complex exp(double angle) {
    return new Complex(Math.cos(angle), Math.sin(angle));
  }

  @Override
  public String toString() {
    return "Complex{" +
        "real='" + real + '\'' +
        ", imag=" + imag +
        '}';
  }
}
