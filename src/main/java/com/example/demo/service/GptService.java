package com.example.demo.service;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GptService {

    private final ChatClient.Builder builder;

    private final VectorStore vectorStore;

    public String ask(String question) {

        ChatClient chatClient =
                builder.build();

        List<Document> docs =
                vectorStore.similaritySearch(
                        question
                );

        StringBuilder context =
                new StringBuilder();

        for (Document doc : docs) {

            context.append(
                    doc.getText()
            ).append("\n");
        }

        String prompt = """
                다음 문서를 참고해서 답변해줘.

                문서:
                %s

                질문:
                %s
                """
                .formatted(
                        context,
                        question
                );

        return chatClient
                .prompt(prompt)
                .call()
                .content();
    }
}