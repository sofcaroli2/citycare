const response = await fetch('/api/lanelogic/meetings', {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
    }
});