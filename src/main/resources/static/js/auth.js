document.addEventListener("DOMContentLoaded", () => {

    const registerForm = document.getElementById("register-form");
    const loginForm = document.getElementById("login-form");


    /* =====================================================
       REGISTER
       ===================================================== */

    if (registerForm) {

        registerForm.addEventListener("submit", async (event) => {

            event.preventDefault();

            const fullName =
                document.getElementById("fullName").value.trim();

            const username =
                document.getElementById("username").value.trim();

            const email =
                document.getElementById("email").value.trim();

            const password =
                document.getElementById("password").value;

            const message =
                document.getElementById("register-message");

            const button =
                registerForm.querySelector("button");

            message.className = "auth-message hidden";
            message.textContent = "";

            button.disabled = true;
            button.textContent = "Creating account...";


            try {

                const response = await fetch("/api/auth/register", {

                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify({
                        fullName,
                        username,
                        email,
                        password
                    })

                });


                const data = await response.json();


                if (!response.ok) {

                    message.className =
                        "auth-message error";

                    message.textContent =
                        data.message || "Unable to create account.";

                    button.disabled = false;
                    button.innerHTML =
                        'Create account <span>→</span>';

                    return;
                }


                message.className =
                    "auth-message success";

                message.textContent =
                    "Account created successfully. Redirecting to sign in...";


                setTimeout(() => {
                    window.location.href = "/login.html";
                }, 1000);


            } catch (error) {

                console.error(error);

                message.className =
                    "auth-message error";

                message.textContent =
                    "Could not connect to VocabFlow. Make sure the server is running.";

                button.disabled = false;

                button.innerHTML =
                    'Create account <span>→</span>';
            }

        });

    }


    /* =====================================================
       LOGIN
       ===================================================== */

    if (loginForm) {

        loginForm.addEventListener("submit", async (event) => {

            event.preventDefault();

            const usernameOrEmail =
                document
                    .getElementById("usernameOrEmail")
                    .value
                    .trim();

            const password =
                document.getElementById("login-password").value;

            const message =
                document.getElementById("login-message");

            const button =
                loginForm.querySelector("button");

            message.className = "auth-message hidden";
            message.textContent = "";

            button.disabled = true;
            button.textContent = "Signing in...";


            try {

                const response = await fetch("/api/auth/login", {

                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify({
                        usernameOrEmail,
                        password
                    })

                });


                const data = await response.json();


                if (!response.ok) {

                    message.className =
                        "auth-message error";

                    message.textContent =
                        data.message || "Invalid login details.";

                    button.disabled = false;

                    button.innerHTML =
                        'Sign in <span>→</span>';

                    return;
                }


                message.className =
                    "auth-message success";

                message.textContent =
                    "Login successful. Opening your dashboard...";


                setTimeout(() => {
                    window.location.href = "/dashboard.html";
                }, 600);


            } catch (error) {

                console.error(error);

                message.className =
                    "auth-message error";

                message.textContent =
                    "Could not connect to VocabFlow. Make sure the server is running.";

                button.disabled = false;

                button.innerHTML =
                    'Sign in <span>→</span>';
            }

        });

    }

});

/* =====================================================
   DASHBOARD SESSION PROTECTION
   ===================================================== */

async function checkDashboardSession() {

    const isDashboard =
        window.location.pathname.endsWith("/dashboard.html");

    if (!isDashboard) {
        return;
    }

    try {

        const response =
            await fetch("/api/auth/session");

        if (!response.ok) {

            window.location.href = "/login.html";
            return;
        }

        const user = await response.json();

        console.log("Logged in as:", user.username);

        // Store current user temporarily for dashboard UI
        window.vocabflowUser = user;

    } catch (error) {

        console.error("Session check failed:", error);

        window.location.href = "/login.html";
    }
}


checkDashboardSession();

/* =====================================================
   LOGOUT
   ===================================================== */

const logoutButton =
    document.getElementById("logout-button");

if (logoutButton) {

    logoutButton.addEventListener("click", async () => {

        logoutButton.disabled = true;
        logoutButton.textContent = "Logging out...";

        try {

            const response =
                await fetch("/api/auth/logout", {
                    method: "POST"
                });

            if (response.ok) {

                window.location.href = "/";

            } else {

                logoutButton.disabled = false;
                logoutButton.textContent = "Logout";

                alert("Could not log out. Please try again.");
            }

        } catch (error) {

            console.error("Logout failed:", error);

            logoutButton.disabled = false;
            logoutButton.textContent = "Logout";

            alert("Could not connect to VocabFlow.");
        }

    });

}