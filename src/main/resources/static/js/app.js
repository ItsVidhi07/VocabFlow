/* =========================================================
   VocabFlow — Frontend Application
   ========================================================= */

const API_BASE = "/api/words";
const DAILY_GOAL = 5;

let words = [];
let currentWordIndex = 0;

let savedWords = JSON.parse(
    localStorage.getItem("vocabflow_saved") || "[]"
);

let learnedWords = JSON.parse(
    localStorage.getItem("vocabflow_learned") || "[]"
);

let quizScore = Number(
    localStorage.getItem("vocabflow_quiz_score") || 0
);

let quizAttempts = Number(
    localStorage.getItem("vocabflow_quiz_attempts") || 0
);


/* =========================================================
   QUIZ STATE
   ========================================================= */

let quizQuestions = [];
let currentQuizIndex = 0;
let currentQuizScore = 0;


/* =========================================================
   INITIALIZE
   ========================================================= */

document.addEventListener("DOMContentLoaded", () => {

    setupNavigation();
    setupButtons();
    setupSearch();

    updateStats();
    updateDailyGoal();

    loadWords();

});


/* =========================================================
   LOAD WORDS FROM SPRING BOOT
   ========================================================= */

async function loadWords() {

    try {

        const response = await fetch(API_BASE);

        if (!response.ok) {
            throw new Error("Could not load vocabulary.");
        }

        words = await response.json();

        console.log("Vocabulary loaded:", words);

        if (words.length === 0) {
            showEmptyVocabulary();
            return;
        }

        currentWordIndex = 0;

        renderDashboardWord();
        renderLearningWord();
        renderWordList();
        updateWordCount();
        updateStats();
        updateDailyGoal();

        initializeQuiz();

    } catch (error) {

        console.error(error);

        const learnWord =
            document.getElementById("learn-word");

        const learnMeaning =
            document.getElementById("learn-meaning");

        if (learnWord) {
            learnWord.textContent =
                "Unable to load words";
        }

        if (learnMeaning) {
            learnMeaning.textContent =
                "Make sure your Spring Boot server is running.";
        }

    }
}


/* =========================================================
   NAVIGATION
   ========================================================= */

function setupNavigation() {

    const navButtons =
        document.querySelectorAll("[data-section]");

    navButtons.forEach(button => {

        button.addEventListener("click", () => {

            const sectionName =
                button.dataset.section;

            showSection(sectionName);

        });

    });
}


function showSection(sectionName) {

    document.querySelectorAll(".page-section")
        .forEach(section => {

            section.classList.remove(
                "active-section"
            );

        });


    const selectedSection =
        document.getElementById(sectionName);

    if (selectedSection) {

        selectedSection.classList.add(
            "active-section"
        );

    }


    /* Desktop navigation */

    document.querySelectorAll(".nav-item")
        .forEach(item => {

            item.classList.toggle(
                "active",
                item.dataset.section === sectionName
            );

        });


    /* Mobile navigation */

    document.querySelectorAll(".mobile-nav-item")
        .forEach(item => {

            item.classList.toggle(
                "active",
                item.dataset.section === sectionName
            );

        });


    window.scrollTo({
        top: 0,
        behavior: "smooth"
    });

}


/* =========================================================
   BUTTONS
   ========================================================= */

function setupButtons() {

    document.querySelectorAll("[data-go]")
        .forEach(button => {

            button.addEventListener("click", () => {

                showSection(
                    button.dataset.go
                );

            });

        });


    const nextButton =
        document.getElementById("next-word");

    if (nextButton) {

        nextButton.addEventListener(
            "click",
            nextWord
        );

    }


    const saveButton =
        document.getElementById("save-word-button");

    if (saveButton) {

        saveButton.addEventListener(
            "click",
            toggleSaveCurrentWord
        );

    }


    const knownButton =
        document.getElementById("learning-known");

    if (knownButton) {

        knownButton.addEventListener(
            "click",
            markCurrentWordLearned
        );

    }

}


/* =========================================================
   DASHBOARD
   ========================================================= */

