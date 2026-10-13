const supabaseClient = window.supabase.createClient(
    "https://feinvzouqgzmqyvizodo.supabase.co",
    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZlaW52em91cWd6bXF5dml6b2RvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3ODkyMTAzMTEsImV4cCI6MjEwNDc4NjMxMX0.wTgivMEiETaiysYqhR8lGv3T62Lbo8wLABgpOPiWWHw"
);

let accessToken = "";

// impedisce di tornare alla pagina
(async () => {
    const { data: { session } } = await supabaseClient.auth.getSession();

    if (session) {
        // Se vuoi reindirizzare subito (es. a homeUser o controllando il ruolo):
        const res = await fetch(`/api/citycare/user/${session.user.id}`, {
            headers: { Authorization: `Bearer ${session.access_token}` }
        });
        const profile = await res.json();

        window.location.replace(profile.isAdmin ? "homeAdmin.html" : "homeUser.html");
    }
})();

// Gestione del logout
async function Logout() {
    try {
        const { error } = await supabaseClient.auth.signOut();

        if (error) {
            throw error;
        }

    } catch (error) {
        console.error("Errore durante il logout:", error.message);
    } finally {
        localStorage.clear();
        sessionStorage.clear();
        window.location.replace("login.html");
    }
}
const logoutButton = document.getElementById("logoutButton");
if (logoutButton) {
    logoutButton.addEventListener("click", Logout);
}

// GET tutte le richieste
async function getRequests() {
    const response = await fetch('/api/citycare/reports/state/0', {
        method: 'GET',
        headers: {
            Authorization: `Bearer ${accessToken}`
        }
    });
    const requests = await response.json();
    return requests;
}

// GET tutte le soluzioni
async function getSolutions() {
    const response = await fetch('/api/citycare/reports/state/1', {
        method: 'GET',
        headers: {
            Authorization: `Bearer ${accessToken}`
        }
    });
    const solutions = await response.json();
    return solutions;
}


// popola la tabella delle request
async function populateRequests() {
    const requests = await getRequests();
    const reportsContainer = document.getElementById('reportsContainer');
    const numeroRequests = document.getElementById('numero-requests');
    numeroRequests.textContent = requests.length;
    reportsContainer.innerHTML = '';

    requests.forEach(request => {
        let elem = `
            <div class="row">
                <h5 class="card-title col-7 text-primary">${request.title}</h5>
                <span class="text-body-secondary text-end col-5">
                    ${request.timeReport.split('T')[0]}
                </span>
            </div>
            <p class="card-text mb-1">
                ${request.descriptionReport}
                <div class="fw-semibold">${request.address} (${request.residence})</div>
            </p>
            <div class="input-group mb-2">
                <textarea
                class="form-control"
                id="description"
                rows="3"
                placeholder="Descrivi la soluzione..."
                required
                ></textarea>
            </div>
            <div class="col d-flex align-items-end justify-content-end">
                <button
                    type="submit"
                    class="btn btn-primary btn-custom-submit shadow-sm align-items-center justify-content-center gap-2"
                >
                    <span>Invia Soluzione</span>
                </button>
            </div>
            <hr>
            `;
        reportsContainer.insertAdjacentHTML('beforeend', elem);
    });
}

// popola la tabella delle soluzioni
async function populateSolutions() {
    const solutions = await getSolutions();
    const solutionsContainer = document.getElementById('solutionsContainer');
    const numeroSolutions = document.getElementById('numero-solutions');
    numeroSolutions.textContent = solutions.length;
    solutionsContainer.innerHTML = '';

    solutions.forEach(solution => {
        let elem = `
            <div class="row">
                <h5 class="card-title col-7 text-primary">${solution.title}</h5>
                <span class="text-body-secondary text-end col-5">
                    ${solution.timeReport.split('T')[0]}
                </span>
            </div>
            <p class="card-text">
                ${solution.descriptionReport}
                <div class="fw-semibold">${solution.address} (${solution.residence})</div>
            </p>
            <hr>
            `;
        solutionsContainer.insertAdjacentHTML('beforeend', elem);
    });
}

async function initializeAdmin() {
    const { data, error } = await supabaseClient.auth.getSession();

    if (error || !data.session) {
        window.location.replace("login.html");
        return;
    }

    accessToken = data.session.access_token;

    const profileResponse = await fetch(
        `/api/citycare/user/${data.session.user.id}`,
        { headers: { Authorization: `Bearer ${accessToken}` } }
    );

    if (!profileResponse.ok) {
        window.location.replace("login.html");
        return;
    }

    const profile = await profileResponse.json();

    if (profile.isAdmin !== true) {
        window.location.replace("homeUser.html");
        return;
    }

    try {
        await Promise.all([
            populateRequests(),
            populateSolutions()
        ]);
    } catch (error) {
        console.error("Errore nel caricamento della pagina admin:", error);
    }
}

initializeAdmin();