package com.basset.pillease.activities

class MedicationInfoDatabase {
    private val responses  = hashMapOf(
        "Aspirine" to "Réduit la fièvre et soulage la douleur. Ne pas utiliser en cas d'allergie à l'aspirine.",
        "Ibuprofène" to "Utilisé pour soulager la douleur et l'inflammation. Prendre avec de la nourriture pour éviter les troubles gastriques.",
        "Paracétamol" to "Soulage la douleur légère. Utiliser avec prudence en cas de problèmes hépatiques.",
        "Amoxicilline" to "Antibiotique pour traiter diverses infections. Prendre selon les instructions du médecin.",
        "Métformine" to "Utilisé pour traiter le diabète de type 2. Prendre avec les repas pour réduire les effets secondaires.",
        "Atorvastatine" to "Réduit les niveaux de cholestérol. Prendre une fois par jour.",
        "Sertraline" to "Antidépresseur. Peut prendre plusieurs semaines pour ressentir les effets.",
        "Warfarine" to "Anticoagulant. Nécessite des tests sanguins réguliers.",
        "Amlodipine" to "Utilisé pour l'hypertension. Peut causer de la fatigue ou des étourdissements.",
        "Simvastatine" to "Réduit le cholestérol. Éviter la consommation de pamplemousse.",
        "Ciprofloxacine" to "Antibiotique pour traiter les infections bactériennes. Boire beaucoup d'eau pendant le traitement.",
        "Albutérol" to "Traitement de l'asthme. Utiliser en cas de crise d'asthme pour un soulagement rapide.",
        "Lisinopril" to "Traitement de l'hypertension. Peut causer une toux sèche.",
        "Omeprazole" to "Réduit l'acidité gastrique. Prendre avant les repas.",
        "Losartan" to "Utilisé pour l'hypertension. Surveiller la pression artérielle régulièrement.",
        "Gabapentine" to "Utilisé pour traiter les douleurs nerveuses. Peut causer de la somnolence.",
        "Prednisone" to "Anti-inflammatoire stéroïdien. Utiliser selon les instructions du médecin.",
        "Zolpidem" to "Aide à l'endormissement. Ne pas utiliser plus de quelques semaines.",
        "Hydrochlorothiazide" to "Diurétique pour l'hypertension. Peut causer une déshydratation.",
        "Furosemide" to "Diurétique. Boire beaucoup d'eau et surveiller la consommation de potassium."
        // Ajout d'autres médicaments ... plutard
    )

    fun getAllQuestions(): List<String> {
        return responses.keys.toList()
    }

    fun getResponse(question: String): String {
        return responses[question] ?: "Désolé, je n'ai pas d'information sur cela."
    }
}