function renderDashboardWord() {

    if (!words.length) return;

    const word =
        words[currentWordIndex];

    const container =
        document.getElementById("dashboard-word");

    if (!container) return;

    container.innerHTML = `

        <div class="word-meta">

            <span class="difficulty ${getDifficultyClass(word.difficulty)}">
                ${escapeHtml(word.difficulty.toUpperCase())}
            </span>

            <span>
                ${escapeHtml(word.partOfSpeech)}
            </span>

        </div>

        <h4>
            ${escapeHtml(word.word)}
        </h4>

        <p class="featured-meaning">
            ${escapeHtml(word.meaning)}
        </p>

    `;
}


/* =========================================================
   LEARNING WORD
   ========================================================= */

function renderLearningWord() {

    if (!words.length) return;

    const word =
        words[currentWordIndex];


    const learnWord =
        document.getElementById("learn-word");

    const learnPart =
        document.getElementById("learn-part");

    const learnMeaning =
        document.getElementById("learn-meaning");

    const learnExample =
        document.getElementById("learn-example");

    const learnCategory =
        document.getElementById("learn-category");

    const difficulty =
        document.getElementById("learn-difficulty");


    if (learnWord) {
        learnWord.textContent =
            word.word;
    }

    if (learnPart) {
        learnPart.textContent =
            word.partOfSpeech;
    }

    if (learnMeaning) {
        learnMeaning.textContent =
            word.meaning;
    }

    if (learnExample) {
        learnExample.textContent =
            word.exampleSentence ||
            "No example available.";
    }

    if (learnCategory) {
        learnCategory.textContent =
            word.category;
    }

    if (difficulty) {

        difficulty.textContent =
            word.difficulty.toUpperCase();

        difficulty.className =
            `difficulty ${getDifficultyClass(word.difficulty)}`;

    }


    /* Synonyms */

    const synonymsContainer =
        document.getElementById("learn-synonyms");

    if (synonymsContainer) {

        synonymsContainer.innerHTML = "";

        if (word.synonyms) {

            word.synonyms
                .split(",")
                .map(item => item.trim())
                .filter(Boolean)
                .forEach(synonym => {

                    const element =
                        document.createElement("span");

                    element.className =
                        "synonym";

                    element.textContent =
                        synonym;

                    synonymsContainer.appendChild(
                        element
                    );

                });

        }

    }


    updateSaveButton();
    animateLearningCard();

}


/* =========================================================
   NEXT WORD
   ========================================================= */

function nextWord() {

    if (!words.length) return;

    currentWordIndex++;

    if (currentWordIndex >= words.length) {
        currentWordIndex = 0;
    }

    renderLearningWord();
    renderDashboardWord();

}


/* =========================================================
   MARK WORD AS LEARNED
   ========================================================= */

function markCurrentWordLearned() {

    if (!words.length) return;

    const word =
        words[currentWordIndex];


    if (!learnedWords.includes(word.id)) {

        learnedWords.push(word.id);

        localStorage.setItem(
            "vocabflow_learned",
            JSON.stringify(learnedWords)
        );


        /* ---------------------------------------------
           DAILY GOAL
           --------------------------------------------- */

        const todayLearned =
            Number(
                localStorage.getItem(
                    "vocabflow_today_learned"
                ) || 0
            );


        localStorage.setItem(
            "vocabflow_today_learned",
            todayLearned + 1
        );


        updateDailyGoal();

    }


    updateStats();


    const button =
        document.getElementById(
            "learning-known"
        );


    if (!button) return;


    button.textContent =
        "✓ Added to progress";

    button.style.background =
        "#dfece6";

    button.style.color =
        "#52796f";


    setTimeout(() => {

        button.textContent =
            "I know this";

        button.style.background =
            "";

        button.style.color =
            "";

        nextWord();

    }, 700);

}


/* =========================================================
   WORD LIST
   ========================================================= */

