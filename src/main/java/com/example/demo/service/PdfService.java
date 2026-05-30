package com.example.demo.service;

import lombok.RequiredArgsConstructor;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.ai.vectorstore.VectorStore;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfService {

    private final VectorStore vectorStore;

    public void savePdf(String path) {

        PagePdfDocumentReader reader =
                new PagePdfDocumentReader(
                        new FileSystemResource(path)
                );

        List<Document> documents =
                reader.get();

        vectorStore.add(documents);
    }
}