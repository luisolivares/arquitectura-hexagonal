package com.challange.api.rest.banco.domain.exceptions;

public class CuentaException extends RuntimeException {

    private String mensaje;
    private int codigoHttp;

    public CuentaException(String message) {
        super(message);
    }

    public CuentaException(String mensaje, int codigoHttp) {
        this.mensaje = mensaje;
        this.codigoHttp = codigoHttp;
    }

    public CuentaException(String message, String mensaje, int codigoHttp) {
        super(message);
        this.mensaje = mensaje;
        this.codigoHttp = codigoHttp;
    }

    public CuentaException(String message, Throwable cause, String mensaje, int codigoHttp) {
        super(message, cause);
        this.mensaje = mensaje;
        this.codigoHttp = codigoHttp;
    }

    public CuentaException(Throwable cause, String mensaje, int codigoHttp) {
        super(cause);
        this.mensaje = mensaje;
        this.codigoHttp = codigoHttp;
    }

    public CuentaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String mensaje, int codigoHttp) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.mensaje = mensaje;
        this.codigoHttp = codigoHttp;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getCodigoHttp() {
        return codigoHttp;
    }

    public void setCodigoHttp(int codigoHttp) {
        this.codigoHttp = codigoHttp;
    }
}