function renderWordList() {

    const container =
        document.getElementById("word-list");

    if (!container) return;

    container.innerHTML = "";


    words.forEach((word, index) => {

        const item =
            document.createElement("div");

        item.className =
            "word-item";


        item.innerHTML = `

            <div>

                <strong>
                    ${escapeHtml(word.word)}
                </strong>

                <span>
                    ${escapeHtml(word.partOfSpeech)}
                    ·
                    ${escapeHtml(word.category)}
                </span>

            </div>

            <span class="word-arrow">
                →
            </span>

        `;


        item.addEventListener("click", () => {

            currentWordIndex =
                index;

            renderLearningWord();
            renderDashboardWord();

        });


        container.appendChild(item);

    });

}


function updateWordCount() {

    const element =
        document.getElementById("word-count");

    if (element) {

        element.textContent =
            `${words.length} words`;

    }

}


/* =========================================================
   SEARCH
   ========================================================= */

function setupSearch() {

    const input =
        document.getElementById("search-input");

    if (!input) return;


    let timeout;


    input.addEventListener("input", () => {

        clearTimeout(timeout);


        const query =
            input.value.trim();


        if (!query) {

            hideSearchResults();
            return;

        }


        timeout = setTimeout(() => {

            searchWords(query);

        }, 250);

    });

}


async function searchWords(query) {

    try {

        const response =
            await fetch(
                `${API_BASE}/search?query=${encodeURIComponent(query)}`
            );


        if (!response.ok) {
            throw new Error("Search failed.");
        }


        const results =
            await response.json();


        renderSearchResults(results);


    } catch (error) {

        console.error(error);

    }

}


function renderSearchResults(results) {

    const container =
        document.getElementById(
            "search-results"
        );

    if (!container) return;


    container.innerHTML = "";


    if (!results.length) {

        container.innerHTML = `

            <div class="search-result">

                <span>
                    No words found.
                </span>

            </div>

        `;

        container.classList.add("visible");

        return;

    }


    results.forEach(word => {

        const result =
            document.createElement("div");

        result.className =
            "search-result";


        result.innerHTML = `

            <div>

                <strong>
                    ${escapeHtml(word.word)}
                </strong>

                <span>
                    ${escapeHtml(word.meaning)}
                </span>

            </div>

            <span>
                →
            </span>

        `;


        result.addEventListener(
            "click",
            () => {

                const index =
                    words.findIndex(
                        item =>
                            item.id === word.id
                    );


                if (index !== -1) {

                    currentWordIndex =
                        index;

                    renderLearningWord();
                    renderDashboardWord();

                }


                hideSearchResults();


                const searchInput =
                    document.getElementById(
                        "search-input"
                    );

                if (searchInput) {
                    searchInput.value = "";
                }

            }
        );


        container.appendChild(result);

    });


    container.classList.add("visible");

}


function hideSearchResults() {

    const container =
        document.getElementById(
            "search-results"
        );

    if (!container) return;

    container.classList.remove(
        "visible"
    );

    container.innerHTML = "";

}


/* =========================================================
   SAVED WORDS
   ========================================================= */

function toggleSaveCurrentWord() {

    if (!words.length) return;

    const word =
        words[currentWordIndex];


    const existingIndex =
        savedWords.indexOf(word.id);


    if (existingIndex === -1) {

        savedWords.push(word.id);

    } else {

        savedWords.splice(
            existingIndex,
            1
        );

    }


    localStorage.setItem(
        "vocabflow_saved",
        JSON.stringify(savedWords)
    );


    updateSaveButton();
    renderSavedWords();
    updateStats();

}


function updateSaveButton() {

    const button =
        document.getElementById(
            "save-word-button"
        );


    if (!button || !words.length) return;


    const word =
        words[currentWordIndex];


    const isSaved =
        savedWords.includes(word.id);


    if (isSaved) {

        button.textContent =
            "♥";

        button.classList.add(
            "saved"
        );

        button.title =
            "Remove from saved words";

    } else {

        button.textContent =
            "♡";

        button.classList.remove(
            "saved"
        );

        button.title =
            "Save word";

    }

}


