const SUPABASE_URL = "https://feinvzouqgzmqyvizodo.supabase.co";
const SUPABASE_ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZlaW52em91cWd6bXF5dml6b2RvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3ODkyMTAzMTEsImV4cCI6MjEwNDc4NjMxMX0.wTgivMEiETaiysYqhR8lGv3T62Lbo8wLABgpOPiWWHw";

const supabaseClient = window.supabase.createClient(
    SUPABASE_URL,
    SUPABASE_ANON_KEY
);

// Controlla all'avvio se c'è già una sessione attiva
(async () => {
    const { data: { session } } = await supabaseClient.auth.getSession();

    if (session) {
        const res = await fetch(`/api/citycare/user/${session.user.id}`, {
            headers: { Authorization: `Bearer ${session.access_token}` }
        });
        const profile = await res.json();

        window.location.replace(profile.isAdmin ? "homeAdmin.html" : "homeUser.html");
    }
})();

const loginForm = document.getElementById("loginForm");
const loginMessage = document.getElementById("loginMessage");

loginForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    loginMessage.textContent = "";

    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value;
    const submitButton = loginForm.querySelector('button[type="submit"]');

    submitButton.disabled = true;

    try {
        const { data, error } = await supabaseClient.auth.signInWithPassword({
            email,
            password
        });

        if (error) {
            loginMessage.textContent = "Email o password non corretti.";
            return;
        }

        const response = await fetch(`/api/citycare/user/${data.user.id}`, {
            headers: {
                Authorization: `Bearer ${data.session.access_token}`
            }
        });

        if (!response.ok) {
            throw new Error("Profilo utente non trovato o richiesta non autorizzata.");
        }

        const profile = await response.json();

        if (typeof profile.isAdmin !== "boolean") {
            throw new Error("Il profilo ricevuto non contiene il campo isAdmin.");
        }

        window.location.href = profile.isAdmin
            ? "homeAdmin.html"
            : "homeUser.html";
    } catch (error) {
        console.error("Errore durante il login:", error);
        loginMessage.textContent =
            error.message || "Impossibile completare l'accesso.";
    } finally {
        submitButton.disabled = false;
    }
});