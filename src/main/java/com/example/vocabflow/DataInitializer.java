package com.example.vocabflow;

import com.example.vocabflow.entity.Word;
import com.example.vocabflow.repository.WordRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final WordRepository wordRepository;

    public DataInitializer(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public void run(String... args) {

        List<Word> words = List.of(

            // =========================
            // COMMUNICATION
            // =========================

            new Word(
                "Articulate",
                "Adjective",
                "Able to express ideas clearly and effectively.",
                "She was articulate when explaining her project.",
                "expressive, clear, eloquent",
                "Intermediate",
                "Communication"
            ),

            new Word(
                "Assertive",
                "Adjective",
                "Confident in expressing opinions or needs without being aggressive.",
                "He was assertive during the team discussion.",
                "confident, self-assured, firm",
                "Intermediate",
                "Communication"
            ),

            new Word(
                "Coherent",
                "Adjective",
                "Logical, clear, and easy to understand.",
                "Her presentation was coherent and well organized.",
                "logical, consistent, clear",
                "Intermediate",
                "Communication"
            ),

            new Word(
                "Compelling",
                "Adjective",
                "Very convincing or interesting.",
                "He presented a compelling argument.",
                "convincing, persuasive, powerful",
                "Advanced",
                "Communication"
            ),

            new Word(
                "Diplomatic",
                "Adjective",
                "Able to deal with people sensitively and tactfully.",
                "She gave a diplomatic response to the criticism.",
                "tactful, polite, considerate",
                "Advanced",
                "Communication"
            ),

            new Word(
                "Emphasize",
                "Verb",
                "To give special importance or attention to something.",
                "The teacher emphasized the importance of practice.",
                "highlight, stress, underline",
                "Intermediate",
                "Communication"
            ),

            new Word(
                "Expressive",
                "Adjective",
                "Showing thoughts or feelings clearly.",
                "Her expressive writing captured everyone's attention.",
                "communicative, emotional, vivid",
                "Intermediate",
                "Communication"
            ),

            new Word(
                "Fluent",
                "Adjective",
                "Able to speak or write smoothly and easily.",
                "He is fluent in three languages.",
                "articulate, proficient, smooth",
                "Intermediate",
                "Communication"
            ),

            new Word(
                "Interpret",
                "Verb",
                "To explain or understand the meaning of something.",
                "Different readers may interpret the poem differently.",
                "explain, understand, decode",
                "Intermediate",
                "Communication"
            ),

            new Word(
                "Persuasive",
                "Adjective",
                "Good at convincing someone to believe or do something.",
                "Her persuasive speech changed their opinion.",
                "convincing, influential, compelling",
                "Intermediate",
                "Communication"
            ),

            new Word(
                "Precise",
                "Adjective",
                "Exact, accurate, and clearly expressed.",
                "Please give precise instructions.",
                "exact, accurate, specific",
                "Intermediate",
                "Communication"
            ),

            new Word(
                "Proclaim",
                "Verb",
                "To announce something publicly or officially.",
                "The leader proclaimed the new policy.",
                "announce, declare, state",
                "Advanced",
                "Communication"
            ),

            new Word(
                "Reiterate",
                "Verb",
                "To say something again to emphasize it.",
                "The teacher reiterated the importance of revision.",
                "repeat, restate, emphasize",
                "Advanced",
                "Communication"
            ),

            new Word(
                "Succinct",
                "Adjective",
                "Clearly expressed using very few words.",
                "His answer was succinct and useful.",
                "concise, brief, compact",
                "Advanced",
                "Communication"
            ),

            new Word(
                "Tactful",
                "Adjective",
                "Careful not to offend or upset others.",
                "She was tactful when giving feedback.",
                "diplomatic, considerate, sensitive",
                "Intermediate",
                "Communication"
            ),

            new Word(
                "Verbose",
                "Adjective",
                "Using more words than necessary.",
                "The report was informative but unnecessarily verbose.",
                "wordy, lengthy, long-winded",
                "Advanced",
                "Communication"
            ),

            new Word(
                "Vivid",
                "Adjective",
                "Producing strong and clear images in the mind.",
                "The author gave a vivid description of the city.",
                "graphic, striking, lively",
                "Intermediate",
                "Communication"
            ),

            new Word(
                "Convey",
                "Verb",
                "To communicate or express an idea or feeling.",
                "The image conveys a sense of calm.",
                "communicate, express, transmit",
                "Intermediate",
                "Communication"
            ),

            new Word(
                "Clarify",
                "Verb",
                "To make something easier to understand.",
                "Could you clarify the final requirement?",
                "explain, simplify, illuminate",
                "Beginner",
                "Communication"
            ),

            new Word(
                "Rhetorical",
                "Adjective",
                "Used to make an effect rather than to obtain an answer.",
                "The speaker asked a rhetorical question.",
                "figurative, expressive, dramatic",
                "Advanced",
                "Communication"
            ),

            // =========================
            // THINKING
            // =========================

            new Word(
                "Analytical",
                "Adjective",
                "Using careful examination and logical reasoning.",
                "Her analytical approach helped solve the problem.",
                "logical, systematic, methodical",
                "Intermediate",
                "Thinking"
            ),

            new Word(
                "Ambiguous",
                "Adjective",
                "Open to more than one interpretation.",
                "The instructions were ambiguous and confusing.",
                "unclear, vague, uncertain",
                "Intermediate",
                "Thinking"
            ),

            new Word(
                "Pragmatic",
                "Adjective",
                "Dealing with problems in a practical way.",
                "We need a pragmatic solution to the problem.",
                "practical, realistic, sensible",
                "Advanced",
                "Thinking"
            ),

            new Word(
                "Profound",
                "Adjective",
                "Very deep, serious, or meaningful.",
                "The experience had a profound effect on her.",
                "deep, meaningful, significant",
                "Advanced",
                "Thinking"
            ),

            new Word(
                "Logical",
                "Adjective",
                "Based on clear reasoning and sensible connections.",
                "Her conclusion was logical and convincing.",
                "rational, reasonable, systematic",
                "Beginner",
                "Thinking"
            ),

            new Word(
                "Rational",
                "Adjective",
                "Based on reason rather than emotion.",
                "He made a rational decision after reviewing the facts.",
                "reasonable, logical, sensible",
                "Intermediate",
                "Thinking"
            ),

            new Word(
                "Infer",
                "Verb",
                "To reach a conclusion from evidence or reasoning.",
                "We can infer the answer from the available data.",
                "deduce, conclude, derive",
                "Advanced",
                "Thinking"
            ),

            new Word(
                "Deduce",
                "Verb",
                "To reach an answer through logical reasoning.",
                "The detective deduced who had entered the room.",
                "infer, conclude, reason",
                "Advanced",
                "Thinking"
            ),

            new Word(
                "Hypothesis",
                "Noun",
                "A proposed explanation that can be tested.",
                "The researchers developed a hypothesis before the experiment.",
                "theory, proposal, assumption",
                "Advanced",
                "Thinking"
            ),

            new Word(
                "Insight",
                "Noun",
                "A deep understanding of something.",
                "The discussion gave me valuable insight into the problem.",
                "understanding, perception, awareness",
                "Intermediate",
                "Thinking"
            ),

            new Word(
                "Intuitive",
                "Adjective",
                "Based on instinct or immediate understanding.",
                "The interface is intuitive and easy to use.",
                "instinctive, natural, immediate",
                "Intermediate",
                "Thinking"
            ),

            new Word(
                "Objective",
                "Adjective",
                "Based on facts rather than personal feelings.",
                "We need an objective evaluation of the results.",
                "impartial, unbiased, neutral",
                "Intermediate",
                "Thinking"
            ),

            new Word(
                "Skeptical",
                "Adjective",
                "Not easily convinced that something is true.",
                "She was skeptical about the unusual claim.",
                "doubtful, questioning, suspicious",
                "Intermediate",
                "Thinking"
            ),

            new Word(
                "Evaluate",
                "Verb",
                "To judge the value or quality of something.",
                "The committee will evaluate all the proposals.",
                "assess, judge, examine",
                "Intermediate",
                "Thinking"
            ),

            new Word(
                "Critique",
                "Noun",
                "A detailed evaluation of something.",
                "The professor gave a detailed critique of the project.",
                "review, analysis, evaluation",
                "Advanced",
                "Thinking"
            ),

            new Word(
                "Perspective",
                "Noun",
                "A particular way of viewing or understanding something.",
                "Travel can change your perspective on life.",
                "viewpoint, outlook, angle",
                "Intermediate",
                "Thinking"
            ),

            new Word(
                "Conceptual",
                "Adjective",
                "Related to ideas or concepts rather than physical things.",
                "The course focuses on conceptual understanding.",
                "theoretical, abstract, intellectual",
                "Advanced",
                "Thinking"
            ),

            new Word(
                "Cognitive",
                "Adjective",
                "Related to thinking, learning, or understanding.",
                "Reading can improve cognitive skills.",
                "mental, intellectual, psychological",
                "Advanced",
                "Thinking"
            ),

            new Word(
                "Curiosity",
                "Noun",
                "A strong desire to know or learn something.",
                "Her curiosity encouraged her to explore new technologies.",
                "interest, inquisitiveness, wonder",
                "Beginner",
                "Thinking"
            ),

            new Word(
                "Contemplate",
                "Verb",
                "To think deeply about something.",
                "He took some time to contemplate the decision.",
                "consider, reflect, ponder",
                "Advanced",
                "Thinking"
            ),

            // =========================
            // PERSONALITY
            // =========================

            new Word(
                "Resilient",
                "Adjective",
                "Able to recover quickly from difficulties.",
                "She remained resilient despite several setbacks.",
                "strong, adaptable, tough",
                "Intermediate",
                "Personality"
            ),

            new Word(
                "Meticulous",
                "Adjective",
                "Showing great attention to detail.",
                "She was meticulous when checking the final report.",
                "careful, precise, thorough",
                "Advanced",
                "Personality"
            ),

            new Word(
                "Benevolent",
                "Adjective",
                "Well meaning and kindly.",
                "The benevolent teacher helped the student.",
                "kind, generous, charitable",
                "Advanced",
                "Personality"
            ),

            new Word(
                "Compassionate",
                "Adjective",
                "Showing sympathy and concern for others.",
                "The compassionate doctor listened carefully to the patient.",
                "kind, caring, sympathetic",
                "Intermediate",
                "Personality"
            ),

            new Word(
                "Empathetic",
                "Adjective",
                "Able to understand another person's feelings.",
                "She was empathetic toward her friend's situation.",
                "understanding, sensitive, compassionate",
                "Intermediate",
                "Personality"
            ),

            new Word(
                "Persistent",
                "Adjective",
                "Continuing firmly despite difficulties.",
                "His persistent effort eventually paid off.",
                "determined, tenacious, consistent",
                "Intermediate",
                "Personality"
            ),

            new Word(
                "Adaptable",
                "Adjective",
                "Able to adjust to new conditions.",
                "An adaptable employee can handle changing situations.",
                "flexible, versatile, adjustable",
                "Intermediate",
                "Personality"
            ),

            new Word(
                "Diligent",
                "Adjective",
                "Showing careful and persistent effort.",
                "She was diligent in completing her assignments.",
                "hardworking, dedicated, industrious",
                "Advanced",
                "Personality"
            ),

            new Word(
                "Humility",
                "Noun",
                "A modest view of one's importance or abilities.",
                "Despite her success, she showed great humility.",
                "modesty, simplicity, unpretentiousness",
                "Advanced",
                "Personality"
            ),

            new Word(
                "Optimistic",
                "Adjective",
                "Hopeful and confident about the future.",
                "He remained optimistic about the project's success.",
                "hopeful, positive, confident",
                "Beginner",
                "Personality"
            ),

            new Word(
                "Reliable",
                "Adjective",
                "Consistently dependable or trustworthy.",
                "She is a reliable member of the team.",
                "dependable, trustworthy, consistent",
                "Beginner",
                "Personality"
            ),

            new Word(
                "Tenacious",
                "Adjective",
                "Not giving up easily.",
                "Her tenacious attitude helped her finish the challenge.",
                "persistent, determined, resolute",
                "Advanced",
                "Personality"
            ),

            new Word(
                "Charismatic",
                "Adjective",
                "Having a compelling charm that attracts others.",
                "The charismatic leader inspired the entire team.",
                "charming, magnetic, influential",
                "Advanced",
                "Personality"
            ),

            new Word(
                "Candid",
                "Adjective",
                "Truthful and straightforward.",
                "He gave a candid opinion about the proposal.",
                "honest, frank, direct",
                "Intermediate",
                "Personality"
            ),

            new Word(
                "Conscientious",
                "Adjective",
                "Careful to do things correctly and responsibly.",
                "She is a conscientious student who meets every deadline.",
                "responsible, careful, diligent",
                "Advanced",
                "Personality"
            ),

            new Word(
                "Generous",
                "Adjective",
                "Willing to give or share freely.",
                "He was generous with his time and advice.",
                "giving, charitable, kind",
                "Beginner",
                "Personality"
            ),

            new Word(
                "Ingenious",
                "Adjective",
                "Very clever and inventive.",
                "She found an ingenious solution to the problem.",
                "creative, inventive, clever",
                "Advanced",
                "Personality"
            ),

            new Word(
                "Versatile",
                "Adjective",
                "Able to adapt to many different activities or situations.",
                "She is a versatile developer who works across technologies.",
                "adaptable, flexible, multifaceted",
                "Intermediate",
                "Personality"
            ),

            new Word(
                "Disciplined",
                "Adjective",
                "Able to control behavior and consistently follow a plan.",
                "A disciplined approach helped him complete the course.",
                "controlled, organized, focused",
                "Intermediate",
                "Personality"
            ),

            new Word(
                "Resourceful",
                "Adjective",
                "Good at finding clever ways to solve problems.",
                "The resourceful student repaired the project with limited tools.",
                "inventive, capable, creative",
                "Advanced",
                "Personality"
            ),

            // =========================
            // ACADEMIC
            // =========================

            new Word(
                "Empirical",
                "Adjective",
                "Based on observation or experiment rather than theory alone.",
                "The study used empirical evidence to support its conclusion.",
                "observational, experimental, factual",
                "Advanced",
                "Academic"
            ),

            new Word(
                "Theoretical",
                "Adjective",
                "Related to ideas or principles rather than practical application.",
                "The course includes both theoretical and practical concepts.",
                "conceptual, abstract, hypothetical",
                "Intermediate",
                "Academic"
            ),

            new Word(
                "Methodology",
                "Noun",
                "A system of methods used in a particular study or activity.",
                "The researcher explained the methodology clearly.",
                "method, procedure, framework",
                "Advanced",
                "Academic"
            ),

            new Word(
                "Hypothetical",
                "Adjective",
                "Based on a possible situation rather than a real one.",
                "The professor presented a hypothetical scenario.",
                "imaginary, theoretical, supposed",
                "Advanced",
                "Academic"
            ),

            new Word(
                "Synthesize",
                "Verb",
                "To combine different ideas or information into a whole.",
                "Students must synthesize information from several sources.",
                "combine, integrate, merge",
                "Advanced",
                "Academic"
            ),

            new Word(
                "Analyze",
                "Verb",
                "To examine something carefully in order to understand it.",
                "We need to analyze the results before making a decision.",
                "examine, investigate, evaluate",
                "Beginner",
                "Academic"
            ),

            new Word(
                "Summarize",
                "Verb",
                "To give the main points of something briefly.",
                "Please summarize the article in your own words.",
                "condense, outline, recap",
                "Beginner",
                "Academic"
            ),

            new Word(
                "Abstract",
                "Adjective",
                "Existing as an idea rather than a physical object.",
                "Justice is an abstract concept.",
                "conceptual, theoretical, intangible",
                "Intermediate",
                "Academic"
            ),

            new Word(
                "Bibliography",
                "Noun",
                "A list of sources used in a piece of academic work.",
                "The report includes a detailed bibliography.",
                "references, sources, citations",
                "Intermediate",
                "Academic"
            ),

            new Word(
                "Citation",
                "Noun",
                "A reference to a source of information.",
                "Every important claim should have an appropriate citation.",
                "reference, quotation, acknowledgment",
                "Intermediate",
                "Academic"
            ),

            new Word(
                "Curriculum",
                "Noun",
                "The subjects and content taught in a course or program.",
                "The university updated its computer science curriculum.",
                "syllabus, program, coursework",
                "Intermediate",
                "Academic"
            ),

            new Word(
                "Dissertation",
                "Noun",
                "A long academic research paper, usually for an advanced degree.",
                "She spent a year completing her dissertation.",
                "thesis, research paper, study",
                "Advanced",
                "Academic"
            ),

            new Word(
                "Inference",
                "Noun",
                "A conclusion reached from evidence and reasoning.",
                "The scientist made an inference from the experimental results.",
                "deduction, conclusion, interpretation",
                "Advanced",
                "Academic"
            ),

            new Word(
                "Objective",
                "Noun",
                "A goal or purpose that someone aims to achieve.",
                "The main objective of the experiment was to test the theory.",
                "goal, aim, purpose",
                "Beginner",
                "Academic"
            ),

            new Word(
                "Criterion",
                "Noun",
                "A standard used to judge or decide something.",
                "Accuracy was an important criterion for evaluation.",
                "standard, measure, requirement",
                "Advanced",
                "Academic"
            ),

            new Word(
                "Relevant",
                "Adjective",
                "Closely connected with the topic or situation.",
                "Please include only relevant information.",
                "related, applicable, pertinent",
                "Intermediate",
                "Academic"
            ),

            new Word(
                "Credible",
                "Adjective",
                "Able to be trusted or believed.",
                "Use credible sources for academic research.",
                "reliable, trustworthy, convincing",
                "Intermediate",
                "Academic"
            ),

            new Word(
                "Plagiarism",
                "Noun",
                "Using someone else's work or ideas without proper acknowledgment.",
                "Students should understand how to avoid plagiarism.",
                "copying, imitation, intellectual theft",
                "Intermediate",
                "Academic"
            ),

            new Word(
                "Quantitative",
                "Adjective",
                "Related to amounts or numerical measurements.",
                "The research used quantitative data.",
                "numerical, measurable, statistical",
                "Advanced",
                "Academic"
            ),

            new Word(
                "Qualitative",
                "Adjective",
                "Related to qualities or characteristics rather than numbers.",
                "The researchers collected qualitative feedback from students.",
                "descriptive, subjective, characteristic",
                "Advanced",
                "Academic"
            ),

            // =========================
            // GENERAL & PROFESSIONAL
            // =========================

            new Word(
                "Inevitable",
                "Adjective",
                "Certain to happen and impossible to avoid.",
                "Change is inevitable as technology develops.",
                "unavoidable, certain, destined",
                "Intermediate",
                "General"
            ),

            new Word(
                "Substantial",
                "Adjective",
                "Large in amount, size, or importance.",
                "The project requires a substantial amount of effort.",
                "considerable, significant, sizeable",
                "Intermediate",
                "General"
            ),

            new Word(
                "Innovative",
                "Adjective",
                "Introducing new ideas or methods.",
                "The team developed an innovative solution.",
                "creative, original, pioneering",
                "Intermediate",
                "Professional"
            ),

            new Word(
                "Efficient",
                "Adjective",
                "Achieving a result with minimal waste of time or resources.",
                "The new system is more efficient.",
                "productive, effective, economical",
                "Beginner",
                "Professional"
            ),

            new Word(
                "Sustainable",
                "Adjective",
                "Able to continue without causing serious long-term harm.",
                "The company is developing sustainable practices.",
                "maintainable, lasting, viable",
                "Intermediate",
                "General"
            ),

            new Word(
                "Feasible",
                "Adjective",
                "Possible and practical to achieve.",
                "The team decided that the proposed solution was feasible.",
                "possible, practical, achievable",
                "Advanced",
                "Professional"
            ),

            new Word(
                "Strategic",
                "Adjective",
                "Related to a plan designed to achieve a specific goal.",
                "The company made a strategic decision to expand.",
                "planned, tactical, deliberate",
                "Intermediate",
                "Professional"
            ),

            new Word(
                "Collaborative",
                "Adjective",
                "Involving people working together toward a common goal.",
                "The project required a collaborative approach.",
                "cooperative, collective, joint",
                "Intermediate",
                "Professional"
            ),

            new Word(
                "Prioritize",
                "Verb",
                "To decide which things are most important.",
                "We need to prioritize the most urgent tasks.",
                "rank, organize, emphasize",
                "Intermediate",
                "Professional"
            ),

            new Word(
                "Allocate",
                "Verb",
                "To distribute something for a particular purpose.",
                "The manager allocated resources to the project.",
                "assign, distribute, designate",
                "Advanced",
                "Professional"
            ),

            new Word(
                "Implement",
                "Verb",
                "To put a plan or idea into effect.",
                "The developers implemented the new feature.",
                "execute, apply, introduce",
                "Intermediate",
                "Professional"
            ),

            new Word(
                "Optimize",
                "Verb",
                "To make something as effective or efficient as possible.",
                "The team optimized the application for better performance.",
                "improve, refine, maximize",
                "Advanced",
                "Professional"
            ),

            new Word(
                "Scalable",
                "Adjective",
                "Able to grow or handle increasing demand effectively.",
                "The company needs a scalable software architecture.",
                "expandable, adaptable, extensible",
                "Advanced",
                "Professional"
            ),

            new Word(
                "Robust",
                "Adjective",
                "Strong and able to work effectively under difficult conditions.",
                "The application needs a robust security system.",
                "strong, reliable, resilient",
                "Advanced",
                "Professional"
            ),

            new Word(
                "Transparent",
                "Adjective",
                "Open and easy to understand or verify.",
                "The organization maintains a transparent process.",
                "clear, open, honest",
                "Intermediate",
                "Professional"
            ),

            new Word(
                "Proactive",
                "Adjective",
                "Taking action before problems occur.",
                "A proactive approach can prevent future issues.",
                "preventive, anticipatory, initiative-taking",
                "Advanced",
                "Professional"
            ),

            new Word(
                "Milestone",
                "Noun",
                "An important stage or event in a project or development.",
                "Completing the prototype was an important milestone.",
                "landmark, achievement, stage",
                "Intermediate",
                "Professional"
            ),

            new Word(
                "Constraint",
                "Noun",
                "A limitation or restriction.",
                "Budget was the biggest constraint on the project.",
                "limitation, restriction, boundary",
                "Advanced",
                "Professional"
            ),

            new Word(
                "Prototype",
                "Noun",
                "An early model used to test an idea or design.",
                "The team built a prototype before developing the final product.",
                "model, sample, preliminary version",
                "Beginner",
                "Professional"
            ),

            new Word(
                "Viable",
                "Adjective",
                "Capable of working successfully or being successful.",
                "We need to determine whether the idea is commercially viable.",
                "feasible, workable, practical",
                "Advanced",
                "Professional"
            )
        );

        int added = 0;

        for (Word word : words) {

            if (!wordRepository.findByWordContainingIgnoreCase(word.getWord()).stream()
                    .anyMatch(existing ->
                            existing.getWord().equalsIgnoreCase(word.getWord()))) {

                wordRepository.save(word);
                added++;
            }
        }

        System.out.println(
                "VocabFlow vocabulary check complete. Added "
                        + added
                        + " new words. Total words: "
                        + wordRepository.count()
        );
    }
}