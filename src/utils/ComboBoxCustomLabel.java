/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author higor
 */
public class ComboBoxCustomLabel {
    private String label;
    private Object value;

    public ComboBoxCustomLabel(String label, Object value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public Object getValue() {
        return value != null ? this.value : this.label ;
    }

    @Override
    public String toString() {
        return this.label;
    }
    
    
}
