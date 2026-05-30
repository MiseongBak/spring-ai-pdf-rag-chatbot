package com.example.demo.controller;

import java.io.File;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.GptService;
import com.example.demo.service.PdfService;

@Controller
@RequiredArgsConstructor
public class RagController {

    private final PdfService pdfService;
    private final GptService gptService;

    private String pdfContent;

    @GetMapping("/rag")
    public String ragPage() {
        return "rag";
    }

    @PostMapping("/rag/upload")
    public String upload(
            @RequestParam MultipartFile file,
            Model model
    ) throws Exception {

        if (file.isEmpty()) {

            model.addAttribute(
                    "message",
                    "PDF 파일을 선택하세요."
            );

            return "rag";
        }

        File saveFile =
                File.createTempFile(
                        "pdf-",
                        ".pdf"
                );

        file.transferTo(saveFile);

        System.out.println(
                "저장 위치 = "
                        + saveFile.getAbsolutePath()
        );

        System.out.println(
                "파일 존재 = "
                        + saveFile.exists()
        );

        pdfService.savePdf(
                saveFile.getAbsolutePath()
        );

        model.addAttribute(
                "message",
                "PDF 업로드 완료"
        );

        return "rag";
    }

    @PostMapping("/rag/question")
    public String question(
            @RequestParam String question,
            Model model
    ) {

        String answer =
                gptService.ask(
                        question
                );

        model.addAttribute(
                "answer",
                answer
        );

        return "rag";
    }
}