// GET richieste dell'utente
async function getRequests() {
    const response = await fetch('/api/citycare/reports/user/9bd1310f-c1d6-488c-9d27-ba156829e6ec/state/false', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    });
    const requests = await response.json();
    return requests;
}

// GET soluzioni dell'utente
async function getSolutions() {
    const response = await fetch('/api/citycare/reports/user/9bd1310f-c1d6-488c-9d27-ba156829e6ec/state/true', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    });
    const solutions = await response.json();
    return solutions;
}

// variabile globale per contenere id utente
let userId = "";
let residence = ""
// GET informazioni dell'utente
async function getUserInfo() {
    const response = await fetch('/api/citycare/user/9bd1310f-c1d6-488c-9d27-ba156829e6ec', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    });
    const userInfo = await response.json();
    userId = userInfo.userId;
    residence = userInfo.residence;
    return userInfo;
}

// POST nuova richiesta
async function postRequest() {
    const reportsTitle = document.getElementById('saveTitle').value;
    const reportsAddress = document.getElementById('saveAddress').value;
    const reportsDescription = document.getElementById('saveDescription').value;

    const requestData = {
        title: reportsTitle,
        address: reportsAddress,
        descriptionReport: reportsDescription,
        userId: userId,
        residence: residence
    };

    if (reportsTitle && reportsAddress && reportsDescription) {
        const response = await fetch('/api/citycare/reports/reports', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(requestData)
        });
        if (response.ok) {
            window.location.reload();
        }
    }
}

// popola la sezione utente
async function populateUserInfo() {
    const userInfo = await getUserInfo();
    document.getElementById('nome').textContent = userInfo.name;
    document.getElementById('cognome').textContent = userInfo.surname;
    document.getElementById('residenza').textContent = userInfo.residence;
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

    solutions.forEach(solution => {
        let elem = `
            <div class="row">
                <h5 class="card-title col-6 text-primary">${solution.title}</h5>
                <span class="text-body-secondary text-end col-6">
                    ${solution.timeReport.split('T')[0]}
                </span>
            </div>
            <p class="card-text">
                ${solution.descriptionReport}
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

populateUserInfo();
populateRequests();
populateSolutions();