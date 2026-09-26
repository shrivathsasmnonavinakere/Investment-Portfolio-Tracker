const apiUrl = "http://localhost:8080/api/investments";

function loadInvestments() {
    const selectedType = document.getElementById("filterType").value;
    const urlWithFilter = selectedType ? apiUrl + "?type=" + selectedType : apiUrl;

    fetch(urlWithFilter)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById("tableBody");
            tableBody.innerHTML = "";

            data.forEach(investment => {
                const profitLossClass = investment.profitLoss >= 0 ? "profit" : "loss";

                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${investment.name}</td>
                    <td>${investment.type}</td>
                    <td>${investment.quantity}</td>
                    <td>${investment.buyPrice}</td>
                    <td>${investment.currentPrice}</td>
                    <td class="${profitLossClass}">${investment.profitLoss.toFixed(2)}</td>
                    <td class="${profitLossClass}">${investment.profitLossPercentage.toFixed(2)}%</td>
                    <td><button onclick="deleteInvestment(${investment.id})">Delete</button></td>
                `;
                tableBody.appendChild(row);
            });
        });

    loadSummary();
}

function loadSummary() {
    fetch(apiUrl + "/summary")
        .then(response => response.json())
        .then(summary => {
            const summaryBox = document.getElementById("summaryBox");
            const profitLossClass = summary.totalProfitLoss >= 0 ? "profit" : "loss";

            summaryBox.innerHTML = `
                <div><strong>Total Invested</strong><br>${summary.totalInvestedAmount.toFixed(2)}</div>
                <div><strong>Current Value</strong><br>${summary.totalCurrentValue.toFixed(2)}</div>
                <div class="${profitLossClass}"><strong>Profit / Loss</strong><br>${summary.totalProfitLoss.toFixed(2)} (${summary.totalProfitLossPercentage.toFixed(2)}%)</div>
                <div><strong>Best Performer</strong><br>${summary.bestPerformingInvestment}</div>
                <div><strong>Worst Performer</strong><br>${summary.worstPerformingInvestment}</div>
            `;
        });
}

function addInvestment() {
    const investment = {
        name: document.getElementById("name").value,
        type: document.getElementById("type").value,
        quantity: document.getElementById("quantity").value,
        buyPrice: document.getElementById("buyPrice").value,
        currentPrice: document.getElementById("currentPrice").value
    };

    fetch(apiUrl, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(investment)
    }).then(response => {
        if (!response.ok) {
            return response.json().then(err => { throw err; });
        }
        return response.json();
    }).then(() => {
        loadInvestments();
    }).catch(err => {
        alert(JSON.stringify(err));
    });
}

function deleteInvestment(id) {
    fetch(apiUrl + "/" + id, {
        method: "DELETE"
    }).then(() => {
        loadInvestments();
    });
}

loadInvestments();
