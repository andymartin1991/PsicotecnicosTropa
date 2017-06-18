package com.example.andym.psicotecnicostropa.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by andym on 16/06/2017.
 */

public class Notas implements Serializable {

    public static int aciertosVerbal;
    public static int fallosVerbal;
    public static int sincontestarVerbal;

    public static int aciertosNumerico;
    public static int fallosNumerico;
    public static int sincontestarNumerico;

    public static int aciertosEspacial;
    public static int fallosEspacial;
    public static int sincontestarEspacial;

    public static int aciertosMecanico;
    public static int fallosMecanico;
    public static int sincontestarMecanico;

    public static int aciertosPerceptiva;
    public static int fallosPerceptiva;
    public static int sincontestarPerceptiva;

    public static int aciertosMemoria;
    public static int fallosMemoria;
    public static int sincontestarMemoria;

    public static int aciertosAbstrapto;
    public static int fallosAbstrapto;
    public static int sincontestarAbstrapto;

    public static double notaredondeadabar;
    public static double notaredondeada;
    public static double bar = 0;
    public static double notasobre10;
    public static double baremoredondeado;

    public static List<Preguntas> bloqueverbal, bloquenumerico, bloqueespacial, bloquemecanico, bloqueperceptiva, bloquememoria, bloqueabstrapto;

    public static List<Preguntas> getBloqueverbal() {
        return bloqueverbal;
    }

    public static void setBloqueverbal(List<Preguntas> bloqueverbal) {
        Notas.bloqueverbal = bloqueverbal;
    }

    public static List<Preguntas> getBloquenumerico() {
        return bloquenumerico;
    }

    public static void setBloquenumerico(List<Preguntas> bloquenumerico) {
        Notas.bloquenumerico = bloquenumerico;
    }

    public static List<Preguntas> getBloqueespacial() {
        return bloqueespacial;
    }

    public static void setBloqueespacial(List<Preguntas> bloqueespacial) {
        Notas.bloqueespacial = bloqueespacial;
    }

    public static List<Preguntas> getBloquemecanico() {
        return bloquemecanico;
    }

    public static void setBloquemecanico(List<Preguntas> bloquemecanico) {
        Notas.bloquemecanico = bloquemecanico;
    }

    public static List<Preguntas> getBloqueperceptiva() {
        return bloqueperceptiva;
    }

    public static void setBloqueperceptiva(List<Preguntas> bloqueperceptiva) {
        Notas.bloqueperceptiva = bloqueperceptiva;
    }

    public static List<Preguntas> getBloquememoria() {
        return bloquememoria;
    }

    public static void setBloquememoria(List<Preguntas> bloquememoria) {
        Notas.bloquememoria = bloquememoria;
    }

    public static List<Preguntas> getBloqueabstrapto() {
        return bloqueabstrapto;
    }

    public static void setBloqueabstrapto(List<Preguntas> bloqueabstrapto) {
        Notas.bloqueabstrapto = bloqueabstrapto;
    }

    public static double getBar() {
        return bar;
    }

    public static void setBar(double bar) {
        Notas.bar = bar;
    }

    public static double getNotasobre10() {
        return notasobre10;
    }

    public static void setNotasobre10(double notasobre10) {
        Notas.notasobre10 = notasobre10;
    }

    public static double getBaremoredondeado() {
        return baremoredondeado;
    }

    public static void setBaremoredondeado(double baremoredondeado) {
        Notas.baremoredondeado = baremoredondeado;
    }

    public static int getAciertosVerbal() {
        return aciertosVerbal;
    }

    public static void setAciertosVerbal(int aciertosVerbal) {
        Notas.aciertosVerbal = aciertosVerbal;
    }

    public static int getFallosVerbal() {
        return fallosVerbal;
    }

    public static void setFallosVerbal(int fallosVerbal) {
        Notas.fallosVerbal = fallosVerbal;
    }

    public static int getSincontestarVerbal() {
        return sincontestarVerbal;
    }

    public static void setSincontestarVerbal(int sincontestarVerbal) {
        Notas.sincontestarVerbal = sincontestarVerbal;
    }

    public static int getAciertosNumerico() {
        return aciertosNumerico;
    }

    public static void setAciertosNumerico(int aciertosNumerico) {
        Notas.aciertosNumerico = aciertosNumerico;
    }

