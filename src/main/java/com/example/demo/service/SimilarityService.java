package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class SimilarityService {

    public double cosine(
            float[] a,
            float[] b
    ) {

        double dot = 0;
        double normA = 0;
        double normB = 0;

        for (int i = 0; i < a.length; i++) {

            dot += a[i] * b[i];

            normA += a[i] * a[i];

            normB += b[i] * b[i];
        }

        return dot /
                (
                        Math.sqrt(normA)
                                *
                                Math.sqrt(normB)
                );
    }
}