function renderSavedWords() {

    const container =
        document.getElementById(
            "saved-list"
        );

    const empty =
        document.getElementById(
            "saved-empty"
        );


    if (!container || !empty) return;


    container.innerHTML = "";


    const saved =
        words.filter(word =>
            savedWords.includes(word.id)
        );


    if (!saved.length) {

        empty.style.display =
            "flex";

        return;

    }


    empty.style.display =
        "none";


    saved.forEach(word => {

        const card =
            document.createElement("div");


        card.className =
            "saved-word-card";


        card.innerHTML = `

            <button
                class="saved-remove"
                title="Remove word">
                ×
            </button>

            <div class="word-meta">

                <span class="difficulty ${getDifficultyClass(word.difficulty)}">
                    ${escapeHtml(word.difficulty.toUpperCase())}
                </span>

                <span>
                    ${escapeHtml(word.partOfSpeech)}
                </span>

            </div>

            <h3>
                ${escapeHtml(word.word)}
            </h3>

            <p>
                ${escapeHtml(word.meaning)}
            </p>

        `;


        const removeButton =
            card.querySelector(
                ".saved-remove"
            );


        removeButton.addEventListener(
            "click",
            () => {

                savedWords =
                    savedWords.filter(
                        id => id !== word.id
                    );


                localStorage.setItem(
                    "vocabflow_saved",
                    JSON.stringify(savedWords)
                );


                renderSavedWords();
                updateSaveButton();
                updateStats();

            }
        );


        container.appendChild(card);

    });

}


/* =========================================================
   STATS
   ========================================================= */

function updateStats() {

    const learnedElement =
        document.getElementById(
            "words-learned"
        );

    const savedElement =
        document.getElementById(
            "saved-count"
        );

    const accuracyElement =
        document.getElementById(
            "quiz-accuracy"
        );

    const progressTotal =
        document.getElementById(
            "progress-total"
        );

    const progressAccuracy =
        document.getElementById(
            "progress-accuracy"
        );


    const learnedCount =
        learnedWords.length;


    const accuracy =
        quizAttempts > 0
            ? Math.round(
                (quizScore / quizAttempts) * 100
            )
            : 0;


    if (learnedElement) {

        learnedElement.textContent =
            learnedCount;

    }


    if (savedElement) {

        savedElement.textContent =
            savedWords.length;

    }


    if (accuracyElement) {

        accuracyElement.textContent =
            `${accuracy}%`;

    }


    if (progressTotal) {

        progressTotal.textContent =
            learnedCount;

    }


    if (progressAccuracy) {

        progressAccuracy.textContent =
            `${accuracy}%`;

    }


    const progressFill =
        document.getElementById(
            "big-progress-fill"
        );


    if (progressFill) {

        const percentage =
            words.length
                ? Math.min(
                    (learnedCount / words.length) * 100,
                    100
                )
                : 0;


        progressFill.style.width =
            `${percentage}%`;

    }


    renderSavedWords();

}


/* =========================================================
   QUIZ
   ========================================================= */

function initializeQuiz() {

    if (words.length < 2) return;

    createQuizQuestions();

    currentQuizIndex = 0;
    currentQuizScore = 0;

    renderQuizQuestion();

}


function createQuizQuestions() {

    const shuffled =
        [...words].sort(
            () => Math.random() - 0.5
        );


    quizQuestions =
        shuffled.slice(
            0,
            Math.min(
                5,
                shuffled.length
            )
        );

}


