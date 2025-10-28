package com.challange.api.rest.banco.domain.exceptions;

public class ClienteException extends RuntimeException {

    private String mensaje;
    private int codigoHttp;

    public ClienteException(String message) {
        super(message);
    }

    public ClienteException(String mensaje, int codigoHttp) {
        this.mensaje = mensaje;
        this.codigoHttp = codigoHttp;
    }

    public ClienteException(String message, String mensaje, int codigoHttp) {
        super(message);
        this.mensaje = mensaje;
        this.codigoHttp = codigoHttp;
    }

    public ClienteException(String message, Throwable cause, String mensaje, int codigoHttp) {
        super(message, cause);
        this.mensaje = mensaje;
        this.codigoHttp = codigoHttp;
    }

    public ClienteException(Throwable cause, String mensaje, int codigoHttp) {
        super(cause);
        this.mensaje = mensaje;
        this.codigoHttp = codigoHttp;
    }

    public ClienteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String mensaje, int codigoHttp) {
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
