package com.ChatPlateReader.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Service
public class OcrService {

    private final Tesseract tesseract;

    public OcrService() {
        tesseract = new Tesseract();
        tesseract.setDatapath("C:/Program Files/Tesseract-OCR/tessdata");
        tesseract.setLanguage("por");
    }

    public List<String> returnTextDocument(MultipartFile docImage) {
        List<String> lista = new ArrayList<>();
        try {
            File tempFile = File.createTempFile("ocr-", ".png");
            docImage.transferTo(tempFile);
            String textoExtraido = tesseract.doOCR(tempFile);

            if (textoExtraido == null || textoExtraido.trim().isEmpty()) {
                return lista;
            }

            String cpf = extrairDados(textoExtraido, "\\b\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}\\b");
            String cnpj = extrairDados(textoExtraido, "\\b\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}\\b");
            String data = extrairDados(textoExtraido, "\\b\\d{2}/\\d{2}/\\d{4}\\b");

            if (!cnpj.isEmpty()) lista.add("CNPJ: " + cnpj);
            if (!cpf.isEmpty()) lista.add("CPF: " + cpf);
            if (!data.isEmpty()) lista.add("Data: " + data);

            if (lista.size() < 2) {
                return null;
            }

        } catch (TesseractException | IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return lista;
    }

    public List<String> returnTextLicensePlate(MultipartFile plateImage) {
        List<String> lista = new ArrayList<>();
        try {
            File tempFile = File.createTempFile("ocr-", ".png");
            plateImage.transferTo(tempFile);
            String textoExtraido = tesseract.doOCR(tempFile);

            if (textoExtraido == null || textoExtraido.trim().isEmpty()) {
                return lista;
            }

            String placa = extrairDados(textoExtraido, "\\b[A-Z]{3}-\\d{4}\\b");

            if (!placa.isEmpty()) {
                lista.add("Placa: " + placa);
            }

        } catch (TesseractException | IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return lista;
    }

    public static String extrairDados(String texto, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);
        return matcher.find() ? matcher.group() : "";

    }
}
