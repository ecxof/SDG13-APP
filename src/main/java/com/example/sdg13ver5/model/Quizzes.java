package com.example.sdg13ver5.model;

import java.util.List;
import java.util.Map;

public final class Quizzes {

    private Quizzes() {}

    private static final Map<Integer, List<Question>> BY_TOPIC = Map.of(
            1, List.of(
                    new Question(
                            "Climate Change Education (CCE) as an SDG-13 tool primarily targets which groups?",
                            List.of(
                                    "Only university students",
                                    "Retired professionals",
                                    "Learners from early childhood through secondary school, with focus on vulnerable communities",
                                    "Corporate boardrooms only"),
                            2,
                            "SDG-13 CCE spans early-childhood to secondary schooling and emphasises vulnerable communities."),
                    new Question(
                            "Which is NOT a core theme of Climate Change Education?",
                            List.of("Climate science", "Mitigation", "Adaptation", "Weekend sports"),
                            3,
                            "CCE covers science, mitigation, adaptation and sustainable behaviours — not recreational activities."),
                    new Question(
                            "Why does CCE emphasise sustainable behaviours alongside science?",
                            List.of(
                                    "So learners can pass exams",
                                    "So knowledge translates into action against climate change",
                                    "To meet school marketing goals",
                                    "It doesn't — only theory matters"),
                            1,
                            "Behaviour change is what turns awareness into real climate impact.")
            ),
            2, List.of(
                    new Question(
                            "Since 1880, roughly how much has global average temperature risen?",
                            List.of("0.10°C", "0.85°C", "3.5°C", "6.0°C"),
                            1,
                            "The commonly cited figure for warming since the late 19th century is about 0.85°C."),
                    new Question(
                            "By roughly what percentage did CO₂ emissions grow between 1990 and today?",
                            List.of("~5%", "~15%", "~50%", "~200%"),
                            2,
                            "Global CO₂ emissions rose by nearly 50% since 1990."),
                    new Question(
                            "Sea levels have risen by approximately how much since 1880?",
                            List.of("1 cm", "19 cm", "1 m", "3 m"),
                            1,
                            "Global mean sea level is up roughly 19 cm since 1880.")
            ),
            3, List.of(
                    new Question(
                            "SDG-13 is most closely linked with which other SDG on human health?",
                            List.of("SDG-1 (No Poverty)", "SDG-3 (Good Health)", "SDG-8 (Decent Work)", "SDG-14 (Life Below Water)"),
                            1,
                            "Climate action and Good Health & Well-Being are tightly coupled."),
                    new Question(
                            "Limiting warming to 1.5°C by 2030 requires primarily…",
                            List.of("Planting more indoor plants", "Cutting global emissions", "Buying carbon offsets only", "Nothing — it's automatic"),
                            1,
                            "The 1.5°C pathway depends on deep emissions cuts across all sectors."),
                    new Question(
                            "Which of these is a direct human-health impact of climate change?",
                            List.of("Faster internet speeds", "Wider spread of infectious diseases", "Longer battery life", "Increased office productivity"),
                            1,
                            "Climate change accelerates disease vector spread and extreme heat exposure.")
            ),
            4, List.of(
                    new Question(
                            "Climate adaptation is best described as…",
                            List.of(
                                    "Ignoring climate change",
                                    "Preparing systems and communities to cope with climate impacts",
                                    "Only planting trees",
                                    "Waiting for governments to act"),
                            1,
                            "Adaptation is proactive preparation of ecosystems, infrastructure and communities."),
                    new Question(
                            "Sustainability, in the climate context, focuses on…",
                            List.of(
                                    "Short-term profit",
                                    "Long-term resource management within social–ecological systems",
                                    "One-off events",
                                    "Cutting all industrial output today"),
                            1,
                            "Sustainability is a long-horizon systems view."),
                    new Question(
                            "Which country is highlighted in the material as proactively coordinating climate response groups?",
                            List.of("Iceland", "Mozambique", "Chile", "Nepal"),
                            1,
                            "The topic body cites Mozambique's coordinated climate groups.")
            ),
            5, List.of(
                    new Question(
                            "Which climate impact of aviation is often overlooked?",
                            List.of("Airport noise", "High-altitude contrails", "Baggage fees", "Duty-free shops"),
                            1,
                            "Contrails and high-altitude water vapour add non-CO₂ climate forcing."),
                    new Question(
                            "Which is a proposed aviation mitigation option?",
                            List.of("Louder engines", "Lower cruising altitudes", "More window seats", "Longer runways"),
                            1,
                            "Lowering cruise altitude can reduce contrail formation."),
                    new Question(
                            "The best framing for aviation impact reduction is…",
                            List.of(
                                    "Ban all flights immediately",
                                    "Combine operational, technological and structural changes",
                                    "Ignore aviation entirely",
                                    "Only consumer boycotts"),
                            1,
                            "A systems approach mixes airspace, tech, and behaviour changes.")
            ),
            6, List.of(
                    new Question(
                            "Studying past extreme climate events primarily helps us…",
                            List.of("Recreate them", "Detect patterns for earlier warning", "Ignore future risks", "Set new sports records"),
                            1,
                            "Historical pattern recognition underpins early-warning systems."),
                    new Question(
                            "A 'tipping point' in climate terms means…",
                            List.of(
                                    "A restaurant custom",
                                    "A threshold beyond which changes accelerate and may become irreversible",
                                    "A mild breeze",
                                    "An accounting term"),
                            1,
                            "Climate tipping points are thresholds triggering runaway or hard-to-reverse change."),
                    new Question(
                            "Once-rare climate incidents becoming common suggests we should…",
                            List.of("Do nothing", "Improve early-warning and preparedness", "Stop measuring", "Assume forecasts are wrong"),
                            1,
                            "Rising frequency of extremes demands stronger warning and preparedness systems.")
            )
    );

    public static List<Question> forTopic(int topicId) {
        return BY_TOPIC.getOrDefault(topicId, List.of());
    }
}
