// GET tutte le richieste
async function getRequests() {
    const response = await fetch('/api/citycare/reports/state/0', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
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
            'Content-Type': 'application/json',
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

populateRequests();
populateSolutions();