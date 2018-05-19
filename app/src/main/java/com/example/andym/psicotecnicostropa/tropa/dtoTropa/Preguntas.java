package com.example.andym.psicotecnicostropa.tropa.dtoTropa;

import java.io.Serializable;

public class Preguntas implements Serializable {

    private int cont;
    private String pregunta;
    private String respuestaA;
    private String respuestaB;
    private String respuestaC;
    private String respuestaD;
    private String solu;
    private String expliSol;

    private String imgPregunta;
    private String imgA;
    private String imgB;
    private String imgC;
    private String imgD;
    private String imgSol;
    private String imgExpli;
    private int respulsada;

    private String memo;

    public Preguntas() {

    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public String getImgSol() {
        return imgSol;
    }

    public void setImgSol(String imgSol) {
        this.imgSol = imgSol;
    }

    public String getImgExpli() {
        return imgExpli;
    }

    public void setImgExpli(String imgExpli) {
        this.imgExpli = imgExpli;
    }

    public String getExpliSol() {
        return expliSol;
    }

    public void setExpliSol(String expliSol) {
        this.expliSol = expliSol;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuestaA() {
        return respuestaA;
    }

    public void setRespuestaA(String respuestaA) {
        this.respuestaA = respuestaA;
    }

    public String getRespuestaB() {
        return respuestaB;
    }

    public void setRespuestaB(String respuestaB) {
        this.respuestaB = respuestaB;
    }

    public String getRespuestaC() {
        return respuestaC;
    }

    public void setRespuestaC(String respuestaC) {
        this.respuestaC = respuestaC;
    }

    public String getRespuestaD() {
        return respuestaD;
    }

    public void setRespuestaD(String respuestaD) {
        this.respuestaD = respuestaD;
    }

    public String getSolu() {
        return solu;
    }

    public void setSolu(String solu) {
        this.solu = solu;
    }

    public String getImgPregunta() {
        return imgPregunta;
    }

    public void setImgPregunta(String imgPregunta) {
        this.imgPregunta = imgPregunta;
    }

    public String getImgA() {
        return imgA;
    }

    public void setImgA(String imgA) {
        this.imgA = imgA;
    }

    public String getImgB() {
        return imgB;
    }

    public void setImgB(String imgB) {
        this.imgB = imgB;
    }

    public String getImgC() {
        return imgC;
    }

    public void setImgC(String imgC) {
        this.imgC = imgC;
    }

    public String getImgD() {
        return imgD;
    }

    public void setImgD(String imgD) {
        this.imgD = imgD;
    }

    public int getRespulsada() {
        return respulsada;
    }

    public void setRespulsada(int respulsada) {
        this.respulsada = respulsada;
    }


    public Preguntas(String pregunta, String resA, String resB, String resC, String resD,
                     String sol, String expliSol, String imgPre, String imgA, String imgB, String imgC,
                     String imgD, String imgSol, String imgExpli, int respulsada, String memo) {
        this.pregunta = pregunta;
        this.respuestaA = resA;
        this.respuestaB = resB;
        this.respuestaC = resC;
        this.respuestaD = resD;
        this.solu = sol;
        this.expliSol = expliSol;
        this.imgPregunta = imgPre;
        this.imgA = imgA;
        this.imgB = imgB;
        this.imgC = imgC;
        this.imgD = imgD;
        this.imgSol = imgSol;
        this.imgExpli = imgExpli;
        this.respulsada = respulsada;
        this.memo = memo;
    }


}
