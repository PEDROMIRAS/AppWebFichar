document.addEventListener("DOMContentLoaded", () => {
    const statusElement = document.getElementById("status");
    const button = document.getElementById("checkInOut");
    const historyList = document.getElementById("historyList");
    const clearHistoryButton = document.getElementById("clearHistory");
    const logoutButton = document.getElementById("btn-logout");
    let status = localStorage.getItem("attendanceStatus") || "Sin registro";
    let history = JSON.parse(localStorage.getItem("attendanceHistory")) || [];

    statusElement.textContent = `Estado actual: ${status}`;
    button.textContent = status === "entrada" ? "Marcar Salida" : "Marcar Entrada";
    
    const updateHistory = () => {
        historyList.innerHTML = "";
        history.forEach(entry => {
            const li = document.createElement("li");
            li.textContent = entry;
            historyList.appendChild(li);
        });
    };
    
    updateHistory();
    
    button.addEventListener("click", () => {
        const now = new Date();
        const timestamp = now.toLocaleString();
        status = status === "entrada" ? "salida" : "entrada";
        localStorage.setItem("attendanceStatus", status);
        statusElement.textContent = `Estado actual: ${status}`;
        button.textContent = status === "entrada" ? "Marcar Salida" : "Marcar Entrada";
        
        history.push(`${timestamp} - ${status}`);
        localStorage.setItem("attendanceHistory", JSON.stringify(history));
        updateHistory();
    });

    clearHistoryButton.addEventListener("click", () => {
        localStorage.removeItem("attendanceHistory");
        history = [];
        updateHistory();
    });

    logoutButton.addEventListener("click", () => {
        localStorage.removeItem("attendanceStatus");
        localStorage.removeItem("attendanceHistory");
        window.location.href = "../index.html";
    });
});