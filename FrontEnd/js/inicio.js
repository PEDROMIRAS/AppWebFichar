// Variables de información de inicio de sesión
const dni = document.getElementById('dni');
const contrasena = document.getElementById('password');

// Botón de iniciar sesión
const iniciarSesion = document.getElementById('btn-form');

// Evento de click en el botón de iniciar sesión
iniciarSesion.addEventListener('click', validarInicioSesion);

// Validar los datos del formulario
function validarInicioSesion(e) {
    e.preventDefault();

    // Eliminar alertas previas
    const alertaExistente = document.querySelector('.alerta');
    if (alertaExistente) alertaExistente.remove();

    if (dni.value.trim() === '' || contrasena.value.trim() === '') {
        mostrarAlerta('Todos los campos son obligatorios', true);
        return;
    }

    // Si todo está correcto, enviar los datos
    enviarDatos();
}

// Enviar datos y redirigir
function enviarDatos() {
    const datos = {
        dni: dni.value.trim(),
        contrasena: contrasena.value.trim()
    };

    console.log('Datos enviados:', datos);

    // Redirigir según el DNI
    if (datos.dni === "admin123") {
        // Redirigir al administrador
        window.location.href = "../html/dAdmin.html";
    } else {
        // Redirigir al empleado
        window.location.href = "../html/dEmpleado.html";
    }

    mostrarAlerta('Inicio de sesión correcto');
}

// Mostrar alerta
function mostrarAlerta(mensaje, error = false) {
    const alerta = document.createElement('p');
    alerta.textContent = mensaje;
    alerta.classList.add('alerta', error ? 'error' : 'correcto');

    document.getElementById('form').appendChild(alerta);

    setTimeout(() => {
        alerta.remove();
    }, 3000);
}