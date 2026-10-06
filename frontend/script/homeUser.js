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

// popola la tabella delle request
async function populateRequests() {
    const requests = await getRequests();
    const requestsContainer = document.getElementById('requestsContainer');
    requestsContainer.innerHTML = '';

    requests.forEach(request => {
        const requestRow = document.createElement('tr');
        requestRow.innerHTML = `
            <td>${request.title}</td>
            <td>${request.descriptionReport}</td>
            <td>${request.address}</td>
            <td>${request.timeReport}</td>
        `;
        requestsContainer.appendChild(requestRow);
    });
}

// popola la tabella delle solution
async function populateSolutions() {
    const solutions = await getSolutions();
    const solutionsContainer = document.getElementById('solutionsContainer');
    solutionsContainer.innerHTML = '';

    solutions.forEach(solution => {
        const solutionRow = document.createElement('tr');
        solutionRow.innerHTML = `
            <td>${solution.title}</td>
            <td>${solution.descriptionReport}</td>
            <td>${solution.address}</td>
            <td>${solution.timeReport}</td>
        `;
        solutionsContainer.appendChild(solutionRow);
        console.log(solution);
    });
}

populateRequests();
populateSolutions();