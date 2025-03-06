package com.ChatPlateReader.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OcrService {
    
    private final Tesseract tesseract;

    public OcrService() {
        tesseract = new Tesseract();
        tesseract.setDatapath("C:/Program Files/Tesseract-OCR/tessdata");
        tesseract.setLanguage("por");
    }

    public List<String> returnTextDocument(String docImage) {
        List<String> lista = new ArrayList<>();

        try {
            File imageFile = new File(docImage);
            if (!imageFile.exists()) return lista;

            String textoExtraido = tesseract.doOCR(imageFile);

            String cpf = extrairDados(textoExtraido, "\\b\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}\\b");
            String cnpj = extrairDados(textoExtraido, "\\b\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}\\b");
            String data = extrairDados(textoExtraido, "\\b\\d{2}/\\d{2}/\\d{4}\\b");

            if (!cnpj.isEmpty()) lista.add("CNPJ: " + cnpj);
            if (!cpf.isEmpty()) lista.add("CPF: " + cpf);
            if (!data.isEmpty()) lista.add("Data: " + data);
            
            if(lista.size() < 2) {
            	return null;
            }

        } catch (TesseractException e) {
            System.out.println("Erro ao processar OCR: " + e.getMessage());
        }
        
        return lista;
    }
    
    public List<String> returnTextLicensePlate(String plateImage){
    	return null;
    }

    public static String extrairDados(String texto, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);
        return matcher.find() ? matcher.group() : "";
    }
}
