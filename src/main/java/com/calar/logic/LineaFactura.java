/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.calar.logic;

/**
 *
 * @author ivanfriasgil
 */
public class LineaFactura {
    private int id_factura;
    private String n_producto;
    private int cantidad;
    private float precioTotal;

    public LineaFactura(int id_factura, String n_producto, int cantidad, float precioTotal) {
        this.id_factura = id_factura;
        this.n_producto = n_producto;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public String getN_producto() {
        return n_producto;
    }

    public void setN_producto(String n_producto) {
        this.n_producto = n_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }
    
    
}