function renderQuizQuestion() {

    if (!quizQuestions.length) return;


    const word =
        quizQuestions[
            currentQuizIndex
        ];


    const quizNumber =
        document.getElementById(
            "quiz-number"
        );

    const quizProgress =
        document.getElementById(
            "quiz-progress-fill"
        );

    const quizWord =
        document.getElementById(
            "quiz-word"
        );

    const optionsContainer =
        document.getElementById(
            "quiz-options"
        );

    const result =
        document.getElementById(
            "quiz-result"
        );


    if (quizNumber) {

        quizNumber.textContent =
            `Question ${currentQuizIndex + 1} of ${quizQuestions.length}`;

    }


    if (quizProgress) {

        quizProgress.style.width =
            `${((currentQuizIndex + 1) / quizQuestions.length) * 100}%`;

    }


    if (quizWord) {

        quizWord.textContent =
            word.word;

    }


    if (!optionsContainer) return;


    optionsContainer.innerHTML = "";


    const options =
        generateQuizOptions(word);


    options.forEach(option => {

        const button =
            document.createElement(
                "button"
            );


        button.className =
            "quiz-option";


        button.textContent =
            option.meaning;


        button.addEventListener(
            "click",
            () =>
                handleQuizAnswer(
                    button,
                    option,
                    word
                )
        );


        optionsContainer.appendChild(
            button
        );

    });


    if (result) {

        result.classList.add(
            "hidden"
        );

        result.textContent = "";

    }

}


function generateQuizOptions(correctWord) {

    const incorrect =
        words
            .filter(
                word =>
                    word.id !==
                    correctWord.id
            )
            .sort(
                () =>
                    Math.random() - 0.5
            )
            .slice(0, 3);


    const options = [
        correctWord,
        ...incorrect
    ];


    return options.sort(
        () =>
            Math.random() - 0.5
    );

}


/* =========================================================
   HANDLE QUIZ ANSWER
   ========================================================= */

function handleQuizAnswer(
    clickedButton,
    selectedWord,
    correctWord
) {

    const buttons =
        document.querySelectorAll(
            ".quiz-option"
        );


    buttons.forEach(button => {

        button.disabled =
            true;

    });


    const correct =
        selectedWord.id ===
        correctWord.id;


    if (correct) {

        clickedButton.classList.add(
            "correct"
        );

        currentQuizScore++;
        quizScore++;

    } else {

        clickedButton.classList.add(
            "wrong"
        );


        buttons.forEach(button => {

            if (
                button.textContent ===
                correctWord.meaning
            ) {

                button.classList.add(
                    "correct"
                );

            }

        });

    }


    /* Overall statistics */

    quizAttempts++;


    localStorage.setItem(
        "vocabflow_quiz_score",
        quizScore
    );


    localStorage.setItem(
        "vocabflow_quiz_attempts",
        quizAttempts
    );


    /* Question feedback */

    const result =
        document.getElementById(
            "quiz-result"
        );


    if (result) {

        result.classList.remove(
            "hidden"
        );


        result.textContent =
            correct
                ? "✓ Correct! Nice work."
                : `Not quite. The correct meaning is: ${correctWord.meaning}`;

    }


    updateStats();


    /* Wait before moving to next question */

    setTimeout(() => {

        currentQuizIndex++;


        if (
            currentQuizIndex >=
            quizQuestions.length
        ) {

            showQuizResults();
            return;

        }


        renderQuizQuestion();

    }, 1400);

}


/* =========================================================
   SHOW QUIZ RESULTS
   ========================================================= */

function showQuizResults() {

    const total =
        quizQuestions.length;


    const accuracy =
        total > 0
            ? Math.round(
                (currentQuizScore / total) * 100
            )
            : 0;


    const quizNumber =
        document.getElementById(
            "quiz-number"
        );

    const quizWord =
        document.getElementById(
            "quiz-word"
        );

    const optionsContainer =
        document.getElementById(
            "quiz-options"
        );

    const result =
        document.getElementById(
            "quiz-result"
        );

    const progress =
        document.getElementById(
            "quiz-progress-fill"
        );


    if (progress) {

        progress.style.width =
            "100%";

    }


    if (quizNumber) {

        quizNumber.textContent =
            "Quiz Complete";

    }


    if (quizWord) {

        quizWord.innerHTML = `

            <span class="quiz-result-title">
                ${currentQuizScore} / ${total}
            </span>

        `;

    }


    if (optionsContainer) {

        optionsContainer.innerHTML = `

            <div class="quiz-final-result">

                <div class="quiz-final-score">
                    ${accuracy}%
                </div>

                <div class="quiz-final-label">
                    Accuracy
                </div>

                <p>
                    You answered
                    <strong>${currentQuizScore}</strong>
                    out of
                    <strong>${total}</strong>
                    questions correctly.
                </p>

                <button
                    id="restart-quiz"
                    class="quiz-restart-button">

                    Try Another Quiz

                </button>

            </div>

        `;


        const restartButton =
            document.getElementById(
                "restart-quiz"
            );


        if (restartButton) {

            restartButton.addEventListener(
                "click",
                startNewQuiz
            );

        }

    }


    if (result) {

        result.classList.add(
            "hidden"
        );

        result.textContent = "";

    }


    updateStats();

}


