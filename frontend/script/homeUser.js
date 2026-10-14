const supabaseClient = window.supabase.createClient(
    "https://feinvzouqgzmqyvizodo.supabase.co",
    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZlaW52em91cWd6bXF5dml6b2RvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3ODkyMTAzMTEsImV4cCI6MjEwNDc4NjMxMX0.wTgivMEiETaiysYqhR8lGv3T62Lbo8wLABgpOPiWWHw"
);

// variabile globale per contenere info utente
let userId = "";
let residence = "";
let accessToken = "";

// gestisce il reindirizzamento alla pagina del login se il token non è valido
async function handleUnauthorized(response) {
    if (response.status === 401) {
        await supabaseClient.auth.signOut();
        localStorage.clear();
        sessionStorage.clear();
        window.location.replace("login.html");
        throw new Error("Sessione scaduta o token non valido");
    }

    return response;
}

// Gestione del logout
async function Logout() {
    try {
        const { error } = await supabaseClient.auth.signOut();

        if (error) {
            throw error;
        }

        localStorage.clear();
        console.log(localStorage);
        sessionStorage.clear();

    } catch (error) {
        console.error("Errore durante il logout:", error.message);
    } finally {
        window.location.replace("login.html");
    }
}
const logoutButton = document.getElementById("logoutButton");
if (logoutButton) {
    logoutButton.addEventListener("click", Logout);
}

// GET informazioni dell'utente
async function getUserInfo() {
    const { data: { session } } = await supabaseClient.auth.getSession();

    if (!session) {
        window.location.href = "login.html";
        return null;
    }

    accessToken = session.access_token;

    const response = await fetch(`/api/citycare/user/${session.user.id}`, {
        headers: {
            Authorization: `Bearer ${accessToken}`
        }
    });

    await handleUnauthorized(response);

    if (!response.ok) {
        throw new Error("Impossibile caricare il profilo utente.");
    }

    const userInfo = await response.json();
    userId = userInfo.userId;
    residence = userInfo.residence;

    return userInfo;
}

// GET richieste dell'utente
async function getRequests() {
    const response = await fetch(
        `/api/citycare/reports/user/${userId}/state/false`,
        {
            method: "GET",
            headers: {
                Authorization: `Bearer ${accessToken}`
            }
        }
    );

    await handleUnauthorized(response);

    return await response.json();
}

// GET soluzioni dell'utente
async function getSolutions() {
    const response = await fetch(
        `/api/citycare/solutions/user/${userId}/state/true`,
        {
            method: "GET",
            headers: {
                Authorization: `Bearer ${accessToken}`
            }
        }
    );

    await handleUnauthorized(response);

    return await response.json();
}

// POST nuova richiesta
async function postRequest() {
    const reportsTitle = document.getElementById("saveTitle").value;
    const reportsAddress = document.getElementById("saveAddress").value;
    const reportsDescription = document.getElementById("saveDescription").value;

    const requestData = {
        title: reportsTitle,
        address: reportsAddress,
        descriptionReport: reportsDescription,
        userId: userId,
        residence: residence
    };

    if (!reportsTitle || !reportsAddress || !reportsDescription) {
        return;
    }

    const response = await fetch("/api/citycare/reports", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${accessToken}`
        },
        body: JSON.stringify(requestData)
    });

    await handleUnauthorized(response);

    if (response.ok) {
        window.location.reload();
    } else {
        console.error("Invio segnalazione fallito:", response.status, await response.text());
    }
}

// popola la sezione utente
async function populateUserInfo() {
    const userInfo = await getUserInfo();

    if (!userInfo) {
        window.location.href = "login.html";
        return false;
    }

    document.getElementById("nome").textContent = userInfo.name;
    document.getElementById("cognome").textContent = userInfo.surname;
    document.getElementById("residenza").textContent = userInfo.residence;
    return true;
}

// caricamento pagina utente, previa verifica
async function initializeHomeUser() {
    try {
        const profileLoaded = await populateUserInfo();

        if (!profileLoaded) {
            return;
        }

        await Promise.all([
            populateRequests(),
            populateSolutions()
        ]);
    } catch (error) {
        console.error("Errore nel caricamento della pagina utente:", error);
    }
}

// popola la tabella delle request
async function populateRequests() {
    const requests = await getRequests();
    const reportsContainer = document.getElementById('reportsContainer');
    reportsContainer.innerHTML = '';

    requests.forEach(request => {
        let elem = `
            <div class="row">
                <h5 class="card-title col-6 text-primary">${request.title}</h5>
                <span class="text-body-secondary text-end col-6">
                    ${request.timeReport.split('T')[0]}
                </span>
            </div>
            <p class="card-text">
                ${request.descriptionReport}
                <div class="fw-semibold">${request.address}</div>
            </p>
            <hr>
            `;
        reportsContainer.insertAdjacentHTML('beforeend', elem);
    });
}

// popola la tabella delle solution
async function populateSolutions() {
    const solutions = await getSolutions();
    const solutionsContainer = document.getElementById('solutionsContainer');
    solutionsContainer.innerHTML = '';
    console.log(solutions);

    solutions.forEach(solution => {
        let elem = `
            <div class="row">
                <h5 class="card-title col-6 text-primary">${solution.title}</h5>
                <span class="text-body-secondary text-end col-6">
                    ${solution.timeReport.split('T')[0]}
                </span>
            </div>
            <p class="card-text">
                ${solution.descriptionSolution}
                <div class="fw-semibold">${solution.address}</div>
            </p>
            <hr>
            `;
        solutionsContainer.insertAdjacentHTML('beforeend', elem);
    });
}

document.getElementById('reportForm').addEventListener('submit', async (event) => {
    event.preventDefault();
    await postRequest();
});

initializeHomeUser();