    public static int getFallosNumerico() {
        return fallosNumerico;
    }

    public static void setFallosNumerico(int fallosNumerico) {
        Notas.fallosNumerico = fallosNumerico;
    }

    public static int getSincontestarNumerico() {
        return sincontestarNumerico;
    }

    public static void setSincontestarNumerico(int sincontestarNumerico) {
        Notas.sincontestarNumerico = sincontestarNumerico;
    }

    public static int getAciertosEspacial() {
        return aciertosEspacial;
    }

    public static void setAciertosEspacial(int aciertosEspacial) {
        Notas.aciertosEspacial = aciertosEspacial;
    }

    public static int getFallosEspacial() {
        return fallosEspacial;
    }

    public static void setFallosEspacial(int fallosEspacial) {
        Notas.fallosEspacial = fallosEspacial;
    }

    public static int getSincontestarEspacial() {
        return sincontestarEspacial;
    }

    public static void setSincontestarEspacial(int sincontestarEspacial) {
        Notas.sincontestarEspacial = sincontestarEspacial;
    }

    public static int getAciertosMecanico() {
        return aciertosMecanico;
    }

    public static void setAciertosMecanico(int aciertosMecanico) {
        Notas.aciertosMecanico = aciertosMecanico;
    }

    public static int getFallosMecanico() {
        return fallosMecanico;
    }

    public static void setFallosMecanico(int fallosMecanico) {
        Notas.fallosMecanico = fallosMecanico;
    }

    public static int getSincontestarMecanico() {
        return sincontestarMecanico;
    }

    public static void setSincontestarMecanico(int sincontestarMecanico) {
        Notas.sincontestarMecanico = sincontestarMecanico;
    }

    public static int getAciertosPerceptiva() {
        return aciertosPerceptiva;
    }

    public static void setAciertosPerceptiva(int aciertosPerceptiva) {
        Notas.aciertosPerceptiva = aciertosPerceptiva;
    }

    public static int getFallosPerceptiva() {
        return fallosPerceptiva;
    }

    public static void setFallosPerceptiva(int fallosPerceptiva) {
        Notas.fallosPerceptiva = fallosPerceptiva;
    }

    public static int getSincontestarPerceptiva() {
        return sincontestarPerceptiva;
    }

    public static void setSincontestarPerceptiva(int sincontestarPerceptiva) {
        Notas.sincontestarPerceptiva = sincontestarPerceptiva;
    }

    public static int getAciertosMemoria() {
        return aciertosMemoria;
    }

    public static void setAciertosMemoria(int aciertosMemoria) {
        Notas.aciertosMemoria = aciertosMemoria;
    }

    public static int getFallosMemoria() {
        return fallosMemoria;
    }

    public static void setFallosMemoria(int fallosMemoria) {
        Notas.fallosMemoria = fallosMemoria;
    }

    public static int getSincontestarMemoria() {
        return sincontestarMemoria;
    }

    public static void setSincontestarMemoria(int sincontestarMemoria) {
        Notas.sincontestarMemoria = sincontestarMemoria;
    }

    public static int getAciertosAbstrapto() {
        return aciertosAbstrapto;
    }

    public static void setAciertosAbstrapto(int aciertosAbstrapto) {
        Notas.aciertosAbstrapto = aciertosAbstrapto;
    }

    public static int getFallosAbstrapto() {
        return fallosAbstrapto;
    }

    public static void setFallosAbstrapto(int fallosAbstrapto) {
        Notas.fallosAbstrapto = fallosAbstrapto;
    }

    public static int getSincontestarAbstrapto() {
        return sincontestarAbstrapto;
    }

    public static void setSincontestarAbstrapto(int sincontestarAbstrapto) {
        Notas.sincontestarAbstrapto = sincontestarAbstrapto;
    }

    public static double getNotaredondeadabar() {
        return notaredondeadabar;
    }

    public static void setNotaredondeadabar(double notaredondeadabar) {
        Notas.notaredondeadabar = notaredondeadabar;
    }

    public static double getNotaredondeada() {
        return notaredondeada;
    }

    public static void setNotaredondeada(double notaredondeada) {
        Notas.notaredondeada = notaredondeada;
    }
}