/* =========================================================
   START NEW QUIZ
   ========================================================= */

function startNewQuiz() {

    createQuizQuestions();

    currentQuizIndex = 0;
    currentQuizScore = 0;

    renderQuizQuestion();

}


/* =========================================================
   DAILY GOAL
   ========================================================= */

function updateDailyGoal() {

    /*
     * Use the user's local date instead of UTC.
     * This is important for India because toISOString()
     * can sometimes return the previous/next date.
     */

    const now =
        new Date();

    const today =
        `${now.getFullYear()}-${String(
            now.getMonth() + 1
        ).padStart(2, "0")}-${String(
            now.getDate()
        ).padStart(2, "0")}`;


    const storedDate =
        localStorage.getItem(
            "vocabflow_goal_date"
        );


    /*
     * If this is a new day, reset today's count.
     */

    if (storedDate !== today) {

        localStorage.setItem(
            "vocabflow_goal_date",
            today
        );


        localStorage.setItem(
            "vocabflow_today_learned",
            "0"
        );

    }


    const todayLearned =
        Number(
            localStorage.getItem(
                "vocabflow_today_learned"
            ) || 0
        );


    const completed =
        Math.min(
            todayLearned,
            DAILY_GOAL
        );


    const goalText =
        document.getElementById(
            "daily-goal-text"
        );


    const goalFill =
        document.getElementById(
            "daily-goal-fill"
        );


    const goalMessage =
        document.getElementById(
            "daily-goal-message"
        );


    if (goalText) {

        goalText.textContent =
            `${completed} / ${DAILY_GOAL}`;

    }


    if (goalFill) {

        goalFill.style.width =
            `${(completed / DAILY_GOAL) * 100}%`;

    }


    if (goalMessage) {

        const remaining =
            DAILY_GOAL - completed;


        if (remaining <= 0) {

            goalMessage.textContent =
                "Daily goal complete. Great work!";

        } else if (remaining === 1) {

            goalMessage.textContent =
                "One more word today.";

        } else {

            goalMessage.textContent =
                `${remaining} more words today.`;

        }

    }

}


/* =========================================================
   VISUAL HELPERS
   ========================================================= */

function animateLearningCard() {

    const card =
        document.querySelector(
            ".learning-card"
        );


    if (!card) return;


    card.animate(
        [
            {
                opacity: 0.65,
                transform: "translateY(5px)"
            },
            {
                opacity: 1,
                transform: "translateY(0)"
            }
        ],
        {
            duration: 300,
            easing: "ease-out"
        }
    );

}


function getDifficultyClass(difficulty) {

    if (!difficulty) {
        return "intermediate";
    }


    return difficulty.toLowerCase() ===
        "advanced"

        ? "advanced"

        : "intermediate";

}


function showEmptyVocabulary() {

    const element =
        document.getElementById(
            "learn-word"
        );


    if (element) {

        element.textContent =
            "No vocabulary yet.";

    }

}


/* =========================================================
   SECURITY / HTML ESCAPING
   ========================================================= */

function escapeHtml(value) {

    if (
        value === null ||
        value === undefined
    ) {

        return "";

    }


    return String(value)

        .replace(
            /&/g,
            "&amp;"
        )

        .replace(
            /</g,
            "&lt;"
        )

        .replace(
            />/g,
            "&gt;"
        )

        .replace(
            /"/g,
            "&quot;"
        )

        .replace(
            /'/g,
            "&#039;"
        );

}