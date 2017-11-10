package ru.rbt.mvnprjs.app.controller;

import ru.rbt.mvnprjs.app.model.Calculator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;


public class CalculatorController {

    private Calculator calculator;
    private UIInput firstNumberInput;
    private UIInput secondNumberInput;


    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    public Calculator getCalculator() {
        return calculator;
    }

    public String add() {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        try {
            calculator.add();
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Added successfully", null));

        } catch (Exception ex) {
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
        }
        return "results";
    }

    public String multiply() {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        try {
            calculator.multiply();
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Multiplied successfully", null));

        } catch (Exception ex) {
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
        }
        return "results";
    }

    public String divide() {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        try {
            calculator.divide();
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Divided successfully", null));

        } catch (Exception ex) {
            if (ex instanceof ArithmeticException) {
                secondNumberInput.setValue(Integer.valueOf(1));
            }
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
        }
        return "results";
    }

    public String clear() {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        try {
            calculator.clear();
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Results cleared", null));

        } catch (Exception ex) {
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
        }
        return null;
    }

    public String getFirstNumberStyleClass() {
        if (firstNumberInput.isValid()) {
            return "labelClass";
        } else {
            return "errorClass";
        }
    }

    public String getSecondNumberStyleClass() {
        if (secondNumberInput.isValid()) {
            return "labelClass";
        } else {
            return "errorClass";
        }
    }

    public UIInput getFirstNumberInput() {
        return firstNumberInput;
    }

    public void setFirstNumberInput(UIInput firstNumberInput) {
        this.firstNumberInput = firstNumberInput;
    }

    public UIInput getSecondNumberInput() {
        return secondNumberInput;
    }

    public void setSecondNumberInput(UIInput secondNumberInput) {
        this.secondNumberInput = secondNumberInput;
    }


}
