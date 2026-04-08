package com.medilabo.microserviceassessment.model;

import java.util.List;

public class DiabetesIndicators {

    private DiabetesIndicators() {}

    public static final List<String> INDICATORS = List.of(
            "Hémoglobine A1C",
            "Microalbumine",
            "Taille",
            "Poids",
            "Fumeur",
            "Fumeuse",
            "Fumer",
            "Anormal",
            "Cholestérol",
            "Vertige",
            "Rechute",
            "Réaction",
            "Anticorps"
    );
}
