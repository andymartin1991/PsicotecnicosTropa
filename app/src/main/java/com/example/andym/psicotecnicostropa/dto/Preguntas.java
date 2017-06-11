package com.example.andym.psicotecnicostropa.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
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

	public static int getAciertosVerbal() {
		return aciertosVerbal;
	}

	public static void setAciertosVerbal(int aciertosVerbal) {
		Preguntas.aciertosVerbal = aciertosVerbal;
	}

	public static int getFallosVerbal() {
		return fallosVerbal;
	}

	public static void setFallosVerbal(int fallosVerbal) {
		Preguntas.fallosVerbal = fallosVerbal;
	}

	public static int getSincontestarVerbal() {
		return sincontestarVerbal;
	}

	public static void setSincontestarVerbal(int sincontestarVerbal) {
		Preguntas.sincontestarVerbal = sincontestarVerbal;
	}

	public static int getAciertosNumerico() {
		return aciertosNumerico;
	}

	public static void setAciertosNumerico(int aciertosNumerico) {
		Preguntas.aciertosNumerico = aciertosNumerico;
	}

	public static int getFallosNumerico() {
		return fallosNumerico;
	}

	public static void setFallosNumerico(int fallosNumerico) {
		Preguntas.fallosNumerico = fallosNumerico;
	}

	public static int getSincontestarNumerico() {
		return sincontestarNumerico;
	}

	public static void setSincontestarNumerico(int sincontestarNumerico) {
		Preguntas.sincontestarNumerico = sincontestarNumerico;
	}

	public static int getAciertosEspacial() {
		return aciertosEspacial;
	}

	public static void setAciertosEspacial(int aciertosEspacial) {
		Preguntas.aciertosEspacial = aciertosEspacial;
	}

	public static int getFallosEspacial() {
		return fallosEspacial;
	}

	public static void setFallosEspacial(int fallosEspacial) {
		Preguntas.fallosEspacial = fallosEspacial;
	}

	public static int getSincontestarEspacial() {
		return sincontestarEspacial;
	}

	public static void setSincontestarEspacial(int sincontestarEspacial) {
		Preguntas.sincontestarEspacial = sincontestarEspacial;
	}

	public static int getAciertosMecanico() {
		return aciertosMecanico;
	}

	public static void setAciertosMecanico(int aciertosMecanico) {
		Preguntas.aciertosMecanico = aciertosMecanico;
	}

	public static int getFallosMecanico() {
		return fallosMecanico;
	}

	public static void setFallosMecanico(int fallosMecanico) {
		Preguntas.fallosMecanico = fallosMecanico;
	}

	public static int getSincontestarMecanico() {
		return sincontestarMecanico;
	}

	public static void setSincontestarMecanico(int sincontestarMecanico) {
		Preguntas.sincontestarMecanico = sincontestarMecanico;
	}

	public static int getAciertosPerceptiva() {
		return aciertosPerceptiva;
	}

	public static void setAciertosPerceptiva(int aciertosPerceptiva) {
		Preguntas.aciertosPerceptiva = aciertosPerceptiva;
	}

	public static int getFallosPerceptiva() {
		return fallosPerceptiva;
	}

	public static void setFallosPerceptiva(int fallosPerceptiva) {
		Preguntas.fallosPerceptiva = fallosPerceptiva;
	}

	public static int getSincontestarPerceptiva() {
		return sincontestarPerceptiva;
	}

	public static void setSincontestarPerceptiva(int sincontestarPerceptiva) {
		Preguntas.sincontestarPerceptiva = sincontestarPerceptiva;
	}

	public static int getAciertosMemoria() {
		return aciertosMemoria;
	}

	public static void setAciertosMemoria(int aciertosMemoria) {
		Preguntas.aciertosMemoria = aciertosMemoria;
	}

	public static int getFallosMemoria() {
		return fallosMemoria;
	}

	public static void setFallosMemoria(int fallosMemoria) {
		Preguntas.fallosMemoria = fallosMemoria;
	}

	public static int getSincontestarMemoria() {
		return sincontestarMemoria;
	}

	public static void setSincontestarMemoria(int sincontestarMemoria) {
		Preguntas.sincontestarMemoria = sincontestarMemoria;
	}

	public static int getAciertosAbstrapto() {
		return aciertosAbstrapto;
	}

	public static void setAciertosAbstrapto(int aciertosAbstrapto) {
		Preguntas.aciertosAbstrapto = aciertosAbstrapto;
	}

	public static int getFallosAbstrapto() {
		return fallosAbstrapto;
	}

	public static void setFallosAbstrapto(int fallosAbstrapto) {
		Preguntas.fallosAbstrapto = fallosAbstrapto;
	}

	public static int getSincontestarAbstrapto() {
		return sincontestarAbstrapto;
	}

	public static void setSincontestarAbstrapto(int sincontestarAbstrapto) {
		Preguntas.sincontestarAbstrapto = sincontestarAbstrapto;
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
			String imgD, String imgSol, String imgExpli, int respulsada) {
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
    }
	
	
}
